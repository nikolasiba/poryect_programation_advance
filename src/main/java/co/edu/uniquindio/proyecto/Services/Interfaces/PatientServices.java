package co.edu.uniquindio.proyecto.Services.Interfaces;

import co.edu.uniquindio.proyecto.Dto.PatientDTOS.*;
import co.edu.uniquindio.proyecto.Exception.AppointmentNotRelatedException;
import co.edu.uniquindio.proyecto.Exception.AppointmentWasNotFoundException;
import co.edu.uniquindio.proyecto.Exception.PatientNotExistException;
import co.edu.uniquindio.proyecto.Exception.PetitionNotFoundException;
import co.edu.uniquindio.proyecto.Model.Enum.AppointmentState;
import co.edu.uniquindio.proyecto.Model.Enum.DoctorState;
import co.edu.uniquindio.proyecto.Model.Enum.Specialization;

import java.util.List;

public interface PatientServices {

    int sigIn(PatientDTO patientDTO) throws Exception;
    int editAccount(EditedPatientDTO editedPatientDTO) throws PatientNotExistException;
    void deleteAccount(int id) throws Exception;
    //consultar que retorno darle a este metodo, de enviar link de recuperación
    void sendLink(String email) throws Exception;
    //consultar que retorno darle a este metodo
    int changePassword(ChangePasswordPatientDTO changePasswordPatientDTO) throws Exception;
    List<ItemDoctorDTO> checkAvailability(Specialization specialization, DoctorState doctorState);
    int createAppointment(CreateAppointmentPatientDTO createAppointmentPatientDTO) throws Exception;
    int cancelAppointement(int code) throws AppointmentWasNotFoundException;
    List<AppointmentStateDTO> filterAppointmentsByState(AppointmentState appointmentState);
    int createPetition(ItemPetitionDTO itemPetitionDTO) throws AppointmentNotRelatedException;
    int cancelPetition(int code) throws PetitionNotFoundException;
    void filterPQRS();

    void responderPQRS();

    void listarCitasPaciente();

    void filtrarCitasPorFecha();

    void filtrarCitasPorMedico();

    void verDetalleCita();

}
