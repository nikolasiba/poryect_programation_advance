package co.edu.uniquindio.proyecto.Services.Implement;

import co.edu.uniquindio.proyecto.Dto.LoginDTO;
import co.edu.uniquindio.proyecto.Dto.TokenDTO;
import co.edu.uniquindio.proyecto.Model.Account;
import co.edu.uniquindio.proyecto.Model.Doctor;
import co.edu.uniquindio.proyecto.Model.Patient;
import co.edu.uniquindio.proyecto.Repository.AccountRepo;
import co.edu.uniquindio.proyecto.Services.Interfaces.AuthenticationService;
import co.edu.uniquindio.proyecto.Utils.JWTUtils;
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
            throw new Exception("the email you entered does not exist");
        }
        Account account = optionalAccount.get();

        if (!passwordEncoder.matches(loginDTO.password(), account.getPassword())
        ) {
            throw new Exception("the password that you entered was incorrect");
        }
        return new TokenDTO(createToke(account));
    }

    private String createToke(Account account) {
        String rol;
        String name;
        if (account instanceof Patient) {
            rol = "Patient";
            name = ((Patient) account).getName();
        } else if (account instanceof Doctor) {
            rol = "Doctor";
            name = ((Doctor) account).getName();
        } else {
            rol = "Admin";
            name = "Admin";
        }
        Map<String, Object> map = new HashMap<>();
        map.put("rol", rol);
        map.put("name", name);
        map.put("id", account.getCode());
        return jwtUtils.generateToken(account.getEmail(), map);
    }
}
