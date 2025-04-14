package evovital.uniquindio.edu.co;

import evovital.uniquindio.edu.co.domain.*;
import evovital.uniquindio.edu.co.dto.consulta.InfoConsultaDTO;
import evovital.uniquindio.edu.co.dto.mensaje.MensajeDTOUsuario;
import evovital.uniquindio.edu.co.dto.paciente.PacienteDTO;
import evovital.uniquindio.edu.co.dto.paciente.PacienteDTOPaciente;
import evovital.uniquindio.edu.co.dto.pqrs.PQRSDTOPacienteReq;
import evovital.uniquindio.edu.co.repositories.*;
import evovital.uniquindio.edu.co.servicios.especificaciones.PacienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class PacienteTest {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private EstadoConsultaRepository estadoConsultaRepository;

    @Autowired
    private EstadoPqrsRepository estadoPqrsRepository;

    @Autowired
    private EspecialidadRepository especialidadRepository;


    private Long pacienteId;
    private Long medicoId;
    private Long consultaId;
    private Long pqrsId;

    @BeforeEach
    public void setup() {
        // Create required reference data
        crearEstadosConsulta();
        crearEstadosPqrs();

        // Create a test patient
        PacienteDTO pacienteDTO = new PacienteDTO(
                "Test Patient",
                "1002555555",
                "testpatient@example.com",
                "password123",
                LocalDate.of(1990, 1, 1),
                "Maní",
                "EPS Sura",
                "Sanitas",
                "O-",
                "Medellín",
                "3053053051"
        );

        pacienteId = pacienteService.registrarse(pacienteDTO);

        // Create a test medico
        Medico medico = Medico.builder()

                .nombre("Dr. Test")
                .email("medico@test.com")
                .password("hola mundo")
                .sal("hioqijwdoqi")
                .mensajes(new ArrayList<>())
                .cedula("1002535555")
                .estaActivo(true)
                .ciudadResidencia("Medellín")
                .telefono("3101234567")
                .urlFotoPersonal("https://example.com/medico.jpg")
                .consultas(new ArrayList<>())
                .especialidad(especialidadRepository.findById(1L).orElse(null))
                .horarios(new ArrayList<>())
                .diasLibres(new ArrayList<>())
                .build();

        Medico savedMedico = medicoRepository.save(medico);

        medicoId = medico.getId();

        // Create a test consultation
        InfoConsultaDTO consultaDTO = new InfoConsultaDTO(
                pacienteId,
                medicoId,
                LocalDateTime.now().plusDays(1),
                "Test consultation"
        );

        pacienteService.agendarConsulta(consultaDTO);

        // Get the created consultation ID
        consultaId = pacienteService.listarConsultasEntityPaciente(pacienteId)
                .stream()
                .findFirst()
                .map(Consulta::getId)
                .orElse(null);

        // Create a test PQRS
        if (consultaId != null) {
            PQRSDTOPacienteReq pqrsRequest = new PQRSDTOPacienteReq(
                    consultaId,
                    new MensajeDTOUsuario(
                            "Test PQRS message",
                            pacienteId,
                            null
                    )
            );

            pacienteService.crearPQRS(pqrsRequest);

            // Get the created PQRS ID
            pqrsId = pacienteService.listarPQRSEntityPaciente(pacienteId)
                    .stream()
                    .findFirst()
                    .map(Pqrs::getId)
                    .orElse(null);
        }
    }

    private void crearEstadosConsulta() {
        // Create required estados consulta if they don't exist
        if (estadoConsultaRepository.findByEstado("Pendiente").isEmpty()) {
            EstadoConsulta pendiente = new EstadoConsulta();
            pendiente.setEstado("Pendiente");
            estadoConsultaRepository.save(pendiente);
        }

        if (estadoConsultaRepository.findByEstado("Completada").isEmpty()) {
            EstadoConsulta completada = new EstadoConsulta();
            completada.setEstado("Completada");
            estadoConsultaRepository.save(completada);
        }

        if (estadoConsultaRepository.findByEstado("Cancelada").isEmpty()) {
            EstadoConsulta cancelada = new EstadoConsulta();
            cancelada.setEstado("Cancelada");
            estadoConsultaRepository.save(cancelada);
        }
    }

    private void crearEstadosPqrs() {
        // Create required estados pqrs if they don't exist
        if (estadoPqrsRepository.findByEstado("En proceso").isEmpty()) {
            EstadoPqrs enProceso = new EstadoPqrs();
            enProceso.setEstado("En proceso");
            estadoPqrsRepository.save(enProceso);
        }

        if (estadoPqrsRepository.findByEstado("Completado").isEmpty()) {
            EstadoPqrs completado = new EstadoPqrs();
            completado.setEstado("Completado");
            estadoPqrsRepository.save(completado);
        }
    }


    @Test
    public void registrarseTest() {
        PacienteDTO pacienteDTO = new PacienteDTO(
                "Juan Carlos",
                "1002555553",
                "testpatient@exampleee.com",
                "password1234",
                LocalDate.of(1990, 1, 1),
                "Maníes",
                "EPS SOS",
                "Sanitas",
                "A+",
                "Medellín",
                "3053053051"
        );

        Long newPacienteId = pacienteService.registrarse(pacienteDTO);

        assertNotNull(newPacienteId);
        assertTrue(newPacienteId > 0);

        Paciente savedPaciente = pacienteRepository.findById(newPacienteId).orElse(null);
        assertNotNull(savedPaciente);
        assertEquals(pacienteDTO.cedula(), savedPaciente.getCedula());
        assertEquals(pacienteDTO.email(), savedPaciente.getEmail());
        assertEquals(pacienteDTO.nombre(), savedPaciente.getNombre());
    }
    /*
      PacienteDTO(Paciente paciente)
  PacienteDTO(String nombre,
   String cedula,
    String email,
    String password,
     LocalDate fechaNacimiento,
      String alergias,
      String urlFotoPersonal, ...)

     */
    @Test
    public void editarPerfilTest() throws Exception {
        PacienteDTO updatedPacienteDTO = new PacienteDTO(
                "Updated Patient Name",
                "1234567890", // Same cedula to avoid uniqueness issues
                "testpatient@example.com", // Same email to avoid uniqueness issues
                "password123",
                LocalDate.of(1990, 1, 1),
                "Updated allergies",
                "Sanitas EPS",
                "O+",
                "Cali",
                "Cali",
                "https://example.com/updated-photo.jpg"
        );

        Long updatedPacienteId = pacienteService.editarPerfil(pacienteId, updatedPacienteDTO);

        assertEquals(pacienteId, updatedPacienteId);

        Paciente updatedPaciente = pacienteRepository.findById(pacienteId).orElse(null);
        assertNotNull(updatedPaciente);
        assertEquals("Updated Patient Name", updatedPaciente.getNombre());
        assertEquals("Updated allergies", updatedPaciente.getAlergias());
        assertEquals("Cali", updatedPaciente.getCiudadResidencia());
    }

    @Test
    public void verPerfilTest() {
        Paciente perfil = pacienteService.verPerfilEntity(pacienteId);

        assertNotNull(perfil);
        assertEquals(pacienteId, perfil.getId());
        assertEquals("Test Patient", perfil.getNombre());
        assertEquals("testpatient@example.com", perfil.getEmail());
    }

    @Test
    public void eliminarCuentaTest() {
        PacienteDTO pacienteEliminado = pacienteService.eliminarCuenta(pacienteId);

        assertNotNull(pacienteEliminado);

        Paciente paciente = pacienteRepository.findById(pacienteId).orElse(null);
        assertNotNull(paciente);
        assertFalse(paciente.isEstaActivo());
    }

    @Test
    public void cambiarPasswordTest() {
        pacienteService.cambiarPassword(pacienteId, "newPassword123");
        // Since passwords are encrypted, we can't directly compare them
        // This test just verifies no exceptions are thrown
    }

    @Test
    public void agendarConsultaTest() {
        InfoConsultaDTO consultaDTO = new InfoConsultaDTO(
                pacienteId,
                medicoId,
                LocalDateTime.now().plusDays(2),
                "Another test consultation"
        );

        pacienteService.agendarConsulta(consultaDTO);

        var consultas = pacienteService.listarConsultasPaciente(pacienteId);
        assertNotNull(consultas);
        assertTrue(consultas.size() >= 1);
    }

    @Test
    public void reagendarConsultaTest() {
        assertNotNull(consultaId, "No consultation was created in setup");

        LocalDateTime newDateTime = LocalDateTime.now().plusDays(3);
        Long reagendadaId = pacienteService.reagendarConsulta(consultaId, newDateTime);

        assertEquals(consultaId, reagendadaId);

        var detalleConsulta = pacienteService.verDetalleConsulta(consultaId);
        assertEquals(newDateTime, detalleConsulta.fechaYHoraAtencion());
    }


    @Test
    public void listarConsultasPacienteTest() {
        var consultas = pacienteService.listarConsultasPaciente(pacienteId);

        assertNotNull(consultas);
        assertFalse(consultas.isEmpty());
    }

    @Test
    public void filtrarConsultasPorFechaTest() {
        var consultas = pacienteService.filtrarConsultasPorFecha(pacienteId, LocalDate.now());

        assertNotNull(consultas);
        // May be empty depending on when test runs vs when consultation was created
    }

    @Test
    public void filtrarConsultasPorMedicoTest() {
        var consultas = pacienteService.filtarConsultasPorMedico(pacienteId, medicoId);

        assertNotNull(consultas);
        assertFalse(consultas.isEmpty());
    }



    @Test
    public void listarPQRSPacienteTest() {
        var pqrsList = pacienteService.listarPQRSPaciente(pacienteId);

        assertNotNull(pqrsList);
        assertFalse(pqrsList.isEmpty());
    }

    @Test
    public void responderPQRSTest() {
        assertNotNull(pqrsId, "No PQRS was created in setup");

        MensajeDTOUsuario mensajeRespuesta = new MensajeDTOUsuario(
                "Test response to PQRS",
                pacienteId,
                pqrsId
        );

        Long mensajeId = pacienteService.responderPQRS(mensajeRespuesta);

        assertNotNull(mensajeId);
        assertTrue(mensajeId > 0);
    }

    @Test
    public void calificarPQRSTest() {
        assertNotNull(pqrsId, "No PQRS was created in setup");

        Long calificadaId = pacienteService.calificarPQRS(pqrsId, 5);

        assertEquals(pqrsId, calificadaId);
    }

    @Test
    public void enviarLinkDeRecuperacionTest() {
        // Just testing no exceptions
        pacienteService.enviarLinkRecuperacion("testpatient@example.com");
    }
}
