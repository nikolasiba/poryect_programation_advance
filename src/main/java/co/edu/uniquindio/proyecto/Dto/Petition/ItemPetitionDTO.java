package co.edu.uniquindio.proyecto.Dto.Petition;

import co.edu.uniquindio.proyecto.Model.Enum.PetitionState;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ItemPetitionDTO (

        @NotNull
        PetitionState state,
        @Lob
        @Column(length= 500, nullable = false)
        String reason,
        @NotNull
        LocalDateTime date,
        @Column(length = 30, nullable = false)
        String doctorName


){
}
