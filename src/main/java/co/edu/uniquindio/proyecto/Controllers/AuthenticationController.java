package co.edu.uniquindio.proyecto.Controllers;


import co.edu.uniquindio.proyecto.Dto.LoginDTO;
import co.edu.uniquindio.proyecto.Dto.MessageDTO;
import co.edu.uniquindio.proyecto.Dto.TokenDTO;
import co.edu.uniquindio.proyecto.Model.Message;
import co.edu.uniquindio.proyecto.Services.Interfaces.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<MessageDTO<TokenDTO>>login(@Valid @RequestBody LoginDTO loginDTO)throws Exception{
        TokenDTO tokenDTO = authenticationService.login(loginDTO);
        return ResponseEntity.ok().body(new MessageDTO<>(false, tokenDTO));
    }
}
