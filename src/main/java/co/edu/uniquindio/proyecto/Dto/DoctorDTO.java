package co.edu.uniquindio.proyecto.Dto;

import co.edu.uniquindio.proyecto.models.Enum.City;
import co.edu.uniquindio.proyecto.models.Enum.Specialization;

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
