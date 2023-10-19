package co.edu.uniquindio.proyecto.Dto;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Positive;

public record AttentionDTO(

        @Positive
        int code,
        @Positive
        int codeAppointment,
        @Lob
        @Column(length = 300)
        String diagnosis,
        @Lob
        @Column(length = 300)
        String treatment,
        @Lob
        @Column(length = 300)
        String medicalNotes

    ) {

}
