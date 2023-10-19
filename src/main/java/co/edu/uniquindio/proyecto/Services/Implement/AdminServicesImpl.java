package co.edu.uniquindio.proyecto.Services.Implement;

import co.edu.uniquindio.proyecto.Exception.PatientNotExistException;
import co.edu.uniquindio.proyecto.Model.Enum.PatientState;
import co.edu.uniquindio.proyecto.Model.Patient;
import co.edu.uniquindio.proyecto.Repository.PatientRepo;
import co.edu.uniquindio.proyecto.Services.Interfaces.AdminServices;
import co.edu.uniquindio.proyecto.Services.Interfaces.PatientServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServicesImpl implements AdminServices {

    final PatientServices patientServices;
    final PatientRepo patientRepo;

    @Override
    public void penalizePatient(int code) throws PatientNotExistException {

        int num = patientServices.listCodePatientAppointments(code).size();

        if(num >= 3){

            Patient penalizedPatient = patientServices.filterPatientByCode(code);
            penalizedPatient.setPatientState(PatientState.INVALIDATED);
            patientRepo.save(penalizedPatient);

        }
    }



}
