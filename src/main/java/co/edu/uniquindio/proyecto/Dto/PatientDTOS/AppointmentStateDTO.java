package co.edu.uniquindio.proyecto.Dto.PatientDTOS;

import co.edu.uniquindio.proyecto.Model.Enum.AppointmentState;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record AppointmentStateDTO(
        @NotNull
        AppointmentState appointmentState,
        @Positive
        int code,
        @NotNull
        LocalDateTime appointmentDate

) {
}
