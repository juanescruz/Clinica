package evovital.uniquindio.edu.co.controllers;

import evovital.uniquindio.edu.co.dto.auxiliar.MensajeDTO;
import evovital.uniquindio.edu.co.dto.imagen.ImagenDTO;
import evovital.uniquindio.edu.co.servicios.especificaciones.ImagenesService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
@CrossOrigin
@RestController
@RequestMapping("/api/imagenes")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class ImagenesController {
    private final ImagenesService imagenesService;

    @PostMapping("/subir")
    public ResponseEntity<MensajeDTO<Map>> subir(@RequestParam("file") MultipartFile imagen)
            throws Exception {
        Map respuesta = imagenesService.subirImagen(imagen);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, respuesta));
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<MensajeDTO<Map>> eliminar(@RequestBody ImagenDTO imagenDTO) throws
            Exception {
        Map respuesta = imagenesService.eliminarImagen(imagenDTO.id());
        return ResponseEntity.ok().body(new MensajeDTO<>(false, respuesta));
    }
}