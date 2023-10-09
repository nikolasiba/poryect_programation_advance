package co.edu.uniquindio.proyecto.Dto.PatientDTOS;

import co.edu.uniquindio.proyecto.models.Enum.BloodType;
import co.edu.uniquindio.proyecto.models.Enum.City;
import co.edu.uniquindio.proyecto.models.Enum.Eps;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;

import java.time.LocalDate;

public record EditedPatientDTO(

        @Column(length = 30, nullable = false, unique = true)
        String name,
        @Column(length = 30, nullable = false, unique = true)
        String id,
        @Column(length = 30, nullable = false)
        String phone,
        @Lob
        @Column(nullable = false)
        String urlPicture,
        City city,
        LocalDate birthday,
        @Lob
        @Column(length = 200)
        String allergies,
        BloodType bloodType,
        Eps eps

    ) {

}
