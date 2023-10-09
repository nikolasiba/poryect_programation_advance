package co.edu.uniquindio.proyecto.Dto;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record EmailDTO(
        @Email
        @NotNull
        String addressee,
        @Lob
        @NotNull
        String bady,
        @NotNull
        @Column(length = 300)
        String affair
) {
}
