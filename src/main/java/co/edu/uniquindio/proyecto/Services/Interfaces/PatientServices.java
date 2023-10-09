package co.edu.uniquindio.proyecto.Services.Interfaces;

import co.edu.uniquindio.proyecto.Dto.PatientDTOS.ChangePasswordPatientDTO;
import co.edu.uniquindio.proyecto.Dto.PatientDTOS.CreateAppointmentPatientDTO;
import co.edu.uniquindio.proyecto.Dto.PatientDTOS.EditedPatientDTO;
import co.edu.uniquindio.proyecto.Dto.PatientDTOS.PatientDTO;
import co.edu.uniquindio.proyecto.models.Enum.Specialization;

public interface PatientServices {

    int sigIn(PatientDTO patientDTO);

    PatientDTO editAccount(EditedPatientDTO editedPatientDTO);

    String deleteAccount(String id);

    //consultar que retorno darle a este metodo
    void enviarLinkRecuperacion();

    //consultar que retorno darle a este metodo
    String changePassword(ChangePasswordPatientDTO changePasswordPatientDTO);
    
    String checkAvailability(Specialization specialization);
    String createAppointment(CreateAppointmentPatientDTO createAppointmentPatientDTO);
    void crearPQRS();

    void listarPQRSPaciente();

    void responderPQRS();

    void listarCitasPaciente();

    void filtrarCitasPorFecha();

    void filtrarCitasPorMedico();

    void verDetalleCita();


}
