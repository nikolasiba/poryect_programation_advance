package co.edu.uniquindio.proyecto.Dto.AppointmentDTOS;

import co.edu.uniquindio.proyecto.Model.Enum.BloodType;
import co.edu.uniquindio.proyecto.Model.Enum.Eps;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ItemAttentionDTO(

        @Positive
        int code,
        @Column(length = 30, nullable = false)
        String namePatient,
        @Positive
        int patientAge,
        @NotNull
        BloodType bloodType,
        @NotNull
        Eps eps,

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
