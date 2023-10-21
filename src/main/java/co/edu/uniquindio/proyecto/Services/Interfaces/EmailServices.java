package co.edu.uniquindio.proyecto.Services.Interfaces;


import co.edu.uniquindio.proyecto.Dto.EmailDTO;

public interface EmailServices {
    void sendEmail(EmailDTO emailDTO) throws Exception;
}
