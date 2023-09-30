package co.edu.uniquindio.proyecto.Dto;

import co.edu.uniquindio.proyecto.models.Enum.Specialization;

public record InfoDoctorDTO(
        Specialization specialization,
        String id,
        String name,
        String phone
) {
}
