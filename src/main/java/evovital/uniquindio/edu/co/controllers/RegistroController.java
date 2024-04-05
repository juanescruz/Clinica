    package evovital.uniquindio.edu.co.controllers;


import evovital.uniquindio.edu.co.dto.auxiliar.MensajeDTO;
import evovital.uniquindio.edu.co.util.ObjetoInicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/registro")

public class RegistroController {
    //comment
    @GetMapping("/ciudades")
    public ResponseEntity<MensajeDTO<List<String>>> listarCiudades() {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, ObjetoInicio.ciudades));
    }

    @GetMapping("/epss")
    public ResponseEntity<MensajeDTO<List<String>>> listarEpss() {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, ObjetoInicio.epss));
    }

    @GetMapping("/tiposSangre")
    public ResponseEntity<MensajeDTO<List<String>>> listarTiposSangre() {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, ObjetoInicio.tiposSangre));
    }
}
