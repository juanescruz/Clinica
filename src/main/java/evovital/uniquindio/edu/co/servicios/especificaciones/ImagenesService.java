package evovital.uniquindio.edu.co.servicios.especificaciones;

import java.io.File;

public interface ImagenesService {

    String subirImagen(File imagen);

    File convertirImagen(String imagenCodificada);

}
