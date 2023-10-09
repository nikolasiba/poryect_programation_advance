package co.edu.uniquindio.proyecto.Services.Interfaces;

import co.edu.uniquindio.proyecto.Dto.*;

import java.util.List;

public interface AdminServices {

    String createMedico(DoctorDTO doctor) throws Exception;

    String updateMedico(int code, DoctorDTO doctor) throws Exception;

    String deleteMedico(int code) throws Exception;

    List<DoctorDTOAdmin> listDoctor() throws Exception;

    InfoDoctorDTO getDoctor(int code) throws Exception;

    List<PQRSDTOAdmin> listPQRS() throws Exception;

    String responderPQRS(int codigo) throws Exception;

    InfoPQRSDTO verDetallePQRS(int codigo) throws Exception;

    List<AppointmentDTOAdmin> listarCitas() throws Exception;
}
