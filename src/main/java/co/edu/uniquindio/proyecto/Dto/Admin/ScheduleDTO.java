package co.edu.uniquindio.proyecto.Dto.Admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record ScheduleDTO(
        @NotBlank
        @NotNull
        LocalDate day,
        @NotBlank
        @NotNull
        LocalTime initialTime,
        @NotBlank
        @NotNull
        LocalTime finalTime

) {
}
