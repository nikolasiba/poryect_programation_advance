package co.edu.uniquindio.proyecto.Dto.Admin;

import co.edu.uniquindio.proyecto.Model.Enum.Specialization;

public record ItemDoctorDTO(

        int identification,
        String name,
        String urlPicture,
        Specialization specialization

) {
}
