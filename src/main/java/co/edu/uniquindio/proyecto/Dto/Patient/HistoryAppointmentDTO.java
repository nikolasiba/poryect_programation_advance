package co.edu.uniquindio.proyecto.Dto.Patient;

import co.edu.uniquindio.proyecto.Model.Enum.AppointmentState;
import co.edu.uniquindio.proyecto.Model.Enum.Specialization;

import java.time.LocalDateTime;

public record HistoryAppointmentDTO(

        int appointmentCode,
        LocalDateTime day,
        Specialization specialization,
        String doctorName,
        AppointmentState appointmentState

) {
}
