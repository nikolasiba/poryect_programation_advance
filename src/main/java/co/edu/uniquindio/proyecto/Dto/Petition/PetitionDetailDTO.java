package co.edu.uniquindio.proyecto.Dto.Petition;

import co.edu.uniquindio.proyecto.Model.Enum.PetitionState;
import co.edu.uniquindio.proyecto.Model.Enum.Specialization;

import java.time.LocalDateTime;
import java.util.List;

public record PetitionDetailDTO(

        int code,
        PetitionState state,
        String reason,
        String patientName,
        String doctorName,
        Specialization specialization,
        LocalDateTime date,
        List<AnswerDTO> messages

) {
}
