package evovital.uniquindio.edu.co.controllers;

import evovital.uniquindio.edu.co.dto.auxiliar.MensajeDTO;
import evovital.uniquindio.edu.co.dto.consulta.ConsultaDTOAdmin;
import evovital.uniquindio.edu.co.dto.horario.HorarioDTO;
import evovital.uniquindio.edu.co.dto.medico.InfoMedicoDTO;
import evovital.uniquindio.edu.co.dto.medico.MedicoDTO;
import evovital.uniquindio.edu.co.dto.medico.MedicoDTOAdmin;
import evovital.uniquindio.edu.co.dto.mensaje.MensajeDTOUsuario;
import evovital.uniquindio.edu.co.dto.paciente.InfoPacienteDTO;
import evovital.uniquindio.edu.co.dto.paciente.PacienteDTO;
import evovital.uniquindio.edu.co.dto.pqrs.InfoPQRSDTO;
import evovital.uniquindio.edu.co.dto.pqrs.PQRSDTOAdmin;
import evovital.uniquindio.edu.co.servicios.especificaciones.AdministradorService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/administrador")
@SecurityRequirement(name = "bearerAuth")
public class AdministradorController {

    private final AdministradorService administradorService;

    @GetMapping("/detalle/paciente/{codigo}")
    public ResponseEntity<MensajeDTO<PacienteDTO>> verDetallePaciente(
        @PathVariable Long codigo
    ) throws Exception {
        return ResponseEntity.ok()
            .body(
                new MensajeDTO<>(
                    false,
                    administradorService.obtenerDetallePaciente(codigo)
                )
            );
    }

    @GetMapping("/listar/paciente")
    public ResponseEntity<MensajeDTO<List<InfoPacienteDTO>>> listarPacientes() {
        return ResponseEntity.ok()
            .body(
                new MensajeDTO<>(false, administradorService.listarPacientes())
            );
    }

    @PostMapping("/crear/medico")
    public ResponseEntity<MensajeDTO<Long>> crearMedico(
        @Valid @RequestBody MedicoDTO medicoDTO
    ) throws Exception {
        return ResponseEntity.ok()
            .body(
                new MensajeDTO<>(
                    false,
                    administradorService.crearMedico(medicoDTO)
                )
            );
    }

    @PutMapping("/actualizar/medico")
    public ResponseEntity<MensajeDTO<Long>> actualizarMedico(
        @Valid @RequestBody MedicoDTO medicoDTO
    ) throws Exception {
        return ResponseEntity.ok()
            .body(
                new MensajeDTO<>(
                    false,
                    administradorService.actualizarMedico(medicoDTO)
                )
            );
    }

    @DeleteMapping("/eliminar/medico/{id}")
    public ResponseEntity<MensajeDTO<MedicoDTO>> eliminarMedico(
        @PathVariable Long id
    ) throws Exception {
        return ResponseEntity.ok()
            .body(
                new MensajeDTO<>(false, administradorService.eliminarMedico(id))
            );
    }

    @GetMapping("/listar/medico")
    public ResponseEntity<MensajeDTO<List<MedicoDTOAdmin>>> listarMedicos()
        throws Exception {
        return ResponseEntity.ok()
            .body(
                new MensajeDTO<>(false, administradorService.listarMedicos())
            );
    }

    @GetMapping("/obtener/medico/{id}")
    public ResponseEntity<MensajeDTO<InfoMedicoDTO>> obtenerMedico(
        @PathVariable Long id
    ) throws Exception {
        return ResponseEntity.ok()
            .body(
                new MensajeDTO<>(false, administradorService.obtenerMedico(id))
            );
    }

    @GetMapping("/listar/pqrs")
    public ResponseEntity<MensajeDTO<List<PQRSDTOAdmin>>> listarPqrs()
        throws Exception {
        return ResponseEntity.ok()
            .body(new MensajeDTO<>(false, administradorService.listarPQRS()));
    }

    @PostMapping("/responder/pqrs")
    public ResponseEntity<MensajeDTO<Long>> responderPqrs(
        @Valid @RequestBody MensajeDTOUsuario mensajeDTOUsuario
    ) throws Exception {
        return ResponseEntity.ok()
            .body(
                new MensajeDTO<>(
                    false,
                    administradorService.responderPQRS(mensajeDTOUsuario)
                )
            );
    }

    @GetMapping("/obtener/pqrs/{id}")
    public ResponseEntity<MensajeDTO<InfoPQRSDTO>> obtenerPqrs(
        @PathVariable Long id
    ) throws Exception {
        return ResponseEntity.ok()
            .body(
                new MensajeDTO<>(false, administradorService.verDetallePQRS(id))
            );
    }

    @GetMapping("/listar/consulta")
    public ResponseEntity<MensajeDTO<List<ConsultaDTOAdmin>>> listarConsultas()
        throws Exception {
        return ResponseEntity.ok()
            .body(
                new MensajeDTO<>(false, administradorService.listarConsultas())
            );
    }

    @PostMapping("/crear/horario/{idMedico}")
    public ResponseEntity<MensajeDTO<Long>> crearHorario(
        @PathVariable Long idMedico,
        @Valid @RequestBody HorarioDTO horarioDTO
    ) throws Exception {
        return ResponseEntity.ok()
            .body(
                new MensajeDTO<>(
                    false,
                    administradorService.crearHorario(idMedico, horarioDTO)
                )
            );
    }

    @PutMapping("/actualizar/horario/{idMedico}")
    public ResponseEntity<MensajeDTO<Long>> actualizarHorario(
        @PathVariable Long idMedico,
        @Valid @RequestBody HorarioDTO horarioDTO
    ) throws Exception {
        return ResponseEntity.ok()
            .body(
                new MensajeDTO<>(
                    false,
                    administradorService.crearHorario(idMedico, horarioDTO)
                )
            );
    }
}
