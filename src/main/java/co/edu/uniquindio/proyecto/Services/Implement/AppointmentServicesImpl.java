package co.edu.uniquindio.proyecto.Services.Implement;

import co.edu.uniquindio.proyecto.Dto.AppointmentDTOS.ItemAppointmentDTO;
import co.edu.uniquindio.proyecto.Dto.AppointmentDTOS.ItemAppointmentDoctorDTO;
import co.edu.uniquindio.proyecto.Dto.AppointmentDTOS.ItemAppointmentPatientDTO;
import co.edu.uniquindio.proyecto.Dto.AppointmentDTOS.ItemAttentionDTO;
import co.edu.uniquindio.proyecto.Dto.AttentionDTO;
import co.edu.uniquindio.proyecto.Dto.EmailDTO;
import co.edu.uniquindio.proyecto.Dto.PatientDTOS.AppointmentStateDTO;
import co.edu.uniquindio.proyecto.Dto.PatientDTOS.CreateAppointmentPatientDTO;
import co.edu.uniquindio.proyecto.Dto.PatientDTOS.PatientDTO;
import co.edu.uniquindio.proyecto.Exception.*;
import co.edu.uniquindio.proyecto.Model.Appointment;
import co.edu.uniquindio.proyecto.Model.Doctor;
import co.edu.uniquindio.proyecto.Model.Enum.AppointmentState;
import co.edu.uniquindio.proyecto.Model.Enum.DoctorState;
import co.edu.uniquindio.proyecto.Model.Patient;
import co.edu.uniquindio.proyecto.Repository.AppointmentRepo;
import co.edu.uniquindio.proyecto.Repository.DoctorRepo;
import co.edu.uniquindio.proyecto.Repository.PatientRepo;
import co.edu.uniquindio.proyecto.Services.Interfaces.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static co.edu.uniquindio.proyecto.Model.Enum.PatientState.INVALIDATED;


@Service
@RequiredArgsConstructor
public class AppointmentServicesImpl implements AppointmentServices {
    @Override
    public int createAppointment(CreateAppointmentPatientDTO createAppointmentPatientDTO) throws Exception {
        return 0;
    }

    @Override
    public int cancelAppointment(int code) throws Exception {
        return 0;
    }

    @Override
    public ItemAppointmentDTO getItemAppointmentDTO(int code) throws AppointmentWasNotFoundException {
        return null;
    }

    @Override
    public List<ItemAppointmentDTO> listAppointment() {
        return null;
    }

    @Override
    public List<ItemAppointmentDoctorDTO> listAppointmentDoctor() throws NotAppointmentsCreatedException {
        return null;
    }

    @Override
    public List<AppointmentStateDTO> filterAppointmentsByState(AppointmentState appointmentState) throws NotAppointmentsCreatedException {
        return null;
    }

    @Override
    public List<ItemAppointmentDTO> filterAppointmentByCodePatient(int code) throws NotAppointmentsCreatedException {
        return null;
    }

    @Override
    public ItemAppointmentPatientDTO showDetailsAppointment(ItemAppointmentDTO itemAppointmentDTO) throws AppointmentWasNotFoundException {
        return null;
    }

    @Override
    public ItemAppointmentDoctorDTO showDetailsAppointmentDoctor(ItemAppointmentDTO itemAppointmentDTO) throws AppointmentWasNotFoundException {
        return null;
    }

    @Override
    public List<ItemAppointmentDTO> filterAppointmentPerDoctor(String doctorName) throws NotAppointmentsCreatedException {
        return null;
    }

    @Override
    public ItemAppointmentDTO filterAppointmentPerDate(LocalDateTime date) throws NotAppointmentsCreatedException {
        return null;
    }

    @Override
    public ItemAttentionDTO attendAppointment(ItemAppointmentDoctorDTO itemAppointmentDoctorDTO) throws AttentionHasAlreadyBeenDoneException, AttentionWasNotFoundException, PatientNotExistException {
        return null;
    }

/*    final PatientRepo patientRepo;
    final DoctorRepo doctorRepo;
    final AppointmentRepo appointmentRepo;

    final EmailServices emailServices;
    final AdminServices adminServices;
    final AttentionServices attentionServices;
    final PatientServices patientServices;

    @Override
    public int createAppointment(CreateAppointmentPatientDTO createAppointmentPatientDTO) throws Exception {

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

        Doctor doctor = optionalDoctor.get();

        if (doctor.getDoctorState()!= DoctorState.AVAILABE){
            throw new DoctorNotAvailableException("The doctor "+doctor.getName()+" isn´t available");
        }

        Patient patient = optionalPatient.get();

        if (patient.getPatientState().equals(INVALIDATED)){
            throw new PatientIsInvalidatedException("The patient "+patient.getName()+" was invalidated");
        }

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

        return appointment.getCode();

    }

    @Override
    public int cancelAppointment(int code) throws Exception {

        Appointment appointment = getAppointment(code);

        if (!validateNumberAppointmentCancelled()){

            adminServices.penalizePatient(appointment.getPatient().getCode());

            EmailDTO emailDTO = new EmailDTO(

                    appointment.getPatient().getEmail(),
                    "Appointment Center Team",
                    "Hi!\n Your Account was penalized because there are more than three canceled appointment"
            );

            emailServices.sendEmail(emailDTO);

            throw new NumberAppointmentCancelWasExceeded("Number of canceled appointments was exceeded");

        }

        appointment.setAppointmentState(AppointmentState.CANCELLED);
        appointmentRepo.save(appointment);

        return appointment.getCode();

    }

    @Override
    public ItemAppointmentDTO getItemAppointmentDTO(int code) throws AppointmentWasNotFoundException {

        Optional<Appointment> optional = appointmentRepo.findById(code);

        if (optional.isEmpty()){
            throw new AppointmentWasNotFoundException("Appointmet with the code "+code+" was not found");
        }

        Appointment appointment = optional.get();

        return new ItemAppointmentDTO(
                appointment.getCode(),
                appointment.getAppointmentState(),
                appointment.getAppointmentDate()
        );

    }

    private Appointment getAppointment(int code) throws AppointmentWasNotFoundException {

        Optional<Appointment> optional = appointmentRepo.findById(code);

        if (optional.isEmpty()){
            throw new AppointmentWasNotFoundException("Appointmet with the code "+code+" was not found");
        }

        return optional.get();

    }

    @Override
    public List<ItemAppointmentDTO> listAppointment() {

        List<Appointment>appointments = appointmentRepo.findAll();
        List<ItemAppointmentDTO>answer=new ArrayList<>();

        for (Appointment a: appointments) {
            answer.add(convert(a));
        }

        return answer;

    }

    private ItemAppointmentDTO convert(Appointment a) {

        return new ItemAppointmentDTO(

                a.getCode(),
                a.getAppointmentState(),
                a.getAppointmentDate());

    }

    @Override
    public List<AppointmentStateDTO> filterAppointmentsByState(AppointmentState appointmentState) throws NotAppointmentsCreatedException {

        List<Appointment> appointments = appointmentRepo.findAllByAppointmentState(appointmentState);
        List<AppointmentStateDTO> answer = new ArrayList<>();

        if (appointments.isEmpty()){
            throw new NotAppointmentsCreatedException("There are not appointments created");
        }

        for (Appointment a:appointments) {
            answer.add(new AppointmentStateDTO(
                    a.getAppointmentState(),
                    a.getCode(),
                    a.getAppointmentDate()) );

        }

        return answer;

    }

    @Override
    public List<ItemAppointmentDTO> filterAppointmentByCodePatient(int code) throws NotAppointmentsCreatedException {

        List<Appointment> list = appointmentRepo.findAllByPatientCode(code);
        return getItemAppointmentDTOS(list);

    }

    @Override
    public ItemAppointmentPatientDTO showDetailsAppointment(ItemAppointmentDTO itemAppointmentDTO)
            throws AppointmentWasNotFoundException {

        Appointment appointment = getAppointment(itemAppointmentDTO.code());

        return new ItemAppointmentPatientDTO(
            appointment.getCode(),

                //hacer los metodos de medico para borrar los doble get

                appointment.getAppointmentState(),
                appointment.getAppointmentDate(),
                appointment.getDoctor().getName(),
                appointment.getDoctor().getSpecialization()

        );

    }

    @Override
    public ItemAppointmentDoctorDTO showDetailsAppointmentDoctor(ItemAppointmentDTO itemAppointmentDTO)
            throws AppointmentWasNotFoundException {

        Appointment appointment = getAppointment(itemAppointmentDTO.code());

        return new ItemAppointmentDoctorDTO(
                appointment.getCode(),
                appointment.getAppointmentState(),
                appointment.getAppointmentDate(),
                appointment.getPatient().getName(),
                appointment.getPatient().getIdentification()
        );

    }

    @Override
    public List<ItemAppointmentDTO> filterAppointmentPerDoctor(String doctorName) throws NotAppointmentsCreatedException {

        List<Appointment> appointments = appointmentRepo.findAllByDoctorName(doctorName);
        return getItemAppointmentDTOS(appointments);

    }

    private List<ItemAppointmentDTO> getItemAppointmentDTOS(List<Appointment> appointments) throws NotAppointmentsCreatedException {

        List<ItemAppointmentDTO> answer = new ArrayList<>();

        if (appointments.isEmpty()){
            throw new NotAppointmentsCreatedException("There are not appointments created");
        }

        for (Appointment a:appointments) {

            answer.add( new ItemAppointmentDTO(
                    a.getCode(),
                    a.getAppointmentState(),
                    a.getAppointmentDate()) );

        }

        return answer;

    }

    @Override
    public ItemAppointmentDTO filterAppointmentPerDate(LocalDateTime date) throws NotAppointmentsCreatedException {

        Appointment appointment = appointmentRepo.findByAppointmentDate(date);

        if (appointment == null){
            throw new NotAppointmentsCreatedException("There are not appointments created with the date "+date);
        }

        return new ItemAppointmentDTO(
                appointment.getCode(),
                appointment.getAppointmentState(),
                appointment.getAppointmentDate()
        );

    }

    @Override
    public ItemAttentionDTO attendAppointment(ItemAppointmentDoctorDTO itemAppointmentDoctorDTO)
            throws AttentionHasAlreadyBeenDoneException, AttentionWasNotFoundException, PatientNotExistException {

            AttentionDTO attentionDTO = attentionServices.getAttentionDTO(attentionServices.createdAttention(itemAppointmentDoctorDTO));
            PatientDTO patientDTO = patientServices.getPatientByIdentification(itemAppointmentDoctorDTO.patientIdentification());
            int age = patientServices.calculateAge(patientDTO.birthday());

        return new ItemAttentionDTO(

                attentionDTO.code(),
                patientDTO.name(),
                age,
                patientDTO.bloodType(),
                patientDTO.eps(),
                attentionDTO.diagnosis(),
                attentionDTO.treatment(),
                attentionDTO.medicalNotes() );

    }

    @Override
    public List<ItemAppointmentDoctorDTO> listAppointmentDoctor() throws NotAppointmentsCreatedException {

        List<Appointment> appointments = appointmentRepo.findAll();
        List<ItemAppointmentDoctorDTO> answer = new ArrayList<>();

        if (appointments.isEmpty()){
            throw new NotAppointmentsCreatedException("There are not appointments created");
        }

        for (Appointment a:appointments) {
            answer.add(new ItemAppointmentDoctorDTO(
                    a.getCode(),
                    a.getAppointmentState(),
                    a.getAppointmentDate(),
                    a.getPatient().getName(),
                    a.getPatient().getIdentification())
                    );

        }

        return answer;

    }

    private boolean validateNumberAppointment() throws NotAppointmentsCreatedException {
        return filterAppointmentsByState(AppointmentState.PENDING).size() <= 2;
    }

    private boolean validateNumberAppointmentCancelled() throws NotAppointmentsCreatedException {
        return filterAppointmentsByState(AppointmentState.CANCELLED).size() == 3;
    }*/

}
