package co.edu.uniquindio.proyecto.Dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record DayOffDTO(
        @NotEmpty
        @NotNull
        LocalDateTime day,
        @Positive
        int doctorCode

        ) {

}
