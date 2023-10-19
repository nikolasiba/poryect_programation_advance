package co.edu.uniquindio.proyecto.Dto.Petition;

import co.edu.uniquindio.proyecto.Model.Enum.PetitionState;
import co.edu.uniquindio.proyecto.Repository.PetitionRepo;

import java.time.LocalDateTime;

public record ItemPetitionDTO (

        PetitionState state,
        String reason,
        LocalDateTime date,
        String patientName

){
}
