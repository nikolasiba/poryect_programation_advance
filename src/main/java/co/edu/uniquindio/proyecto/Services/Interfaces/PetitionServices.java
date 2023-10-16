package co.edu.uniquindio.proyecto.Services.Interfaces;

import co.edu.uniquindio.proyecto.Dto.MessageDTO;
import co.edu.uniquindio.proyecto.Dto.PatientDTOS.ItemPetitionDTO;
import co.edu.uniquindio.proyecto.Exception.AppointmentNotRelatedException;
import co.edu.uniquindio.proyecto.Exception.PetitionNotFoundException;
import co.edu.uniquindio.proyecto.Model.Enum.PetitionState;

public interface PetitionServices {

    int createPetition(ItemPetitionDTO itemPetitionDTO) throws AppointmentNotRelatedException;
    int cancelPetition(int code) throws PetitionNotFoundException;
    int changeStatePetition(PetitionState petitionState, int code) throws PetitionNotFoundException;
    void answerPetition(MessageDTO messageDTO);
    void listPetitionPatient();
    void showDetailsPetition();
}
