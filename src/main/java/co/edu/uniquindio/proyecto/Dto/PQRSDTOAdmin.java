package co.edu.uniquindio.proyecto.Dto;

import java.time.LocalDateTime;

public record PQRSDTOAdmin(

        int code,
        String state,
        LocalDateTime appointmentDate,
        String namePatient

) {
}
