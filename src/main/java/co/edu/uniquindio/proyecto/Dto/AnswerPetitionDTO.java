package co.edu.uniquindio.proyecto.Dto;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Positive;

public record AnswerPetitionDTO(

        @Positive
        int petitionCode,

        @Positive
        int patientCode,

        @Lob
        @Column(length = 500)
        String message


) {
}
