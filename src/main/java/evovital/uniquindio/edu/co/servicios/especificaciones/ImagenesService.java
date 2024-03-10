package evovital.uniquindio.edu.co.servicios.especificaciones;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ImagenesService {

    Map subirImagen(MultipartFile imagen) throws Exception;

    Map eliminarImagen(String idImagen) throws Exception;

}
