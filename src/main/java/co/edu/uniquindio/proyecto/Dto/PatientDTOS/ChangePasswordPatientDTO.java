package co.edu.uniquindio.proyecto.Dto.PatientDTOS;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;

public record ChangePasswordPatientDTO (
        @Email
        @Column(length = 50, nullable = false, unique = true)
        String email,
        @Column(length = 10, nullable = false, unique = true)
        String password
){
}
