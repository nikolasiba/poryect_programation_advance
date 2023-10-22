package co.edu.uniquindio.proyecto.Dto.Doctor;

import co.edu.uniquindio.proyecto.Model.Enum.AppointmentState;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record AppointmentDocDTO(
        @Positive
        int codeAppointment,
        @NotNull
        AppointmentState appointmentState,
        @Column(length = 30, nullable = false)
        String patientName,
        @Positive
        int patientId,
        @NotNull
        LocalDateTime localDateTime
) {
}
