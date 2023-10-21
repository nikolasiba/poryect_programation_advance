package co.edu.uniquindio.proyecto.Dto.Petition;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record RespLogDTO (

        @Positive
        int accountCode,
        @Positive
        int petitionCode,
        @Positive
        int messageCode,
        @NotBlank
        String message

) {
}
