package co.edu.uniquindio.proyecto.Dto.Petition;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record AnswerDTO(

        @Positive
        int codeMessage,
        @Lob
        String message,
        @Positive
        String userName,
        @NotNull
        LocalDateTime date

) {
}
