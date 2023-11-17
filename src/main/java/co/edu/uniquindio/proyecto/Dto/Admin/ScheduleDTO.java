package co.edu.uniquindio.proyecto.Dto.Admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record ScheduleDTO(
        @NotNull
        LocalDate day,
        @NotNull
        LocalTime initialTime,

        @NotNull
        LocalTime finalTime

) {
}
