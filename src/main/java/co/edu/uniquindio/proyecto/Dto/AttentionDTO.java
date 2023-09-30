package co.edu.uniquindio.proyecto.Dto;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;

public class AttentionDTO {
    @Lob
    @Column(length = 300,nullable = false)
    private String diagnosis;

    @Lob
    @Column(length = 300, nullable = false)
    private String treatment;

    @Lob
    @Column(length = 300, nullable = false)
    private String medicalNotes;

}
