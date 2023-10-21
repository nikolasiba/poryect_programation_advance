package co.edu.uniquindio.proyecto.Dto.Patient;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;

public record ItemPatientPwdDTO(
        @Email
        @Column(length = 50, nullable = false, unique = true)
        String email,
        @Column(length = 10, nullable = false, unique = true)
        String password,
        @Column(length = 10, nullable = false)
        String repeatPassword
) {
}
