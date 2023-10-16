package co.edu.uniquindio.proyecto.Services.Interfaces;

import co.edu.uniquindio.proyecto.Dto.PatientDTOS.AppointmentStateDTO;
import co.edu.uniquindio.proyecto.Dto.PatientDTOS.CreateAppointmentPatientDTO;
import co.edu.uniquindio.proyecto.Exception.*;
import co.edu.uniquindio.proyecto.Model.Enum.AppointmentState;

import java.util.List;

public interface AppointmentServices {
    int createAppointment(CreateAppointmentPatientDTO createAppointmentPatientDTO) throws Exception;
    int cancelAppointment(int code) throws AppointmentWasNotFoundException;
    List<AppointmentStateDTO>filterAppointmentsByState(AppointmentState appointmentState);
    void listAppointmentPatient();
    void showDetailsAppointment();
    void filterAppointmentPerDoctor();
    void filterAppointmentPerDate();
    void listAppointmentPendientes();
    void atenderCita();
    void listAppointmentDoctor();



    //Es mejor hacer los servicios por rol
}
