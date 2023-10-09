package co.edu.uniquindio.proyecto.Services.Implement;

import co.edu.uniquindio.proyecto.Dto.AttentionDTO;
import co.edu.uniquindio.proyecto.Dto.FreeDayDTO;
import co.edu.uniquindio.proyecto.Dto.ItemAppointmentDTO;
import co.edu.uniquindio.proyecto.Repositorios.AppointmentRepo;
import co.edu.uniquindio.proyecto.Repositorios.AttentionRepo;
import co.edu.uniquindio.proyecto.Repositorios.DoctorRepo;
import co.edu.uniquindio.proyecto.Services.Interfaces.DoctorServices;
import co.edu.uniquindio.proyecto.models.Attention;
import co.edu.uniquindio.proyecto.models.Enum.AppointmentState;
import co.edu.uniquindio.proyecto.models.FreeDay;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServicesImpl implements DoctorServices {

    final DoctorRepo doctorRepo;
    final AppointmentRepo appointmentRepo;
    final AttentionRepo attentionRepo;

    @Override
    public String preformAttention(AttentionDTO attentionDTO) throws Exception {



        Attention attention = new Attention();
        attention.setDiagnosis(attentionDTO.diagnosis());
        attention.setTreatment(attentionDTO.treatment());
        attention.setMedicalNotes(attentionDTO.medicalNotes());

        //Attention newAttention = attentionRepo.save(attention);

        return attention.getCode();

    }

    @Override
    public String scheduleDayOff(FreeDayDTO freeDayDTO) throws Exception {
        FreeDay freeDay = new FreeDay();
        if (freeDay!=null) {
            if (){
                freeDay.setDay(freeDayDTO.day());
            }
        }
        else{
            throw new Exception("The doctor has already a day");
        }
        return null;
    }

    @Override
    public List<ItemAppointmentDTO> listFutureAppointment() {

        listFu

        return null;
    }

    @Override
    public void listFinishedAppointment(AppointmentState appointmentState) {

    }

    @Override
    public void listPendingAppointment(AppointmentState appointmentState) {

    }

    @Override
    public void patientMedicalHistory(int code) {

    }
}
