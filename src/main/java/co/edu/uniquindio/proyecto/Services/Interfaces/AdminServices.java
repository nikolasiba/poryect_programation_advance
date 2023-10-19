package co.edu.uniquindio.proyecto.Services.Interfaces;

import co.edu.uniquindio.proyecto.Dto.Admin.DoctorDetailDTO;
import co.edu.uniquindio.proyecto.Dto.Admin.DoctorRecordDTO;
import co.edu.uniquindio.proyecto.Dto.Admin.ItemDoctorDTO;
import co.edu.uniquindio.proyecto.Dto.Admin.ItemAppointmentAdminDTO;
import co.edu.uniquindio.proyecto.Dto.Petition.ItemPetitionDTO;
import co.edu.uniquindio.proyecto.Dto.Petition.PetitionDetailDTO;
import co.edu.uniquindio.proyecto.Dto.Petition.RespLogDTO;
import co.edu.uniquindio.proyecto.Exception.AppointmentException.AccountNotFoundException;
import co.edu.uniquindio.proyecto.Exception.AppointmentException.AppointmentsNotFoundException;
import co.edu.uniquindio.proyecto.Exception.DoctorExceptions.DoctorNotFoundException;
import co.edu.uniquindio.proyecto.Exception.DoctorExceptions.DoctorWithEmailRepeatedException;
import co.edu.uniquindio.proyecto.Exception.DoctorExceptions.DoctorWithIdRepeatedException;
import co.edu.uniquindio.proyecto.Exception.DoctorExceptions.DoctorsNotFoundException;
import co.edu.uniquindio.proyecto.Exception.PetitionExceptions.PetitionNotFoundException;
import co.edu.uniquindio.proyecto.Exception.PetitionExceptions.PetitionsNotFoundException;
import co.edu.uniquindio.proyecto.Model.Enum.PetitionState;

import java.util.List;

public interface AdminServices {

    int createDoctor(DoctorRecordDTO doctorRecordDTO) throws DoctorWithIdRepeatedException, DoctorWithEmailRepeatedException;
    int updateDoctor(DoctorDetailDTO doctorDTO) throws DoctorNotFoundException;
    void deleteDoctor(int code) throws DoctorNotFoundException;
    List<ItemDoctorDTO> ListDoctors() throws DoctorsNotFoundException;
    DoctorDetailDTO getDoctor(int code) throws DoctorNotFoundException;
    List<ItemPetitionDTO> listPetition() throws PetitionsNotFoundException;
    PetitionDetailDTO showDetailPetition(int code) throws PetitionNotFoundException;
    int respondPetition(RespLogDTO respLogDTO) throws PetitionNotFoundException, AccountNotFoundException;
    void changePetitionState(int petitionCode, PetitionState petitionState) throws PetitionNotFoundException;
    List<ItemAppointmentAdminDTO> listAppointment() throws AppointmentsNotFoundException;



}
