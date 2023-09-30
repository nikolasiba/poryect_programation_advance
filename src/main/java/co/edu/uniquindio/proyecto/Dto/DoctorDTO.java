package co.edu.uniquindio.proyecto.Dto;

import co.edu.uniquindio.proyecto.models.Enum.City;
import co.edu.uniquindio.proyecto.models.Enum.Specialization;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Email;
import lombok.NonNull;

public record DoctorDTO(

        Specialization specialization,
        String id,
        String name,
        String phone,
        String urlPicture,
        City city,
        String email,
        String password

        ) {
}
