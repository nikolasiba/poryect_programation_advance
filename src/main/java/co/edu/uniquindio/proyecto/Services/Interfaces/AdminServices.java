package co.edu.uniquindio.proyecto.Services.Interfaces;

import co.edu.uniquindio.proyecto.Exception.PatientNotExistException;
import co.edu.uniquindio.proyecto.Model.Enum.PatientState;

public interface AdminServices {

    void penalizePatient(int code) throws PatientNotExistException;

}
