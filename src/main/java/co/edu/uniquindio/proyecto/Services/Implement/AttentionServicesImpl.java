package co.edu.uniquindio.proyecto.Services.Implement;

import co.edu.uniquindio.proyecto.Dto.AppointmentDTOS.ItemAppointmentDoctorDTO;
import co.edu.uniquindio.proyecto.Dto.AttentionDTO;
import co.edu.uniquindio.proyecto.Exception.AttentionHasAlreadyBeenDoneException;
import co.edu.uniquindio.proyecto.Exception.AttentionWasNotFoundException;
import co.edu.uniquindio.proyecto.Model.Attention;
import co.edu.uniquindio.proyecto.Repository.AttentionRepo;
import co.edu.uniquindio.proyecto.Services.Interfaces.AttentionServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttentionServicesImpl implements AttentionServices {

    final AttentionRepo attentionRepo;

    @Override
    public int createdAttention(ItemAppointmentDoctorDTO itemAppointmentDoctorDTO)
            throws AttentionWasNotFoundException, AttentionHasAlreadyBeenDoneException {

        validate(itemAppointmentDoctorDTO.code());
        Attention attention = getAttention(itemAppointmentDoctorDTO.code());
        attentionRepo.save(attention);

        return attention.getCode();

    }

    @Override
    public AttentionDTO getAttentionDTO(int code) throws AttentionWasNotFoundException {
        Attention a = getAttention(code);
        return convert(a);
    }

    private void validate(int code) throws AttentionHasAlreadyBeenDoneException, AttentionWasNotFoundException {

        Optional<Attention>optional=attentionRepo.findById(code);

        if(optional.isPresent()){
            throw new AttentionHasAlreadyBeenDoneException("Attention with the code "+code+ " has been done");
        }

    }

    private AttentionDTO convert(Attention attention) {
        return new AttentionDTO(
                attention.getCode(),
                attention.getAppointment().getCode(),
                attention.getDiagnosis(),
                attention.getTreatment(),
                attention.getMedicalNotes() );

    }

    private Attention getAttention(int code) throws AttentionWasNotFoundException {

        Optional<Attention>optional=attentionRepo.findById(code);

        if (optional.isEmpty()){
            throw new AttentionWasNotFoundException("Attention with the code "+code+" was not found");
        }

        return optional.get();

    }




}
