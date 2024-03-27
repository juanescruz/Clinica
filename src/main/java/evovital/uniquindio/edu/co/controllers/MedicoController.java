package evovital.uniquindio.edu.co.controllers;

import evovital.uniquindio.edu.co.dto.atencionConsulta.AtencionConsultaDTOMedico;
import evovital.uniquindio.edu.co.dto.auxiliar.MensajeDTO;
import evovital.uniquindio.edu.co.dto.consulta.ConsultaDTOMedico;
import evovital.uniquindio.edu.co.dto.consulta.DetalleConsultaDTOMedico;
import evovital.uniquindio.edu.co.dto.consulta.MetodoPagoDTO;
import evovital.uniquindio.edu.co.servicios.especificaciones.MedicoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/medico")
@SecurityRequirement(name = "bearerAuth")
public class MedicoController {

    private final MedicoService medicoService;

    @GetMapping("/consulta/filtrar/pendiente/{idMedico}")
    public ResponseEntity<MensajeDTO<List<ConsultaDTOMedico>>> listarConsultasPendientes(@PathVariable Long idMedico) {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, medicoService.listarConsultasPendientes(idMedico)) );
    }

    @GetMapping("/consulta/obtener/{idConsulta}")
    public ResponseEntity<MensajeDTO<DetalleConsultaDTOMedico>> obtenerConsulta(@PathVariable Long idConsulta) {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, medicoService.verDetalleConsulta(idConsulta)) );
    }

    @PostMapping("/consulta/atender/{idConsulta}")
    public ResponseEntity<MensajeDTO<String>> atenderConsulta(@PathVariable Long idConsulta, @Valid @RequestBody AtencionConsultaDTOMedico atencionConsultaDTOMedico) throws Exception {
        medicoService.atenderConsulta(idConsulta, atencionConsultaDTOMedico);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "consulta atendida con exito") );
    }

    @GetMapping("/consulta/filtrar/paciente/{idMedico}/{idPaciente}")
    public ResponseEntity<MensajeDTO<List<ConsultaDTOMedico>>> listarConsultasPaciente(@PathVariable Long idMedico, @PathVariable Long idPaciente) {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, medicoService.listarConsultasPaciente(idMedico, idPaciente)) );
    }

    @PostMapping("/consulta/agendar-dia-libre/{idMedico}/{fecha}")
    public ResponseEntity<MensajeDTO<Boolean>> agendarDiaLibre(@PathVariable Long idMedico, @PathVariable LocalDate fecha) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, medicoService.agendarDiaLibre(idMedico, fecha)) );
    }

    @GetMapping("/consulta/filtrar/realizadas/{idMedico}")
    public ResponseEntity<MensajeDTO<List<ConsultaDTOMedico>>> listarConsultasRealizadas(@PathVariable Long idMedico) {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, medicoService.listarConsultasRealizadasMedico(idMedico)) );
    }

    @PostMapping("/consulta/hacer-factura/{idConsulta}")
    public ResponseEntity<MensajeDTO<Boolean>> hacerFactura(@PathVariable Long idConsulta, @RequestBody MetodoPagoDTO pago) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, medicoService.hacerFactura(idConsulta, pago)) );
    }

    @DeleteMapping("/consulta/cancelar/{idConsulta}")
    public ResponseEntity<MensajeDTO<Boolean>> cancelarConsulta(@PathVariable Long idConsulta) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, medicoService.cancelarConsulta(idConsulta)) );
    }

    @GetMapping("/consulta/todas-consultas/{idMedico}")
    public ResponseEntity<MensajeDTO<List<ConsultaDTOMedico>>> listarTodasConsultas(@PathVariable Long idMedico) {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, medicoService.listarTodasConsultas(idMedico)) );
    }
}
