package evovital.uniquindio.edu.co.servicios.implementaciones;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import evovital.uniquindio.edu.co.servicios.especificaciones.ImagenesService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

@Service
public class ImagenesServiceImpl implements ImagenesService {

    private final Cloudinary cloudinary;

    public ImagenesServiceImpl() {
        Map<String, String> config = Map.of(
                "cloud_name", "duhcmfydk",
                "api_key", "551487173235559",
                "api_secret", "***************************"
        );

        cloudinary = new Cloudinary(config);
    }

    @Override
    public Map subirImagen(MultipartFile imagen) throws Exception {

        File file = convertir(imagen);
        return cloudinary.uploader().upload(file, ObjectUtils.asMap(
                "folder", "EvoVitalImages"
        ));

    }

    @Override
    public Map eliminarImagen(String idImagen) throws Exception {

        return cloudinary.uploader().destroy(idImagen, ObjectUtils.emptyMap());

    }

    private File convertir(MultipartFile imagen) throws IOException {
        File file = File.createTempFile(imagen.getOriginalFilename(), null);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(imagen.getBytes());
        fos.close();
        return file;
    }
}
