package co.edu.uniquindio.proyecto.Services;

import co.edu.uniquindio.proyecto.Dto.*;

import java.util.List;

public interface AdminServices {
    String crearMedico(DoctorDTO doctor) throws Exception;

    String actualizarMedico(int codigo, DoctorDTO doctor) throws Exception;

    String eliminarMedico(int codigo) throws Exception;

    List<DoctorDTOAdmin> listarMedicos() throws Exception;

    InfoDoctorDTO obtenerMedico(int codigo) throws Exception;

    List<PQRSDTOAdmin> listarPQRS() throws Exception;

    String responderPQRS(int codigo) throws Exception;

    InfoPQRSDTO verDetallePQRS(int codigo) throws Exception;

    List<AppointmentDTOAdmin> listarCitas() throws Exception;
}
