package co.edu.uniquindio.proyecto.Dto;

import co.edu.uniquindio.proyecto.Model.Schedule;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record EmailDTO(

        @Email
        @NotNull
        String addressee,
        @NotNull
        @Column(length = 300)
        String affair,
        @Lob
        @NotNull
        String body

) {
}
