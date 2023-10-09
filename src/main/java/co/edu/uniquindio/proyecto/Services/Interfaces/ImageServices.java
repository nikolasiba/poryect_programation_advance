package co.edu.uniquindio.proyecto.Services.Interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ImageServices {

    Map uploadImage(MultipartFile image) throws Exception;
    Map daleteImage(String idImage) throws Exception;

}
