package co.edu.uniquindio.proyecto.Dto.PatientDTOS;

import co.edu.uniquindio.proyecto.Model.Schedule;

import java.util.List;

public record ItemDoctorDTO(
        int identification,
        String name,
        List<Schedule>scheduleList


) {
}
