package co.edu.uniquindio.proyecto.Dto;

import co.edu.uniquindio.proyecto.Model.Enum.Specialization;

public record DoctorDTOAdmin(

        String id,
        String name,
        Specialization specialization,
        String phone

        ) {
}
