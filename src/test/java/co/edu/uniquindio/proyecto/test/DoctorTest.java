package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.Dto.Admin.ScheduleDTO;
import co.edu.uniquindio.proyecto.Dto.AppointmentAttentionDTO;
import co.edu.uniquindio.proyecto.Dto.DayOffDTO;
import co.edu.uniquindio.proyecto.Dto.Doctor.AppointmentDocDTO;
import co.edu.uniquindio.proyecto.Exception.AppointmentException.AppointmentsNotFoundException;
import co.edu.uniquindio.proyecto.Exception.AppointmentHasAlreadyCreatedException;
import co.edu.uniquindio.proyecto.Exception.DoctorExceptions.DoctorNotFoundException;
import co.edu.uniquindio.proyecto.Exception.DoctorHasAlreadyDayOffException;
import co.edu.uniquindio.proyecto.Model.Appointment;
import co.edu.uniquindio.proyecto.Model.Enum.AppointmentState;
import co.edu.uniquindio.proyecto.Model.Enum.ScheduleState;
import co.edu.uniquindio.proyecto.Repository.DoctorRepo;
import co.edu.uniquindio.proyecto.Services.Interfaces.DoctorServices;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Sql(value = "/dataset.sql")
@Transactional
@SpringBootTest
public class DoctorTest {

    @Autowired
    private DoctorServices doctorServices;
    @Autowired
    private DoctorRepo doctorRepo;


    @Test
    public void listAppointment() {
        try {
            List<AppointmentDocDTO> appointmentDocDTOS = doctorServices.listAppointment(4);
            System.out.println(appointmentDocDTOS);
        } catch (AppointmentsNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void listPendingAppointments() {
        try {
            List<AppointmentDocDTO> appointmentDocDTOS = doctorServices.listPendingAppointments(4, AppointmentState.PENDING);
            System.out.println(appointmentDocDTOS);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    @Test
    public void listFinishedAndCancelledAppointments() {
        try {
            List<AppointmentDocDTO> appointmentDocDTOS = doctorServices.listFinishedAndCancelledAppointments(4);
            if (appointmentDocDTOS.isEmpty()) {
                throw new AppointmentsNotFoundException("No se encontraron citas finalizadas o canceladas");
            }
            System.out.println(appointmentDocDTOS);
        } catch (AppointmentsNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void listSchedules() {
        try {
            List<ScheduleDTO> appointmentDocDTOS = doctorServices.listSchedules(4);
            if (appointmentDocDTOS.isEmpty()) {
                throw new AppointmentsNotFoundException("No se encontraron horarios");
            }
            System.out.println(appointmentDocDTOS);

        } catch (AppointmentsNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void listAppointmentsActualDay() {
        try {
            List<AppointmentDocDTO> appointmentDocDTOS = doctorServices.listAppointmentsActualDay(4);
            System.out.println(appointmentDocDTOS);
        } catch (AppointmentsNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void listFutureAppointments() {
        try {
            List<AppointmentDocDTO> appointmentDocDTOS = doctorServices.listFutureAppointments(4);
            if (appointmentDocDTOS.isEmpty()) {
                throw new AppointmentsNotFoundException("No se encontraron citas futuras");
            }
            System.out.println(appointmentDocDTOS);
        } catch (AppointmentsNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void attendAppointment() {
        AppointmentAttentionDTO appointmentAttentionDTO = new AppointmentAttentionDTO(2, "Se atendio la cita", "qweqweqwe", "qweqwe");

        AppointmentAttentionDTO appointmentAttentionDTO2 = new AppointmentAttentionDTO(3, "Se atendio la cita", "qweqweqwe", "qweqwe");

        try {
            int code = doctorServices.attendAppointment(appointmentAttentionDTO);
            int code2 = doctorServices.attendAppointment(appointmentAttentionDTO2);
            System.out.println(code);
            System.out.println(code2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void assignDayOff() {
        DayOffDTO dayOffDTO = new DayOffDTO(LocalDateTime.now(),4 );

        try{
            int code = doctorServices.assignDayOff(dayOffDTO);
            System.out.println(code);
        } catch (AppointmentsNotFoundException | DoctorNotFoundException | DoctorHasAlreadyDayOffException |
                 AppointmentHasAlreadyCreatedException e) {
            System.out.println(e.toString());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
