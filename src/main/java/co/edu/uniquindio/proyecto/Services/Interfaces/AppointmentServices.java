package co.edu.uniquindio.proyecto.Services.Interfaces;

import co.edu.uniquindio.proyecto.Dto.AppointmentDTOS.ItemAppointmentDTO;
import co.edu.uniquindio.proyecto.Dto.AppointmentDTOS.ItemAppointmentDoctorDTO;
import co.edu.uniquindio.proyecto.Dto.AppointmentDTOS.ItemAppointmentPatientDTO;
import co.edu.uniquindio.proyecto.Dto.AppointmentDTOS.ItemAttentionDTO;
import co.edu.uniquindio.proyecto.Dto.PatientDTOS.AppointmentStateDTO;
import co.edu.uniquindio.proyecto.Dto.PatientDTOS.CreateAppointmentPatientDTO;
import co.edu.uniquindio.proyecto.Exception.*;
import co.edu.uniquindio.proyecto.Model.Appointment;
import co.edu.uniquindio.proyecto.Model.Enum.AppointmentState;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentServices {
    int createAppointment(CreateAppointmentPatientDTO createAppointmentPatientDTO) throws Exception;
    int cancelAppointment(int code) throws Exception;
    ItemAppointmentDTO getItemAppointmentDTO(int code) throws AppointmentWasNotFoundException;
    List<ItemAppointmentDTO> listAppointment();
    List<ItemAppointmentDoctorDTO> listAppointmentDoctor() throws NotAppointmentsCreatedException;
    List<AppointmentStateDTO>filterAppointmentsByState(AppointmentState appointmentState) throws NotAppointmentsCreatedException;
    List<ItemAppointmentDTO>filterAppointmentByCodePatient(int code) throws NotAppointmentsCreatedException;
    ItemAppointmentPatientDTO showDetailsAppointment(ItemAppointmentDTO itemAppointmentDTO) throws AppointmentWasNotFoundException;

    ItemAppointmentDoctorDTO showDetailsAppointmentDoctor(ItemAppointmentDTO itemAppointmentDTO) throws AppointmentWasNotFoundException;
    List<ItemAppointmentDTO> filterAppointmentPerDoctor(String doctorName) throws NotAppointmentsCreatedException;
    ItemAppointmentDTO filterAppointmentPerDate(LocalDateTime date) throws NotAppointmentsCreatedException;
    ItemAttentionDTO attendAppointment(ItemAppointmentDoctorDTO itemAppointmentDoctorDTO) throws AttentionHasAlreadyBeenDoneException, AttentionWasNotFoundException, PatientNotExistException;




    //Es mejor hacer los servicios por rol
}
