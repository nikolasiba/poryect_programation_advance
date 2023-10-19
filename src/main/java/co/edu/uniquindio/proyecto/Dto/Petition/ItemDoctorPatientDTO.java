package co.edu.uniquindio.proyecto.Dto.Petition;

import co.edu.uniquindio.proyecto.Model.Enum.Specialization;
import co.edu.uniquindio.proyecto.Model.Schedule;

import java.util.List;

public record ItemDoctorPatientDTO(
        int identification,
        String doctorName,
        List<Schedule> schedules
) {
}
