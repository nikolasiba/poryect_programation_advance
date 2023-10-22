package co.edu.uniquindio.proyecto.Services.Interfaces;

import co.edu.uniquindio.proyecto.Dto.Admin.ScheduleDTO;
import co.edu.uniquindio.proyecto.Dto.AppointmentDTO;
import co.edu.uniquindio.proyecto.Dto.Doctor.AppointmentDocDTO;
import co.edu.uniquindio.proyecto.Exception.AppointmentException.AppointmentsNotFoundException;
import co.edu.uniquindio.proyecto.Model.Enum.AppointmentState;
import co.edu.uniquindio.proyecto.Model.Schedule;

import java.util.List;

public interface DoctorServices {

    List<AppointmentDocDTO>listAppointment(int docCode) throws Exception;
    List<AppointmentDocDTO>listPendingAppointments(int docCode, AppointmentState appointmentState) throws Exception;
    List<ScheduleDTO> listSchedules (int docCode) throws Exception;
    List<AppointmentDocDTO>listFinishedAndCancelledAppointments(int docCode) throws AppointmentsNotFoundException;
    List<AppointmentDocDTO>listAppointmentsActualDay(int docCode) throws AppointmentsNotFoundException;

}