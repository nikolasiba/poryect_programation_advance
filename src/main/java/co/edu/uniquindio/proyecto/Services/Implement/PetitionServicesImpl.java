package co.edu.uniquindio.proyecto.Services.Implement;

import co.edu.uniquindio.proyecto.Dto.MessageDTO;
import co.edu.uniquindio.proyecto.Dto.PatientDTOS.ItemPetitionDTO;
import co.edu.uniquindio.proyecto.Exception.AppointmentNotRelatedException;
import co.edu.uniquindio.proyecto.Exception.PetitionNotFoundException;
import co.edu.uniquindio.proyecto.Model.Appointment;
import co.edu.uniquindio.proyecto.Model.Enum.PetitionState;
import co.edu.uniquindio.proyecto.Model.Message;
import co.edu.uniquindio.proyecto.Model.Petition;
import co.edu.uniquindio.proyecto.Repository.AppointmentRepo;
import co.edu.uniquindio.proyecto.Repository.PetitionRepo;
import co.edu.uniquindio.proyecto.Services.Interfaces.PetitionServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PetitionServicesImpl implements PetitionServices {

    final PetitionRepo petitionRepo;
    final AppointmentRepo appointmentRepo;

    @Override
    public int createPetition(ItemPetitionDTO itemPetitionDTO) throws AppointmentNotRelatedException {

        Appointment appointment = validateAppointment(itemPetitionDTO.codeAppointment());

        Petition petition = new Petition();

        petition.setCreatedDate(LocalDateTime.now());
        petition.setReason(itemPetitionDTO.reason());
        petition.setTypePetition(itemPetitionDTO.typePetition());
        petition.setPetitionState(PetitionState.PENDING);

        petition.setAppointment(appointment);

        Petition newPetition = petitionRepo.save(petition);

        return newPetition.getCode();

    }

    @Override
    public int cancelPetition(int code) throws PetitionNotFoundException {

        Petition petition = validatePetition(code);

        petition.setPetitionState(PetitionState.CANCELLED);

        petitionRepo.save(petition);

        return petition.getCode();

    }
    private Petition validatePetition(int code) throws PetitionNotFoundException {

        Optional<Petition> optional = petitionRepo.findById(code);

        if (optional.isEmpty()){
            throw new PetitionNotFoundException("Petition with the code "+code+" wasn´t found");
        }

        return optional.get();

    }

    private Appointment validateAppointment(int code) throws AppointmentNotRelatedException {

        Optional<Appointment> optional = appointmentRepo.findById(code);

        if (optional.isEmpty()){
            throw new AppointmentNotRelatedException("Petition isn´t related with the apponintment code: "+ code);
        }

        return optional.get();

    }

    @Override
    public int changeStatePetition(PetitionState petitionState, int code) throws PetitionNotFoundException {

        Petition petition = validatePetition(code);

        petition.setPetitionState(petitionState);

        petitionRepo.save(petition);

        return petition.getCode();

    }

    @Override
    public void answerPetition(MessageDTO messageDTO) {
        Petition petition = new Petition();
        petition.setCreatedDate(LocalDateTime.now());
    }

    @Override
    public void listPetitionPatient() {

    }

    @Override
    public void showDetailsPetition() {

    }
}
