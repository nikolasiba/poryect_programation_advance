package co.edu.uniquindio.proyecto.Services.Implement;

import co.edu.uniquindio.proyecto.Dto.EmailDTO;
import co.edu.uniquindio.proyecto.Dto.PatientDTOS.*;
import co.edu.uniquindio.proyecto.Exception.*;
import co.edu.uniquindio.proyecto.Model.Appointment;
import co.edu.uniquindio.proyecto.Model.Doctor;
import co.edu.uniquindio.proyecto.Model.Enum.AppointmentState;
import co.edu.uniquindio.proyecto.Model.Enum.DoctorState;
import co.edu.uniquindio.proyecto.Model.Enum.PatientState;
import co.edu.uniquindio.proyecto.Model.Enum.Specialization;
import co.edu.uniquindio.proyecto.Model.Patient;
import co.edu.uniquindio.proyecto.Model.Petition;
import co.edu.uniquindio.proyecto.Repository.AppointmentRepo;
import co.edu.uniquindio.proyecto.Repository.DoctorRepo;
import co.edu.uniquindio.proyecto.Repository.PatientRepo;
import co.edu.uniquindio.proyecto.Services.Interfaces.EmailServices;
import co.edu.uniquindio.proyecto.Services.Interfaces.PatientServices;
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
    final EmailServices emailServices;
    final AppointmentRepo appointmentRepo;



    @Override
    public int sigIn(PatientDTO patientDTO) throws Exception {


        if (validateIfExists(patientDTO.identification())){
            throw new RepeatedIdException("Id "+patientDTO.identification()+" is already used");
        }

        if (validateIfEmailExist(patientDTO.email())){
            throw new RepeatedEmailException("Email"+ patientDTO.email()+"is already used");
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

        Patient newPatient = patientRepo.save(patient);

        EmailDTO emailDTO = new EmailDTO(
                newPatient.getEmail(),
                "Appointment Center Team",
                "Wellcome to appointment team, thanks to you with your support "
        );

        emailServices.sendEmail(emailDTO);

        return newPatient.getCode();

    }

    private boolean validateIfEmailExist(String email) {
        return patientRepo.finByEmail(email)!= null;
    }

    private boolean validateIfExists(int id) {
        return patientRepo.findByIdentification(id) != null;
    }

    @Override
    public int editAccount(EditedPatientDTO editedPatientDTO) throws PatientNotExistException {

        Optional<Patient> optional = patientRepo.findById(editedPatientDTO.code());

        if (optional.isEmpty()){
            throw new PatientNotExistException("Patient with the code "+editedPatientDTO.code()+" doesn´t exist");
        }

        Patient wanted = optional.get();

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
    public void deleteAccount(int code) throws Exception {

        Optional<Patient> optional = patientRepo.findById(code);

        if (optional.isEmpty()){
            throw new PatientNotExistException("Patient with the code "+code+" doesn´t exist");
        }

        Patient wanted = optional.get();
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

        if (validateIfEmailExist(email)){
            throw new PatientNotExistException("Patient with email "+email+" doesn´t exist");
        }

        EmailDTO emailDTO = new EmailDTO(
                email,
                "Appointment Center Team",
                "Please follow the link bellow to change your password\n RUTA"
        );

        emailServices.sendEmail(emailDTO);

    }

    @Override
    public int changePassword(ChangePasswordPatientDTO changePasswordPatientDTO) throws Exception {

        if (validateIfEmailExist(changePasswordPatientDTO.email())){
            throw new PatientNotExistException("Patient with email "+changePasswordPatientDTO.email()+" doesn´t exist");
        }

        Patient patient = patientRepo.finByEmail(changePasswordPatientDTO.email());

        if (changePasswordPatientDTO.repeatPassword().equals(changePasswordPatientDTO.password())){
            throw new PssWrdNotMatchException("Passwords don´t match, please repeat");
        }

        patient.setPassword(changePasswordPatientDTO.password());

        patientRepo.save(patient);

        EmailDTO emailDTO = new EmailDTO(
                changePasswordPatientDTO.email(),
                "Appointment Center Team",
                "Hi!\n Your password was changed successfully"
        );

        emailServices.sendEmail(emailDTO);

        return patient.getCode();
    }

    @Override
    public List<ItemDoctorDTO> checkAvailability(Specialization specialization, DoctorState doctorState) {

        List<Doctor> doctors = doctorRepo.findAllBySpecializationAndDoctorState(specialization, DoctorState.AVAILABE);

        List<ItemDoctorDTO> answer = new ArrayList<>();

        for (Doctor d: doctors) {
            answer.add( new ItemDoctorDTO(
                    d.getIdentification(),
                    d.getName(),
                    d.getScheduleList()) );
        }

        return answer;

    }

    private boolean validateNumberAppointment(){
        return filterAppointmentsByState(AppointmentState.PENDING).size() <= 2;
    }

    @Override
    public int createAppointment(CreateAppointmentPatientDTO createAppointmentPatientDTO) throws
            Exception {

        if (!validateNumberAppointment()){
            throw new MaxNumAppointmentReachedException("You have exceeded the maximum number of appointments");
        }

        Optional<Patient> optionalPatient  = patientRepo.findById(createAppointmentPatientDTO.patientCode());
        Optional<Doctor> optionalDoctor = doctorRepo.findById(createAppointmentPatientDTO.doctorCode());

        if (optionalPatient.isEmpty()){
            throw new PatientNotExistException("Patient with the code "+createAppointmentPatientDTO.patientCode()+
                    " doesn´t exist");
        }
        if (optionalDoctor.isEmpty()){
            throw new DoctorNotExistException("Doctor with the code "+createAppointmentPatientDTO.doctorCode()+
                    " doesn´t exist");
        }

        Patient patient = optionalPatient.get();
        Doctor doctor = optionalDoctor.get();

        Appointment appointment = new Appointment();

        appointment.setAppointmentDate(createAppointmentPatientDTO.appointmentDate());
        appointment.setCreatedDate(LocalDateTime.now());
        appointment.setReason(createAppointmentPatientDTO.reason());

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        appointment.setAppointmentState(AppointmentState.PENDING);

        appointmentRepo.save(appointment);

        EmailDTO emailDTOpatient = new EmailDTO(
                patient.getEmail(),
                "Appointment Center Team",
                "Hi!\n You have created an appointment, with the doctor: "+
                        doctor.getName()+", the day "+appointment.getAppointmentDate()+
                        " , it was created at: "+appointment.getCreatedDate()+". Hope you stay here, thank you"
        );

        emailServices.sendEmail(emailDTOpatient);

        EmailDTO emailDTOdoctor = new EmailDTO(
                doctor.getEmail(),
                "Appointment Center Team",
                "Hi!\n You have an appointemen with the patient:"+
                        patient.getName()+" the day "+appointment.getAppointmentDate()+
                        "it was created at: "+appointment.getCreatedDate()+". Have a good attention, see you soon"

        );

        emailServices.sendEmail(emailDTOdoctor);

        return appointment.getCode();

    }

    @Override
    public int cancelAppointement(int code) throws AppointmentWasNotFoundException {

        Optional<Appointment> optional = appointmentRepo.findById(code);

        if (optional.isEmpty()){
            throw new AppointmentWasNotFoundException("Appointment with the code "+code+" wasn´t found");
        }

        Appointment appointment = optional.get();

        appointment.setAppointmentState(AppointmentState.CANCELLED);

        appointmentRepo.save(appointment);

        return appointment.getCode();

    }

    @Override
    public List<AppointmentStateDTO> filterAppointmentsByState(AppointmentState appointmentState) {

        List<Appointment> appointments = appointmentRepo.findAllByAppointmentState(appointmentState);
        List<AppointmentStateDTO> answer = new ArrayList<>();

        for (Appointment a:appointments) {
            answer.add(new AppointmentStateDTO(
                    a.getAppointmentState(),
                    a.getCode(),
                    a.getAppointmentDate()) );

        }

        return answer;

    }



    @Override
    public void createPQRS() {
        validatePQRS();

    }

    private Boolean validatePQRS() {
        return null;
    }

    @Override
    public void filterPQRS() {

    }

    @Override
    public void responderPQRS() {

    }

    @Override
    public void listarCitasPaciente() {

    }

    @Override
    public void filtrarCitasPorFecha() {

    }

    @Override
    public void filtrarCitasPorMedico() {

    }

    @Override
    public void verDetalleCita() {

    }


}
