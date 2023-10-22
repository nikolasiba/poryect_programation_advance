package co.edu.uniquindio.proyecto.Dto.Patient;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ItemAppointmentPatientDTO(
        @Positive
        int patientCode,
        @Positive
        int doctorCode,

        @Positive
        int  scheduleCode,
        @Lob
        @Column(length = 500, nullable = false)
        String reason

) {
}
