package co.edu.uniquindio.proyecto.Controllers;

import co.edu.uniquindio.proyecto.Dto.Admin.ScheduleDTO;
import co.edu.uniquindio.proyecto.Dto.AppointmentAttentionDTO;
import co.edu.uniquindio.proyecto.Dto.DayOffDTO;
import co.edu.uniquindio.proyecto.Dto.Doctor.AppointmentDocDTO;
import co.edu.uniquindio.proyecto.Exception.AppointmentException.AppointmentsNotFoundException;
import co.edu.uniquindio.proyecto.Exception.AppointmentHasAlreadyCreatedException;
import co.edu.uniquindio.proyecto.Exception.AttentionNotAssociatedAppointmentException;
import co.edu.uniquindio.proyecto.Exception.DoctorExceptions.AppointmentNotFoundException;
import co.edu.uniquindio.proyecto.Exception.DoctorExceptions.DoctorNotFoundException;
import co.edu.uniquindio.proyecto.Exception.DoctorHasAlreadyDayOffException;
import co.edu.uniquindio.proyecto.Exception.PatientException.PatientNotFoundException;
import co.edu.uniquindio.proyecto.Model.Enum.AppointmentState;
import co.edu.uniquindio.proyecto.Services.Interfaces.DoctorServices;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/doctor")
@SecurityRequirement(name = "bearerAuth")

public class DoctorController {

    private final DoctorServices doctorServices;

    @GetMapping("/get-appointment/{docCode}")
    public List<AppointmentDocDTO> listAppointment(@PathVariable int docCode) throws Exception{
        return doctorServices.listAppointment(docCode);
    }

    @GetMapping("/get-pending-appointments/{docCode}")
    public List<AppointmentDocDTO> listPendingAppointments(@PathVariable int docCode, @Valid @RequestBody AppointmentState appointmentState)
            throws Exception{
        return doctorServices.listPendingAppointments(docCode,appointmentState);
    }

    @GetMapping("/get-cancelled-finished-appointments/{docCode}")
    public List<AppointmentDocDTO> listFinishedAndCancelledAppointments(@PathVariable int docCode) throws AppointmentsNotFoundException{
        return doctorServices.listFinishedAndCancelledAppointments(docCode);
    }

    @GetMapping("/get-schedules/{docCode}")
    public List<ScheduleDTO> listSchedules(int docCode) throws Exception{
        return doctorServices.listSchedules(docCode);
    }

    @GetMapping("/get-choosed-attention")
    public AppointmentAttentionDTO showDetailsAttention(@Valid @RequestBody AppointmentDocDTO appointmentDocDTO)
            throws PatientNotFoundException, AppointmentNotFoundException, AttentionNotAssociatedAppointmentException{
        return doctorServices.showDetailsAttention(appointmentDocDTO);
    }

    @GetMapping("/get-choosed-appointment/{patientCode}")
    public List<AppointmentDocDTO> showAppointmentHistoryPatient(@PathVariable int patientCode)
            throws AppointmentsNotFoundException{
        return doctorServices.showAppointmentHistoryPatient(patientCode);
    }

    @GetMapping("/get-actual-appointment/{docCode}")
    public List<AppointmentDocDTO> listAppointmentsActualDay(@PathVariable int docCode) throws AppointmentsNotFoundException{
        return doctorServices.listAppointmentsActualDay(docCode);
    }

    @GetMapping("/get-future-appointment/{docCode}")
    public List<AppointmentDocDTO> listFutureAppointments(@PathVariable int docCode) throws AppointmentsNotFoundException{
        return doctorServices.listFutureAppointments(docCode);
    }

    @PostMapping("/attend-appointment")
    public int attendAppointment(@Valid @RequestBody AppointmentAttentionDTO appointmentAttentionDTO) throws Exception{
        return doctorServices.attendAppointment(appointmentAttentionDTO);
    }

    @PostMapping("/assign-day-off")
    public int assignDayOff(@Valid @RequestBody DayOffDTO dayOffDTO) throws AppointmentsNotFoundException,
            AppointmentHasAlreadyCreatedException, DoctorNotFoundException, DoctorHasAlreadyDayOffException{
        return doctorServices.assignDayOff(dayOffDTO);
    }


}
