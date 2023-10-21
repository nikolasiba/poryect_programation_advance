package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.Dto.EmailDTO;
import co.edu.uniquindio.proyecto.Services.Interfaces.EmailServices;
import co.edu.uniquindio.proyecto.Services.Interfaces.PatientServices;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@Transactional
@SpringBootTest
@Sql(value = "/dataset.sql")
public class EmailTest {

    @Autowired
    private EmailServices emailServices;



    @Test
    public  void sendEmail(){
        EmailDTO emailDTO =  new EmailDTO(
                "nikolasiba23@gmail.com",
                "Bienvenido",
                "hola nicolas creaste tu cuenta "

        );
        try {
            emailServices.sendEmail(emailDTO);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
