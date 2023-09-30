package co.edu.uniquindio.proyecto.Dto;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;

public record AttentionDTO(
        String diagnosis,
        String treatment,
        String medicalNotes) {


}
