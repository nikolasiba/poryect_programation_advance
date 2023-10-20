package co.edu.uniquindio.proyecto.Services.Implement;

import co.edu.uniquindio.proyecto.Dto.AppointmentDTO;
import co.edu.uniquindio.proyecto.Dto.Doctor.AppointmentDocDTO;
import co.edu.uniquindio.proyecto.Repository.*;
import co.edu.uniquindio.proyecto.Services.Interfaces.DoctorServices;
import co.edu.uniquindio.proyecto.Services.Interfaces.PatientServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

}
