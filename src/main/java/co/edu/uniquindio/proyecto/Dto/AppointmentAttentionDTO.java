package co.edu.uniquindio.proyecto.Dto;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Positive;

public record AppointmentAttentionDTO(
        @Positive
        int appointmentCode,
        @Lob
        @Column(length = 300)
        String medicalNotes,
        @Lob
        @Column(length = 300)
        String treatment,
        @Lob
        @Column(length = 300)
        String diagnosis



) {
}
