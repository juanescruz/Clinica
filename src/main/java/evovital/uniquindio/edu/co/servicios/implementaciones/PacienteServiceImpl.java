package evovital.uniquindio.edu.co.servicios.implementaciones;

import evovital.uniquindio.edu.co.domain.*;
import evovital.uniquindio.edu.co.dto.consulta.ConsultaDTOPaciente;
import evovital.uniquindio.edu.co.dto.consulta.DetalleConsultaDTOPaciente;
import evovital.uniquindio.edu.co.dto.consulta.InfoConsultaDTO;
import evovital.uniquindio.edu.co.dto.mensaje.MensajeDTOUsuario;
import evovital.uniquindio.edu.co.dto.paciente.PacienteDTO;
import evovital.uniquindio.edu.co.dto.pqrs.PQRSDTOPaciente;
import evovital.uniquindio.edu.co.dto.pqrs.PQRSDTOPacienteReq;
import evovital.uniquindio.edu.co.repositories.*;
import evovital.uniquindio.edu.co.servicios.especificaciones.ImagenesService;
import evovital.uniquindio.edu.co.servicios.especificaciones.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteServiceImpl implements PacienteService {

    private final PqrsRepository pqrsRepository;
    private final EstadoPqrsRepository estadoPqrsRepository;
    private final ConsultaRepository consultaRepository;
    private final MensajeRepository mensajeRepository;
    private final UsuarioRepository usuarioRepository;
    private final PacienteRepository pacienteReposotory;
    private final EstadoConsultaRepository estadoConsultaRepository;
    private final MedicoRepository medicoRepository;

    private final ImagenesService imagenesService;

    @Override
    public boolean registrarse(PacienteDTO pacienteDTO) {

        Paciente paciente = pacienteDTO.toEntity();
        paciente.setFotoPersonal(imagenesService.subirImagen(imagenesService.convertirImagen(pacienteDTO.fotoPersonal())));

        paciente = pacienteReposotory.save(paciente);
        return true;
    }

    @Override
    public boolean editarPerfil(int idPaciente, PacienteDTO pacienteDTO) {
        return false;
    }

    @Override
    public PacienteDTO eliminarCuenta(int idPaciente) {
        return null;
    }

    @Override
    public boolean enviarLinkRecuperacion(String emailPaciente) {
        return false;
    }

    @Override
    public boolean cambiarPassword(int idPaciente, String password) {
        return false;
    }

    /**
     * Crea una nueva consulta a nombre del paciente
     * @param consultaDTO
     */
    @Override
    public void agendarConsulta(InfoConsultaDTO consultaDTO) {

        Consulta consulta = consultaDTO.toEntity();
        consulta.setPaciente(pacienteReposotory.findById(consultaDTO.idPaciente()).orElseThrow(() -> new RuntimeException("No se encontró el paciente")));
        consulta.setEstadoConsulta(estadoConsultaRepository.findByEstado("Pendiente"));
        consulta.setMedico(medicoRepository.findById(consultaDTO.idMedico()).orElseThrow(() -> new RuntimeException("No se encontró el medico")));

        consultaRepository.save(consulta);
    }

    /**
     * Crea una nueva PQRS a nombre del paciente asociado a la consulta, se asocia el paciente al mensaje del PQRS pero no al PQRS como tal, por facilidad
     */
    @Override
    public void crearPQRS(PQRSDTOPacienteReq pqrsPaciente) {

        Pqrs pqrs = pqrsPaciente.toEntity();
        pqrs.setEstadoPqrs(estadoPqrsRepository.findByEstado("En proceso"));
        pqrs.setConsulta(consultaRepository.findById(pqrsPaciente.idConsulta()).orElseThrow(() -> new RuntimeException("No se encontró la consulta")));
        pqrs = pqrsRepository.save(pqrs);

        Mensaje mensaje = pqrsPaciente.mensaje().toEntity();
        mensaje.setPqrs(pqrs);
        mensaje.setUsuario(usuarioRepository.getReferenceById(pqrsPaciente.mensaje().idUsuario()));
        mensajeRepository.save(mensaje);

    }

    @Override
    public List<PQRSDTOPaciente> listarPQRSPaciente(Long idPaciente) {
        return null;
    }

    @Override
    public void responderPQRS(Long idPQRS, MensajeDTOUsuario mensajeUsuario) {

    }

    @Override
    public List<ConsultaDTOPaciente> listarConsultasPaciente(Long idPaciente) {
        return null;
    }

    @Override
    public List<ConsultaDTOPaciente> filtrarConsultasPorFecha(Long idPaciente, LocalDate fecha) {
        return null;
    }

    @Override
    public List<ConsultaDTOPaciente> filtarConsultasPorMedico(Long idPaciente, Long idMedico) {
        return null;
    }

    @Override
    public DetalleConsultaDTOPaciente verDetalleConsulta(Long idConsulta) {
        return null;
    }

    @Override
    public boolean reagendarConsulta(Long idConsulta, LocalDateTime fechaYHora) {
        return false;
    }

    @Override
    public boolean calificarPQRS(Long idPQRS, int calificacion) {
        return false;
    }
}
