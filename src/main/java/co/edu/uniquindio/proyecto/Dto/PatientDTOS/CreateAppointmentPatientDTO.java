package co.edu.uniquindio.proyecto.Dto.PatientDTOS;

import co.edu.uniquindio.proyecto.Model.Patient;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateAppointmentPatientDTO(

        @NotNull
        LocalDateTime appointmentDate,
        @NotNull
        String reason,
        @NotNull
        int patientCode,
        @NotNull
        int doctorCode


)  {

}
