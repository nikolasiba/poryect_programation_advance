package co.edu.uniquindio.proyecto.Services.Interfaces;

import co.edu.uniquindio.proyecto.Dto.LoginDTO;
import co.edu.uniquindio.proyecto.Dto.TokenDTO;

public interface AuthenticationService {

    TokenDTO login(LoginDTO loginDto ) throws Exception;
}
