package evovital.uniquindio.edu.co.controllers;

import evovital.uniquindio.edu.co.dto.auxiliar.MensajeDTO;
import evovital.uniquindio.edu.co.dto.medico.MedicoDTO;
import evovital.uniquindio.edu.co.dto.paciente.InfoPacienteDTO;
import evovital.uniquindio.edu.co.dto.paciente.PacienteDTO;
import evovital.uniquindio.edu.co.servicios.especificaciones.AdministradorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/administrador")
public class AdministradorController {

    private final AdministradorService administradorService;

    @GetMapping("/detalle/{codigo}")
    public ResponseEntity<MensajeDTO<PacienteDTO>> verDetallePaciente(@PathVariable Long codigo) throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false, administradorService.obtenerDetallePaciente(codigo)) );
    }

    @GetMapping("/listar-todos")
    public ResponseEntity<MensajeDTO<List<InfoPacienteDTO>>> listarTodos(){
        return ResponseEntity.ok().body( new MensajeDTO<>(false, administradorService.listarPacientes()) );
    }

    @PostMapping("/crear/medico")
    public ResponseEntity<MensajeDTO<Long>> crearMedico(@Valid @RequestBody MedicoDTO medicoDTO) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, administradorService.crearMedico(medicoDTO)));
    }

    @PutMapping("/actualizar/medico")
    public ResponseEntity<MensajeDTO<Long>> actualizarMedico(@PathVariable Long id, @Valid @RequestBody MedicoDTO medicoDTO) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, administradorService.actualizarMedico(medicoDTO)));
    }

}
