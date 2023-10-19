package co.edu.uniquindio.proyecto.Dto.PatientDTOS;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateAppointmentPatientDTO(

        @NotNull
        LocalDateTime appointmentDate,
        @Lob
        @Column(length = 500, nullable = false)
        String reason,
        @NotNull
        int patientCode,
        @NotNull
        int doctorCode


)  {

}
