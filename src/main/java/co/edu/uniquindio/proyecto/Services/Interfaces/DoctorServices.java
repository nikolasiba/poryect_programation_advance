package co.edu.uniquindio.proyecto.Services.Interfaces;

import co.edu.uniquindio.proyecto.Dto.Admin.ScheduleDTO;
import co.edu.uniquindio.proyecto.Dto.AppointmentAttentionDTO;
import co.edu.uniquindio.proyecto.Dto.DayOffDTO;
import co.edu.uniquindio.proyecto.Dto.Doctor.AppointmentDocDTO;
import co.edu.uniquindio.proyecto.Exception.AppointmentException.AppointmentsNotFoundException;
import co.edu.uniquindio.proyecto.Exception.AppointmentHasAlreadyCreatedException;
import co.edu.uniquindio.proyecto.Exception.DoctorExceptions.AppointmentNotFoundException;
import co.edu.uniquindio.proyecto.Exception.DoctorExceptions.DoctorNotFoundException;
import co.edu.uniquindio.proyecto.Exception.DoctorHasAlreadyDayOffException;
import co.edu.uniquindio.proyecto.Model.Attention;
import co.edu.uniquindio.proyecto.Model.Enum.AppointmentState;

import java.util.List;

public interface DoctorServices {

    List<AppointmentDocDTO> listAppointment(int docCode) throws Exception;

    List<AppointmentDocDTO> listPendingAppointments(int docCode, AppointmentState appointmentState) throws Exception;

    List<AppointmentDocDTO> listFinishedAndCancelledAppointments(int docCode) throws AppointmentsNotFoundException;

    List<ScheduleDTO> listSchedules(int docCode) throws Exception;

    List<AppointmentDocDTO> listAppointmentsActualDay(int docCode) throws AppointmentsNotFoundException;

    List<AppointmentDocDTO> listFutureAppointments(int docCode) throws AppointmentsNotFoundException;

    int attendAppointment(AppointmentAttentionDTO appointmentAttentionDTO) throws Exception;

    int assignDayOff(DayOffDTO dayOffDTO) throws AppointmentsNotFoundException, AppointmentHasAlreadyCreatedException, DoctorNotFoundException, DoctorHasAlreadyDayOffException;

}