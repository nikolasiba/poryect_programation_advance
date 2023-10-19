package co.edu.uniquindio.proyecto.Dto.AppointmentDTOS;

import co.edu.uniquindio.proyecto.Model.Enum.AppointmentState;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record ItemAppointmentDoctorDTO(


        @Positive
        int code,
        @NotNull
        AppointmentState appointmentState,
        @NotNull
        LocalDateTime date,
        @Column(length = 30, nullable = false)
        String namePatient,
        @Column(nullable = false, unique = true)
        int patientIdentification


) {
}
