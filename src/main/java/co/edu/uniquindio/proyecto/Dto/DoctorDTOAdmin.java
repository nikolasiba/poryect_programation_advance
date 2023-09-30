package co.edu.uniquindio.proyecto.Dto;

import co.edu.uniquindio.proyecto.models.Enum.Specialization;

public record DoctorDTOAdmin(

        String id,
        String name,
        Specialization specialization,
        String phone

        ) {
}
