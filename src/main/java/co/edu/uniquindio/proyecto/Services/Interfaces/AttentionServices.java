package co.edu.uniquindio.proyecto.Services.Interfaces;

import co.edu.uniquindio.proyecto.Dto.AppointmentDTOS.ItemAppointmentDoctorDTO;
import co.edu.uniquindio.proyecto.Dto.AttentionDTO;
import co.edu.uniquindio.proyecto.Exception.AttentionHasAlreadyBeenDoneException;
import co.edu.uniquindio.proyecto.Exception.AttentionWasNotFoundException;

public interface AttentionServices {

    int createdAttention(ItemAppointmentDoctorDTO itemAppointmentDoctorDTO) throws AttentionWasNotFoundException, AttentionHasAlreadyBeenDoneException;
    AttentionDTO getAttentionDTO(int code) throws AttentionWasNotFoundException;

}
