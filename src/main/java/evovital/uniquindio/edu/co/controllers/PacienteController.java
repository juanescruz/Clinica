package evovital.uniquindio.edu.co.controllers;

import evovital.uniquindio.edu.co.dto.auxiliar.MensajeDTO;
import evovital.uniquindio.edu.co.dto.paciente.PacienteDTO;
import evovital.uniquindio.edu.co.servicios.especificaciones.PacienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/paciente")
public class PacienteController {

    private final PacienteService pacienteService;

    @PutMapping("/editarPerfil/{idPaciente}")
    public ResponseEntity<MensajeDTO<Long>> editarPerfil(@PathVariable Long idPaciente, @Valid @RequestBody PacienteDTO pacienteDTO) throws Exception {
        Long id = pacienteService.editarPerfil(idPaciente, pacienteDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, id) );
    }

    @DeleteMapping("/eliminarCuenta/{idPaciente}")
    public PacienteDTO eliminarCuenta(@PathVariable Long idPaciente) {
        return pacienteService.eliminarCuenta(idPaciente);
    }

}
