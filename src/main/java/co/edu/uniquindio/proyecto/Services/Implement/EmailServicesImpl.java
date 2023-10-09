package co.edu.uniquindio.proyecto.Services.Implement;

import co.edu.uniquindio.proyecto.Dto.EmailDTO;
import co.edu.uniquindio.proyecto.Services.Interfaces.EmailServices;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServicesImpl implements EmailServices {

    private final JavaMailSender javaMailSender;

        /*
        DONDE SE REQUIERA ENVIAR EL CORREO PONEMOS LO SIGUIENTE

        Inicializar el servicio EmailServices y llamar el metodo con los argumentos.
        emailServices.sendEmail(new EmailDTO("Affair message title", "bady message", "Email adressee"));

     */

    @Override
    public void sendEmail(EmailDTO emailDTO) throws Exception {

        MimeMessage mensaje = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje);

        helper.setSubject(emailDTO.affair());
        helper.setText(emailDTO.bady(), true);
        helper.setTo(emailDTO.addressee());
        helper.setFrom("no_reply@dominio.com");

        javaMailSender.send(mensaje);

    }






}
