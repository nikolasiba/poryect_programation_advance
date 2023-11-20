package co.edu.uniquindio.proyecto.Dto.Patient;

import co.edu.uniquindio.proyecto.Model.Enum.Eps;
import co.edu.uniquindio.proyecto.Model.Enum.Specialization;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record PatientAppointmentDTO(

        int appointmentCode,
        LocalDateTime day,
        LocalTime initialTime,
        LocalTime finalTime,
        Eps eps,
        Specialization specialization,
        String doctorName


) {
}
