package co.edu.uniquindio.proyecto.Dto.PatientDTOS;

import co.edu.uniquindio.proyecto.Model.Patient;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateAppointmentPatientDTO(

        @NotNull
        LocalDateTime appointmentDate,
        @Lob
        @NotNull
        @Column(length = 500)
        String reason,
        @NotNull
        int patientCode,
        @NotNull
        int doctorCode


)  {

}
