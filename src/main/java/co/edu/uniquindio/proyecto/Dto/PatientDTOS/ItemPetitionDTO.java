package co.edu.uniquindio.proyecto.Dto.PatientDTOS;

import co.edu.uniquindio.proyecto.Model.Enum.PetitionState;
import co.edu.uniquindio.proyecto.Model.Enum.TypePetition;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ItemPetitionDTO(

        @Positive
        int codeAppointment,
        @Column(length = 500, nullable = false)
        String reason,
        @NotNull
        TypePetition typePetition

) {
}
