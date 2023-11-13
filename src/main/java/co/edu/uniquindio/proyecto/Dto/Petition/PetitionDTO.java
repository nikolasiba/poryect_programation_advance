package co.edu.uniquindio.proyecto.Dto.Petition;

import co.edu.uniquindio.proyecto.Model.Enum.TypePetition;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PetitionDTO (
        @Positive
        int codeAppointment,
        @NotNull
        @Lob
        String reason,
        @NotNull
        TypePetition typePetition,
        @Positive
        int patientCode,
        @Lob
        @Column(length= 500)
        String message


) {
}
