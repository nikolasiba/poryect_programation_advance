package co.edu.uniquindio.proyecto.Services.Interfaces;


import co.edu.uniquindio.proyecto.Dto.AppointmentDTO;
import co.edu.uniquindio.proyecto.Dto.ItemAttentionDTO;
import co.edu.uniquindio.proyecto.Dto.Patient.EditedPatientDTO;
import co.edu.uniquindio.proyecto.Dto.Patient.ItemAppointmentPatientDTO;
import co.edu.uniquindio.proyecto.Dto.Petition.ItemDoctorPatientDTO;
import co.edu.uniquindio.proyecto.Dto.Patient.ItemPatientPwdDTO;
import co.edu.uniquindio.proyecto.Dto.Patient.PatientDTO;
import co.edu.uniquindio.proyecto.Dto.Petition.PetitionDTO;
import co.edu.uniquindio.proyecto.Exception.AppointmentException.AccountNotFoundException;
import co.edu.uniquindio.proyecto.Exception.AppointmentException.AppointmentsNotFoundException;
import co.edu.uniquindio.proyecto.Exception.AttentionNotAssociatedAppointmentException;
import co.edu.uniquindio.proyecto.Exception.AttentionNotFoundException;
import co.edu.uniquindio.proyecto.Exception.DoctorExceptions.AppointmentNotFoundException;
import co.edu.uniquindio.proyecto.Exception.DoctorExceptions.DoctorsNotFoundException;
import co.edu.uniquindio.proyecto.Exception.PatientException.PatientNotFoundException;
import co.edu.uniquindio.proyecto.Model.Enum.AppointmentState;
import co.edu.uniquindio.proyecto.Model.Enum.DoctorState;
import co.edu.uniquindio.proyecto.Model.Enum.Specialization;

import java.util.List;

public interface PatientServices {

    int sigIn(PatientDTO patientDTO) throws Exception;
    int editAccount(EditedPatientDTO editedPatientDTO) throws PatientNotFoundException;
    PatientDTO getPatientByIdentification(int identification) throws PatientNotFoundException;
    void deleteAccount(int code) throws Exception;
    void sendLink(String email) throws Exception;
    int changePassword(ItemPatientPwdDTO itemPatientPwdDTO) throws Exception;
    List<ItemDoctorPatientDTO> checkAvailability(Specialization specialization, DoctorState doctorState)
            throws DoctorsNotFoundException;
    AppointmentDTO createAppointment(ItemAppointmentPatientDTO itemAppointmentPatientDTO) throws Exception;
    int cancelAppointment(int code) throws Exception;
    List<AppointmentDTO> listAppointment() throws AppointmentNotFoundException, AppointmentsNotFoundException;
    List<AppointmentDTO> filterAppointmentsByState(AppointmentState appointmentState) throws AppointmentsNotFoundException;
    ItemAttentionDTO showDetailsAppointment(int code)
            throws AppointmentNotFoundException, AttentionNotFoundException;
    int createPetition(PetitionDTO petitionDTO) throws AppointmentNotFoundException,
            AttentionNotAssociatedAppointmentException, AccountNotFoundException;
}
