package co.edu.uniquindio.proyecto.Dto;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public record MessageDTO(
        @NotNull
        LocalDateTime createdDate,
        @Lob
        @Column(length = 500)
        String message,
        @Positive
        int codePetition,
        @Positive
        int codeDoctor
) {
}
