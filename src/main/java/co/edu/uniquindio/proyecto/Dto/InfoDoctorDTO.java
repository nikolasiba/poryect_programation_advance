package co.edu.uniquindio.proyecto.Dto;

import co.edu.uniquindio.proyecto.Model.Enum.Specialization;

public record InfoDoctorDTO(
        Specialization specialization,
        String id,
        String name,
        String phone
) {
}
