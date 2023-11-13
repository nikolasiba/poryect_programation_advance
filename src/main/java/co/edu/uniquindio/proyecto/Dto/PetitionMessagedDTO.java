package co.edu.uniquindio.proyecto.Dto;

import co.edu.uniquindio.proyecto.Model.Enum.TypePetition;
import co.edu.uniquindio.proyecto.Model.Message;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record PetitionMessagedDTO(

        @Positive
        int codeAppointment,
        @NotNull
        @Lob
        String reason,
        @NotNull
        TypePetition typePetition,
        @Positive
        int patientCode,
        @NotNull
        List<Message> message
) {
}
