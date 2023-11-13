package co.edu.uniquindio.proyecto.Services.Implement;

import co.edu.uniquindio.proyecto.Services.Interfaces.ImageServices;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service

public class ImageServicesImpl implements ImageServices {

    private final Cloudinary cloudinary;

    public ImageServicesImpl(){

        Map<String,String> config = new HashMap<>();
        config.put("cloud_name", "dcabxchxb");
        config.put("api_key", "125593646187625");
        config.put("api_secret", "j4Y0a3zYLOrcu98WRKWc2sD7Yog");

        cloudinary = new Cloudinary(config);

    }

    @Override
    public Map uploadImage(MultipartFile image) throws Exception {

        File file = convert(image);
        return cloudinary.uploader().upload(file, ObjectUtils.asMap("folder", "uniquindio/proyecto/photos"));

    }

    private File convert(MultipartFile image)throws IOException {

        File file = File.createTempFile(image.getOriginalFilename(), null);
        FileOutputStream fos = new FileOutputStream(file);

        fos.write(image.getBytes());
        fos.close();

        return file;

    }

    @Override
    public Map daleteImage(String idImage) throws Exception {

        return cloudinary.uploader().destroy(idImage, ObjectUtils.emptyMap());

    }

}
