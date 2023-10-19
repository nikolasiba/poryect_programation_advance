package co.edu.uniquindio.proyecto.Dto.Admin;

import co.edu.uniquindio.proyecto.Model.Enum.AppointmentState;
import co.edu.uniquindio.proyecto.Model.Enum.Specialization;

import java.time.LocalDateTime;

public record ItemAppointmentAdminDTO(

        int AppointmentCode,
        int patientId,
        String patientName,
        String doctorName,
        Specialization specialization,
        AppointmentState appointmentState,
        LocalDateTime date

) {
}
