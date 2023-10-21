package co.edu.uniquindio.proyecto.Dto.Patient;

import co.edu.uniquindio.proyecto.Model.Enum.BloodType;
import co.edu.uniquindio.proyecto.Model.Enum.City;
import co.edu.uniquindio.proyecto.Model.Enum.Eps;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PatientDTO(
        @Column(length = 30, nullable = false, unique = true)
        String name,
        @Column(nullable = false, unique = true)
        int identification,
        @Column(length = 30, nullable = false)
        String phone,
        @Lob
        @Column(nullable = false)
        String urlPicture,
        @NotNull
        City city,
        @NotNull
        LocalDate birthday,
        @Lob
        @Column(length = 200)
        String allergies,
        @NotNull
        BloodType bloodType,
        @NotNull
        Eps eps,
        @Email
        @Column(length = 50, nullable = false, unique = true)
        String email,
        @Column(length = 10, nullable = false, unique = true)
        String password

) {
}
