package co.edu.uniquindio.proyecto.Controllers;


import co.edu.uniquindio.proyecto.Dto.ImageDTO;
import co.edu.uniquindio.proyecto.Dto.MessageDTO;
import co.edu.uniquindio.proyecto.Services.Interfaces.ImageServices;
import lombok.RequiredArgsConstructor;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImagesController {

    private final ImageServices imageServices;

    @PostMapping("/upload")
    public ResponseEntity<MessageDTO<Map>> upload (@RequestParam ("file")MultipartFile image)throws Exception{
        Map answer = imageServices.uploadImage(image);
        return ResponseEntity.ok().body(new MessageDTO<>(false, answer));

    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<MessageDTO<Map>> delete (@RequestBody ImageDTO imageDTO) throws
            Exception {

        Map answer = imageServices.daleteImage(imageDTO.id());
        return ResponseEntity.ok().body(new MessageDTO<>(false, answer));
    }
}
