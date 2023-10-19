package co.edu.uniquindio.proyecto.Dto;

import co.edu.uniquindio.proyecto.Model.Enum.Specialization;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record AppointmentDTO(

        @Positive
        int code,
        @NotNull
        LocalDateTime date,
        @Column(length = 30, nullable = false)
        String doctorName,
        @NotNull
        Specialization specialization


) {
}
