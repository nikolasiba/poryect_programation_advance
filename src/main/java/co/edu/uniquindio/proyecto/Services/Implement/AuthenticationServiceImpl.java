package co.edu.uniquindio.proyecto.Services.Implement;

import co.edu.uniquindio.proyecto.Dto.LoginDTO;
import co.edu.uniquindio.proyecto.Dto.TokenDTO;
import co.edu.uniquindio.proyecto.Model.Account;
import co.edu.uniquindio.proyecto.Model.Doctor;
import co.edu.uniquindio.proyecto.Model.Patient;
import co.edu.uniquindio.proyecto.Repository.AccountRepo;
import co.edu.uniquindio.proyecto.Services.Interfaces.AuthenticationService;
import co.edu.uniquindio.proyecto.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
@RequiredArgsConstructor

public class AuthenticationServiceImpl implements AuthenticationService {

    private final AccountRepo accountRepo;
    private final JWTUtils jwtUtils;


    @Override
    public TokenDTO login(LoginDTO loginDTO) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


        Optional<Account> optionalAccount = accountRepo.findByEmail(loginDTO.email());
        if (optionalAccount.isEmpty()) {
            throw new Exception("No existe el correo ingresado");
        }
        Account account = optionalAccount.get();

        if (!passwordEncoder.matches(loginDTO.password(), account.getPassword())
        ) {
            throw new Exception("La contraseña ingresada es incorrecta");
        }
        return new TokenDTO(crearToken(account));
    }

    private String crearToken(Account account) {
        String rol;
        String name;
        if (account instanceof Patient) {
            rol = "Patient";
            name = ((Patient) account).getName();
        } else if (account instanceof Doctor) {
            rol = "medico";
            name = ((Doctor) account).getName();
        } else {
            rol = "admin";
            name = "Administrator";
        }
        Map<String, Object> map = new HashMap<>();
        map.put("rol", rol);
        map.put("name", name);
        map.put("id", account.getCode());
        return jwtUtils.generateToken(account.getEmail(), map);
    }
}
