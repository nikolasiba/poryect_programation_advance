package co.edu.uniquindio.proyecto.Dto.PatientDTOS;

import java.time.LocalDateTime;

public record CreateAppointmentPatientDTO(

        LocalDateTime appointmentDate,
        String doctor,
        String reason


) {
}
