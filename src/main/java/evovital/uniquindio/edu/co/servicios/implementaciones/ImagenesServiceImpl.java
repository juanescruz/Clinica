package evovital.uniquindio.edu.co.servicios.implementaciones;

import evovital.uniquindio.edu.co.servicios.especificaciones.ImagenesService;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class ImagenesServiceImpl implements ImagenesService {

    // TODO implementar los metodos de la interfaz
    @Override
    public String subirImagen(File imagen) {
        return "https://www.imagenes.com/1234";
    }

    // TODO implementar los metodos de la interfaz
    @Override
    public File convertirImagen(String imagenCodificada) {
        return null;
    }
}
