package co.edu.uniquindio.proyecto.Dto.AppointmentDTOS;

import co.edu.uniquindio.proyecto.Model.Enum.AppointmentState;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record ItemAppointmentDTO(

        @Positive
        int code,
        @NotNull
        AppointmentState appointmentState,
        @NotNull
        LocalDateTime date

) {
}
