package co.edu.uniquindio.proyecto.Dto;

import co.edu.uniquindio.proyecto.Model.Enum.City;
import co.edu.uniquindio.proyecto.Model.Enum.Specialization;

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
