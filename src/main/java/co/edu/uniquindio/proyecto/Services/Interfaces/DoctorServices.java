package co.edu.uniquindio.proyecto.Services.Interfaces;

import co.edu.uniquindio.proyecto.Dto.AppointmentDTOS.ItemAppointmentDoctorDTO;
import co.edu.uniquindio.proyecto.Dto.AttentionDTO;
import co.edu.uniquindio.proyecto.Dto.FreeDayDTO;
import co.edu.uniquindio.proyecto.Model.Enum.AppointmentState;

import java.util.List;

public interface DoctorServices {

    //realizar atencion solo a las que tiene en el dia actual
    int preformAttention(AttentionDTO attentionDTO)throws Exception;

    //parametro una fecha futura, retornar una respuesta, revisar sí la fecha
    // solicitada existen citas por atender y sí ya cuenta con una.
    String scheduleDayOff(FreeDayDTO freeDayDTO) throws Exception;

    //se debe retornar las citas futuras
    List<ItemAppointmentDoctorDTO> listFutureAppointment();

    //recibe como parametro el estado finalizadas y las retorna
    void listFinishedAppointment(AppointmentState appointmentState);

    //recibe como parametro el estado pendientes y las retorna
    void listPendingAppointment(AppointmentState appointmentState);

    //retornar una lista del historial médico
    void patientMedicalHistory(int code);



}
