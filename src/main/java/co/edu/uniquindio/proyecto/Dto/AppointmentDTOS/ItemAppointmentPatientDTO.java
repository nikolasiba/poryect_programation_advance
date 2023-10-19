package co.edu.uniquindio.proyecto.Dto.AppointmentDTOS;

import co.edu.uniquindio.proyecto.Model.Enum.AppointmentState;
import co.edu.uniquindio.proyecto.Model.Enum.Specialization;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record ItemAppointmentPatientDTO(

        @Positive
        int code,
        @NotNull
        AppointmentState appointmentState,
        @NotNull
        LocalDateTime date,
        @Column(length = 30, nullable = false)
        String nameDoctor,
        @NotNull
        Specialization specialization



) {
}
