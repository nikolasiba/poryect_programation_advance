package co.edu.uniquindio.proyecto.Services.Implement;

import co.edu.uniquindio.proyecto.Dto.EmailDTO;
import co.edu.uniquindio.proyecto.Dto.PatientDTOS.AppointmentStateDTO;
import co.edu.uniquindio.proyecto.Dto.PatientDTOS.CreateAppointmentPatientDTO;
import co.edu.uniquindio.proyecto.Exception.*;
import co.edu.uniquindio.proyecto.Model.Appointment;
import co.edu.uniquindio.proyecto.Model.Doctor;
import co.edu.uniquindio.proyecto.Model.Enum.AppointmentState;
import co.edu.uniquindio.proyecto.Model.Enum.DoctorState;
import co.edu.uniquindio.proyecto.Model.Patient;
import co.edu.uniquindio.proyecto.Repository.AppointmentRepo;
import co.edu.uniquindio.proyecto.Repository.DoctorRepo;
import co.edu.uniquindio.proyecto.Repository.PatientRepo;
import co.edu.uniquindio.proyecto.Services.Interfaces.AppointmentServices;
import co.edu.uniquindio.proyecto.Services.Interfaces.EmailServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AppointmentServicesImpl implements AppointmentServices {

    final PatientRepo patientRepo;
    final DoctorRepo doctorRepo;
    final AppointmentRepo appointmentRepo;

    final EmailServices emailServices;



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

    private boolean validateNumberAppointment(){
        return filterAppointmentsByState(AppointmentState.PENDING).size() <= 2;
    }

    @Override
    public int cancelAppointment(int code) throws AppointmentWasNotFoundException {

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
    public void listAppointmentPatient() {

    }

    @Override
    public void showDetailsAppointment() {

    }

    @Override
    public void filterAppointmentPerDoctor() {

    }

    @Override
    public void filterAppointmentPerDate() {

    }

    @Override
    public void listAppointmentPendientes() {

    }

    @Override
    public void atenderCita() {

    }

    @Override
    public void listAppointmentDoctor() {

    }
}
