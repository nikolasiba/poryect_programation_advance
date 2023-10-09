package co.edu.uniquindio.proyecto.Services.Interfaces;

import co.edu.uniquindio.proyecto.Dto.EmailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


public interface EmailServices {
    void sendEmail(EmailDTO emailDTO) throws Exception;
}
