package co.edu.uniquindio.proyecto.Services.Implement;

import co.edu.uniquindio.proyecto.Dto.AppointmentDTO;
import co.edu.uniquindio.proyecto.Dto.Doctor.AppointmentDocDTO;
import co.edu.uniquindio.proyecto.Dto.Patient.PatientDTO;
import co.edu.uniquindio.proyecto.Model.Appointment;
import co.edu.uniquindio.proyecto.Model.Enum.AppointmentState;
import co.edu.uniquindio.proyecto.Model.Patient;
import co.edu.uniquindio.proyecto.Repository.*;
import co.edu.uniquindio.proyecto.Services.Interfaces.DoctorServices;
import co.edu.uniquindio.proyecto.Services.Interfaces.PatientServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServicesImpl implements DoctorServices {

    final DoctorRepo doctorRepo;

    final AppointmentRepo appointmentRepo;

    final PetitionRepo petitionRepo;
    final PatientRepo patientRepo;

    final AttentionRepo attentionRepo;

    final PatientServices patientServices;


    @Override
    public List<AppointmentDocDTO> listAppointment(int docCode) throws Exception {

        List<AppointmentDocDTO> answer= getAppointmentsDoc(docCode);

        return answer;

    }

    @Override
    public List<AppointmentDocDTO> listPendingAppointments(int docCode, AppointmentState appointmentState) throws Exception {

        List<AppointmentDocDTO> answer = getAppointmentsDoc(docCode);

        for (AppointmentDocDTO item:answer) {
            if (!item.appointmentState().equals(appointmentState)){
                answer.remove(item);
            }
        }

        return answer;

    }

    private List<AppointmentDocDTO> getAppointmentsDoc(int docCode) throws Exception {

        List<Appointment>appointments=appointmentRepo.findAllByDoctorCode(docCode);

        if (appointments.isEmpty()){
            throw new Exception("There are not appointment associated with the doctor code: "+docCode);
        }
        return convert(appointments);
    }

    private List<AppointmentDocDTO> convert(List<Appointment> appointments) {
        List<AppointmentDocDTO> appointmentsDocDTO=new ArrayList<>();
        for (Appointment item:appointments) {
            AppointmentDocDTO appointmentDocDTO = new AppointmentDocDTO(
                    item.getCode(),
                    item.getAppointmentState(),
                    item.getPatient().getName(),
                    item.getPatient().getIdentification(),
                    item.getAppointmentDate());
                appointmentsDocDTO.add(appointmentDocDTO);
        }

        return appointmentsDocDTO;

    }


}
