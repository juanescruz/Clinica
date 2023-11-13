package evovital.uniquindio.edu.co.controllers;

import evovital.uniquindio.edu.co.dto.auxiliar.MensajeDTO;
import evovital.uniquindio.edu.co.dto.consulta.ConsultaDTOPaciente;
import evovital.uniquindio.edu.co.dto.consulta.DetalleConsultaDTOPaciente;
import evovital.uniquindio.edu.co.dto.consulta.InfoConsultaDTO;
import evovital.uniquindio.edu.co.dto.mensaje.MensajeDTOUsuario;
import evovital.uniquindio.edu.co.dto.paciente.PacienteDTO;
import evovital.uniquindio.edu.co.dto.pqrs.PQRSDTOPaciente;
import evovital.uniquindio.edu.co.dto.pqrs.PQRSDTOPacienteReq;
import evovital.uniquindio.edu.co.servicios.especificaciones.PacienteService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/paciente")
@SecurityRequirement(name = "bearerAuth")
public class PacienteController {

    private final PacienteService pacienteService;

    @PutMapping("/editarPerfil/{idPaciente}")
    public ResponseEntity<MensajeDTO<Long>> editarPerfil(@PathVariable Long idPaciente, @Valid @RequestBody PacienteDTO pacienteDTO) throws Exception {
        Long id = pacienteService.editarPerfil(idPaciente, pacienteDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, id) );
    }

    @DeleteMapping("/eliminarCuenta/{idPaciente}")
    public ResponseEntity<MensajeDTO<PacienteDTO>> eliminarCuenta(@PathVariable Long idPaciente) {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, pacienteService.eliminarCuenta(idPaciente)) );
    }

    @PostMapping("/consulta/agendar")
    public ResponseEntity<MensajeDTO<String>> agendarConsulta(@Valid @RequestBody InfoConsultaDTO infoConsultaDTO) {
        pacienteService.agendarConsulta(infoConsultaDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "consulta agendada con exito") );
    }

    @PostMapping("/pqrs/crear")
    public ResponseEntity<MensajeDTO<String>> crearPqrs(@Valid @RequestBody PQRSDTOPacienteReq pqrsdtoPacienteReq) {
        pacienteService.crearPQRS(pqrsdtoPacienteReq);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "pqrs creado con exito") );
    }

    @GetMapping("/pqrs/listar/{idPaciente}")
    public ResponseEntity<MensajeDTO<List<PQRSDTOPaciente>>> listarPqrsPaciente(@PathVariable Long idPaciente) {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, pacienteService.listarPQRSPaciente(idPaciente)) );
    }

    @PostMapping("/pqrs/responder")
    public ResponseEntity<MensajeDTO<Long>> responderPqrs(@Valid @RequestBody MensajeDTOUsuario mensajeDTOUsuario) {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, pacienteService.responderPQRS(mensajeDTOUsuario)) );
    }

    @GetMapping("/consulta/listar/{idPaciente}")
    public ResponseEntity<MensajeDTO<List<ConsultaDTOPaciente>>> listarConsultas(@PathVariable Long idPaciente) {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, pacienteService.listarConsultasPaciente(idPaciente)) );
    }

    @GetMapping("/consulta/filtrar/fecha/{idPaciente}/{fecha}")
    public ResponseEntity<MensajeDTO<List<ConsultaDTOPaciente>>> filtrarPorFecha(@PathVariable Long idPaciente, @PathVariable LocalDate fecha) {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, pacienteService.filtrarConsultasPorFecha(idPaciente, fecha)) );
    }

    @GetMapping("/consulta/filtrar/medico/{idPaciente}/{idMedico}")
    public ResponseEntity<MensajeDTO<List<ConsultaDTOPaciente>>> filtrarPorMedico(@PathVariable Long idPaciente, @PathVariable Long idMedico) {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, pacienteService.filtarConsultasPorMedico(idPaciente, idMedico)) );
    }

    @GetMapping("/consulta/obtener/{idConsulta}")
    public ResponseEntity<MensajeDTO<DetalleConsultaDTOPaciente>> obtenerConsulta(@PathVariable Long idConsulta) {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, pacienteService.verDetalleConsulta(idConsulta)) );
    }

    @PostMapping("/consulta/agendar/{idConsulta}/{fechaYHora}")
    public ResponseEntity<MensajeDTO<Long>> reagendarConsulta(@PathVariable Long idConsulta, @PathVariable LocalDateTime fechaYHora) {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, pacienteService.reagendarConsulta(idConsulta, fechaYHora)) );
    }

    @PostMapping("/pqrs/calificar/{idPqrs}/{calificacion}")
    public ResponseEntity<MensajeDTO<Long>> calificarPqrs(@PathVariable Long idPqrs, @PathVariable int calificacion) {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, pacienteService.calificarPQRS(idPqrs, calificacion)) );
    }

}
