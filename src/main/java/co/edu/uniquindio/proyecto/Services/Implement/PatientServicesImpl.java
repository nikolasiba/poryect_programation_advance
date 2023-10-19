package co.edu.uniquindio.proyecto.Services.Implement;

import co.edu.uniquindio.proyecto.Dto.*;
import co.edu.uniquindio.proyecto.Dto.Patient.EditedPatientDTO;
import co.edu.uniquindio.proyecto.Dto.Patient.ItemAppointmentPatientDTO;
import co.edu.uniquindio.proyecto.Dto.Patient.ItemPatientPwdDTO;
import co.edu.uniquindio.proyecto.Dto.Patient.PatientDTO;
import co.edu.uniquindio.proyecto.Dto.Petition.ItemDoctorPatientDTO;
import co.edu.uniquindio.proyecto.Exception.AppointmentException.AppointmentsNotFoundException;
import co.edu.uniquindio.proyecto.Exception.AttentionNotFoundException;
import co.edu.uniquindio.proyecto.Exception.DoctorExceptions.*;
import co.edu.uniquindio.proyecto.Exception.AppointmentException.MaxNumAppointmentReachedException;
import co.edu.uniquindio.proyecto.Exception.PatientException.PatientNotFoundException;
import co.edu.uniquindio.proyecto.Exception.PatientException.PatientWithEmailRepeatedException;
import co.edu.uniquindio.proyecto.Exception.PatientException.PatientWithIdRepeatedException;
import co.edu.uniquindio.proyecto.Exception.PatientException.PwdNotMatchException;
import co.edu.uniquindio.proyecto.Exception.AppointmentException.PatientPenalizedException;
import co.edu.uniquindio.proyecto.Model.Appointment;
import co.edu.uniquindio.proyecto.Model.Attention;
import co.edu.uniquindio.proyecto.Model.Doctor;
import co.edu.uniquindio.proyecto.Model.Enum.AppointmentState;
import co.edu.uniquindio.proyecto.Model.Enum.DoctorState;
import co.edu.uniquindio.proyecto.Model.Enum.PatientState;
import co.edu.uniquindio.proyecto.Model.Enum.Specialization;
import co.edu.uniquindio.proyecto.Model.Patient;
import co.edu.uniquindio.proyecto.Repository.AppointmentRepo;
import co.edu.uniquindio.proyecto.Repository.AttentionRepo;
import co.edu.uniquindio.proyecto.Repository.DoctorRepo;
import co.edu.uniquindio.proyecto.Repository.PatientRepo;
import co.edu.uniquindio.proyecto.Services.Interfaces.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PatientServicesImpl implements PatientServices {

    final PatientRepo patientRepo;
    final DoctorRepo doctorRepo;
    final AppointmentRepo appointmentRepo;
    final AttentionRepo attentionRepo;

    final EmailServices emailServices;

    @Override
    public int sigIn(PatientDTO patientDTO) throws Exception {

        if(validateRepeatedId(patientDTO.identification())){
            throw new PatientWithIdRepeatedException("The patient with the id: "+patientDTO.identification()+
                    " has been created");
        }
        if (validateRepeatedEmail(patientDTO.email())){
            throw new PatientWithEmailRepeatedException("The patient with the email: "+patientDTO.email()+
                    " has been created");
        }

        Patient patient = new Patient();

        patient.setEmail(patientDTO.email());
        patient.setPassword(patientDTO.password());

        patient.setName(patientDTO.name());
        patient.setIdentification(patientDTO.identification());
        patient.setPhone(patientDTO.phone());

        patient.setUrlPicture(patientDTO.urlPicture());

        patient.setCity(patientDTO.city());

        patient.setBirthday(patientDTO.birthday());

        patient.setAllergies(patientDTO.allergies());
        patient.setEps(patientDTO.eps());
        patient.setPatientState(PatientState.ASSET);

        Patient newPatietn = patientRepo.save(patient);

        EmailDTO emailDTO = new EmailDTO(
                patient.getEmail(),
                "Appointment Center Team",
                "Welcome to appointment team, thanks to you with your support "
        );

        emailServices.sendEmail(emailDTO);

        return newPatietn.getCode();

    }

    private boolean validateRepeatedEmail(String email) {
        return patientRepo.findByEmail(email)!=null;
    }

    private boolean validateRepeatedId(int identification) {
        return patientRepo.findByIdentification(identification)!=null;
    }



    @Override
    public int editAccount(EditedPatientDTO editedPatientDTO) throws PatientNotFoundException {

        Patient wanted = getPatientByCode(editedPatientDTO.code());

        wanted.setIdentification(editedPatientDTO.identification());
        wanted.setName(editedPatientDTO.name());
        wanted.setPhone(editedPatientDTO.phone());
        wanted.setUrlPicture(editedPatientDTO.urlPicture());
        wanted.setCity(editedPatientDTO.city());
        wanted.setBirthday(editedPatientDTO.birthday());
        wanted.setAllergies(editedPatientDTO.allergies());
        wanted.setBloodType(editedPatientDTO.bloodType());
        wanted.setEps(editedPatientDTO.eps());

        patientRepo.save(wanted);

        return wanted.getCode();

    }

    @Override
    public PatientDTO getPatientByIdentification(int identification) throws PatientNotFoundException{

        Patient patient = patientRepo.findByIdentification(identification);

        if (patient==null){
            throw new PatientNotFoundException("There is not patient with the id: "+identification);
        }

        return convertDTO(patient);

    }

    private PatientDTO convertDTO(Patient patient) {

        return new PatientDTO(
                patient.getName(),
                patient.getIdentification(),
                patient.getPhone(),
                patient.getUrlPicture(),
                patient.getCity(),
                patient.getBirthday(),
                patient.getAllergies(),
                patient.getBloodType(),
                patient.getEps(),
                patient.getEmail(),
                patient.getPassword()
        );

    }

    @Override
    public void deleteAccount(int code) throws Exception {

        Patient wanted = getPatientByCode(code);
        wanted.setPatientState(PatientState.INACTIVE);

        EmailDTO emailDTO = new EmailDTO(
                wanted.getEmail(),
                "Appointment Center Team",
                "Hi!\nYour account was deleted successfully"
        );

        emailServices.sendEmail(emailDTO);

        patientRepo.save(wanted);

    }


    @Override
    public void sendLink(String email) throws Exception {

        if (validatePatientEmail(email)){
            throw new PatientNotFoundException("The patient with the email: "+email+
            " was not found");
        }

        EmailDTO emailDTO = new EmailDTO(
                email,
                "Appointment Center Team",
                "Please follow the link bellow to change your password\n //RUTA"
        );

        emailServices.sendEmail(emailDTO);

    }

    private boolean validatePatientEmail(String email) {
        return patientRepo.findByEmail(email)==null;
    }

    @Override
    public int changePassword(ItemPatientPwdDTO itemPatientPwdDTO) throws Exception {

        if (validatePatientEmail(itemPatientPwdDTO.email())){
            throw new PatientNotFoundException("Patient with email "+itemPatientPwdDTO.email()+" does not exist");
        }

        Patient patient = patientRepo.findByEmail(itemPatientPwdDTO.email());

        if (!itemPatientPwdDTO.repeatPassword().equals(itemPatientPwdDTO.password())){
            throw new PwdNotMatchException("Passwords don´t match, please repeat it");
        }

        patient.setPassword(itemPatientPwdDTO.password());

        Patient newPatient = patientRepo.save(patient);

        EmailDTO emailDTO = new EmailDTO(
                itemPatientPwdDTO.email(),
                "Appointment Center Team",
                "Hi!\n Your password was changed successfully"
        );

        emailServices.sendEmail(emailDTO);

        return newPatient.getCode();

    }


    @Override
    public List<ItemDoctorPatientDTO> checkAvailability(Specialization specialization, DoctorState doctorState)
            throws DoctorsNotFoundException {

        List<Doctor> doctors = doctorRepo.findAllBySpecializationAndDoctorState(specialization, DoctorState.AVAILABE);

        if (doctors.isEmpty()){
            throw new DoctorsNotFoundException("There are not doctors available with the specialization that you need,"+
                    " please do it later");
        }

        List<ItemDoctorPatientDTO> answer = new ArrayList<>();

        for (Doctor d: doctors) {
            answer.add( new ItemDoctorPatientDTO(
                    d.getIdentification(),
                    d.getName(),
                    d.getScheduleList()) );
        }

        return answer;

    }

    @Override
    public AppointmentDTO createAppointment(ItemAppointmentPatientDTO itemAppointmentPatientDTO)
            throws Exception {


        if (!validateNumAppointment(itemAppointmentPatientDTO.patientCode())){
            throw new MaxNumAppointmentReachedException("You have exceeded the maximum number of appointments");
        }

        if (validatePatientState(itemAppointmentPatientDTO.patientCode())){
            throw new PatientPenalizedException("The patient with the code: "+itemAppointmentPatientDTO.patientCode()+
                    "is penalized");
        }

        Optional<Doctor> optionalDoctor = doctorRepo.findById(itemAppointmentPatientDTO.doctorCode());

        if (optionalDoctor.isEmpty()){
            throw new DoctorNotFoundException("Doctor with the code "+itemAppointmentPatientDTO.doctorCode()+
                    " does not exist");
        }

        Patient patient = getPatientByCode(itemAppointmentPatientDTO.patientCode());
        Doctor doctor = optionalDoctor.get();

        if (patient.getPatientState().equals(PatientState.INACTIVE)){
            throw new PatientInactiveException("The patient "+patient.getName()+" was invalidated");
        }

        if (validateNumAppointmentCanceled(itemAppointmentPatientDTO.patientCode())){
            throw new ReachedNumCancelAppointmentsException("The patient "+patient.getName()+
                    " has benn reached the number of canceled appointment");
        }

        Appointment appointment = new Appointment();

        appointment.setAppointmentDate(itemAppointmentPatientDTO.date());
        appointment.setCreatedDate(LocalDateTime.now());
        appointment.setReason(itemAppointmentPatientDTO.reason());

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        appointment.setAppointmentState(AppointmentState.PENDING);



        Appointment newAppointment = appointmentRepo.save(appointment);

        EmailDTO emailDTOpatient = new EmailDTO(
                patient.getEmail(),
                "Appointment Center Team",
                "Hi!\n You have created an appointment, with the doctor: "+
                        doctor.getName()+", the day "+appointment.getAppointmentDate()+
                        " , it was created at: "+appointment.getCreatedDate()+" Hope you stay here, thank you"
        );

        emailServices.sendEmail(emailDTOpatient);

        EmailDTO emailDTOdoctor = new EmailDTO(
                doctor.getEmail(),
                "Appointment Center Team",
                "Hi!\n You have an appointment with the patient:"+
                        patient.getName()+" the day "+appointment.getAppointmentDate()+
                        "it was created at: "+appointment.getCreatedDate()+". Have a good attention, see you soon"

        );

        emailServices.sendEmail(emailDTOdoctor);

        return convertAppointmentDTO(newAppointment.getCode());

    }

    private AppointmentDTO convertAppointmentDTO(int code) throws AppointmentNotFoundException {
        Appointment a = getAppointmentByCode(code);
        return new AppointmentDTO(
                a.getCode(),
                a.getAppointmentDate(),
                a.getDoctor().getName(),
                a.getDoctor().getSpecialization()
        );
    }

    private Appointment getAppointmentByCode(int code) throws AppointmentNotFoundException {
        Optional<Appointment>optional = appointmentRepo.findById(code);
        if (optional.isEmpty()){
            throw new AppointmentNotFoundException("Appointment with the code: "+code+" was not found");
        }
        return optional.get();
    }

    private boolean validateNumAppointmentCanceled(int i)
            throws PatientNotFoundException {

        Optional<Patient> optional = patientRepo.findById(i);

        if (optional.isEmpty()){
            throw new PatientNotFoundException("Patient with the code: "+i+" was not found");
        }

        Patient patient = optional.get();

        List<Appointment> appointments = patient.getAppointmentList();
        List<Appointment>canceledAppointments = new ArrayList<>();
        for (Appointment a:appointments) {
            if (a.getAppointmentState().equals(AppointmentState.CANCELLED)){
                canceledAppointments.add(a);
            }
        }

        return canceledAppointments.size()>=3;

    }
    private Patient getPatientByCode(int code) throws PatientNotFoundException {

        Optional<Patient> optional = patientRepo.findById(code);

        if (optional.isEmpty()){
            throw new PatientNotFoundException("The patient with the code: "+ code+" was not found");
        }

        return optional.get();
    }

    private boolean validatePatientState(int i) throws PatientNotFoundException {

        Optional<Patient> optional = patientRepo.findById(i);

        if (optional.isEmpty()){
            throw new PatientNotFoundException("Patient with the code: "+i+" was not found");
        }

        Patient patient = optional.get();

        return patient.getPatientState().equals(PatientState.PENALIZED);
    }

    private boolean validateNumAppointment(int code) throws PatientNotFoundException {

        Optional<Patient>optional=patientRepo.findById(code);

        if (optional.isEmpty()){
            throw new PatientNotFoundException("Patient with the code: "+code+" was not found");
        }

        Patient patient = optional.get();

        int numberAppointments = patient.getAppointmentList().size();

        return numberAppointments<=3;

    }

    private String getEmailPatientByAppointmentCode(int code) throws AppointmentNotFoundException {
        Appointment appointment = getAppointmentByCode(code);
        return appointment.getPatient().getEmail();
    }

    @Override
    public int cancelAppointment(int code) throws Exception {

        Appointment appointment = getAppointmentByCode(code);

        if (validateNumAppointmentCanceled(code)){

            penalizePatient(appointment.getPatient());

            EmailDTO emailDTO = new EmailDTO(

                    getEmailPatientByAppointmentCode(code),
                    "Appointment Center Team",
                    "Hi!\n Your Account was penalized because there are more than three canceled appointments"
            );

            emailServices.sendEmail(emailDTO);

            throw new ReachedNumCancelAppointmentsException("The number of cancel appointment was reached");

        }

        appointment.setAppointmentState(AppointmentState.CANCELLED);

        Appointment newAppointment = appointmentRepo.save(appointment);

        return newAppointment.getCode();

    }

    private void penalizePatient(Patient patient) {
        patient.setPatientState(PatientState.PENALIZED);
    }

    @Override
    public List<AppointmentDTO> listAppointment() throws AppointmentNotFoundException, AppointmentsNotFoundException {

        List<Appointment>appointments = appointmentRepo.findAll();
        if (appointments.isEmpty()){
            throw new AppointmentsNotFoundException("There are not appointments");
        }
        List<AppointmentDTO> answer =new ArrayList<>();

        for (Appointment a: appointments) {
            answer.add(convertAppointmentDTO(a.getCode()));
        }

        return answer;

    }

    @Override
    public List<AppointmentDTO> filterAppointmentsByState(AppointmentState appointmentState) throws AppointmentsNotFoundException {

        List<Appointment> appointments = appointmentRepo.findAllByAppointmentState(appointmentState);

        if (appointments.isEmpty()){
            throw new AppointmentsNotFoundException("There are not appointments");
        }

        List<AppointmentDTO> answer = new ArrayList<>();

        for (Appointment a:appointments) {
            answer.add(new AppointmentDTO(
                    a.getCode(),
                    a.getAppointmentDate(),
                    a.getDoctor().getName(),
                    a.getDoctor().getSpecialization()
            ) );

        }

        return answer;

    }

    @Override
    public ItemAttentionDTO showDetailsAppointment(AppointmentDTO appointmentDTO)
            throws AppointmentNotFoundException, AttentionNotFoundException {

        Appointment appointment = getAppointmentByCode(appointmentDTO.code());
        Attention attention = attentionRepo.findByAppointmentCode(appointmentDTO.code());
        if(attention==null){
            throw new AttentionNotFoundException("There is not attention associated with an appointment");
        }
        return new ItemAttentionDTO(
                attention.getCode(),
                attention.getDiagnosis(),
                attention.getTreatment(),
                attention.getMedicalNotes(),
                appointment.getCode(),
                appointment.getAppointmentState()
        );

    }
}
