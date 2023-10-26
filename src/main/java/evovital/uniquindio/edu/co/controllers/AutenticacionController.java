package evovital.uniquindio.edu.co.controllers;

import evovital.uniquindio.edu.co.dto.auxiliar.MensajeDTO;
import evovital.uniquindio.edu.co.dto.auxiliar.TokenDTO;
import evovital.uniquindio.edu.co.dto.login.AuthLoginDto;
import evovital.uniquindio.edu.co.dto.paciente.PacienteDTO;
import evovital.uniquindio.edu.co.servicios.especificaciones.AutenticacionService;
import evovital.uniquindio.edu.co.servicios.especificaciones.PacienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AutenticacionController {

    private final AutenticacionService autenticacionServicio;
    private final PacienteService pacienteService;

    @PostMapping("/login")
    public ResponseEntity<MensajeDTO<TokenDTO>> login(@Valid @RequestBody AuthLoginDto loginDTO) throws Exception {
        TokenDTO tokenDTO = autenticacionServicio.login(loginDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, tokenDTO));
    }

    @PostMapping("/registrarse")
    public ResponseEntity<MensajeDTO<String>> registrarse(@Valid @RequestBody PacienteDTO pacienteDTO) throws Exception{
        pacienteService.registrarse(pacienteDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Paciente registrado correctamente") );
    }

}
