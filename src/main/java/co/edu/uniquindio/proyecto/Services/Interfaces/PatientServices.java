package co.edu.uniquindio.proyecto.Services.Interfaces;

import co.edu.uniquindio.proyecto.Dto.PatientDTOS.*;
import co.edu.uniquindio.proyecto.Exception.*;
import co.edu.uniquindio.proyecto.Model.Appointment;
import co.edu.uniquindio.proyecto.Model.Enum.AppointmentState;
import co.edu.uniquindio.proyecto.Model.Enum.DoctorState;
import co.edu.uniquindio.proyecto.Model.Enum.Specialization;
import co.edu.uniquindio.proyecto.Model.Patient;

import java.time.LocalDate;
import java.util.List;

public interface PatientServices {

    int sigIn(PatientDTO patientDTO) throws Exception;

    int editAccount(EditedPatientDTO editedPatientDTO) throws PatientNotExistException;
    void deleteAccount(int id) throws Exception;

    void sendLink(String email) throws Exception;
    int changePassword(ChangePasswordPatientDTO changePasswordPatientDTO) throws Exception;

    List<ItemDoctorDTO> checkAvailability(Specialization specialization, DoctorState doctorState);


    int createAppointment(CreateAppointmentPatientDTO createAppointmentPatientDTO) throws Exception;
    int cancelAppointement(int code) throws Exception;

    int calculateAge(LocalDate birthday);


    List<Appointment> listCodePatientAppointments(int code);
    List<AppointmentStateDTO> filterAppointmentsByState(AppointmentState appointmentState) throws NotAppointmentsCreatedException;


    Patient filterPatientByCode(int code)throws PatientNotExistException;
    PatientDTO getPatientByName(String name) throws PatientNotExistException;
    PatientDTO getPatientByIdentification(int identification) throws PatientNotExistException;





    int createPetition(ItemPetitionDTO itemPetitionDTO) throws AppointmentNotRelatedException;
    int cancelPetition(int code) throws PetitionNotFoundException;
    void filterPQRS();

    void responderPQRS();


    void filtrarCitasPorFecha();

    void filtrarCitasPorMedico();

    void verDetalleCita();

}
