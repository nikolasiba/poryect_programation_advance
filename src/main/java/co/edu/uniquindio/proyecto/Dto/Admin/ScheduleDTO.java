package co.edu.uniquindio.proyecto.Dto.Admin;

import co.edu.uniquindio.proyecto.Model.Enum.Day;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record ScheduleDTO(
        @NotBlank
        @NotNull
        Day day,
        @NotBlank
        @NotNull
        LocalTime initialTime,
        @NotBlank
        @NotNull
        LocalTime finalTime

) {
}
