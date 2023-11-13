
package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.Dto.LoginDTO;
import co.edu.uniquindio.proyecto.Dto.TokenDTO;
import co.edu.uniquindio.proyecto.Services.Interfaces.AuthenticationService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@Sql(value = "/dataset.sql")
@Transactional
@SpringBootTest
public class AuthenticationTest {

    @Autowired
    private  AuthenticationService authenticationService;

    @Test
    public void login() {
        try {
            LoginDTO loginDTO = new LoginDTO("nikolasiba23@gmail.com", "123a");
            TokenDTO tokenDTO = authenticationService.login(loginDTO);
            System.out.println(tokenDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
