package co.edu.uniquindio.proyecto.Services;

import co.edu.uniquindio.proyecto.Dto.AttentionDTO;
import co.edu.uniquindio.proyecto.Dto.FreeDayDTO;
import co.edu.uniquindio.proyecto.models.Enum.AppointmentState;
import co.edu.uniquindio.proyecto.models.FreeDay;

import java.time.LocalDateTime;

public interface DoctorServices {

    //realizar atencion solo a las que tiene en el dia actual
    void preformAttention(AttentionDTO attentionDTO);

    //parametro una fecha futura, retornar una respuesta, revisar sí la fecha
    // solicitada existen citas por atender y sí ya cuenta con una.
    void scheduleDayOff(FreeDayDTO freeDay);

    //se debe retornar las citas futuras
    void listFutureAppointment();

    //recibe como parametro el estado finalizadas y las retorna
    void listFinishedAppointment(AppointmentState appointmentState);

    //recibe como parametro el estado pendientes y las retorna
    void listPendingAppointment(AppointmentState appointmentState);

    //retornar una lista del historial médico
    void patientMedicalHistory(int code);



}
