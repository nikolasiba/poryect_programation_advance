package co.edu.uniquindio.proyecto.Dto.Patient;

import co.edu.uniquindio.proyecto.Model.Enum.ScheduleState;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record ScheduleDto(
        int code,
        LocalDate day,
        LocalTime initialTime,
        LocalTime finalTime,
        String doctorName,
        ScheduleState scheduleState,

        int doctorCode

        ) {
}
