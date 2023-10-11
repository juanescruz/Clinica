package evovital.uniquindio.edu.co.servicios.implementaciones;

import evovital.uniquindio.edu.co.domain.Paciente;
import evovital.uniquindio.edu.co.dto.consulta.ConsultaDTOPaciente;
import evovital.uniquindio.edu.co.dto.consulta.DetalleConsultaDTOPaciente;
import evovital.uniquindio.edu.co.dto.consulta.InfoConsultaDTO;
import evovital.uniquindio.edu.co.dto.login.AuthLoginDto;
import evovital.uniquindio.edu.co.dto.mensaje.MensajeDTOUsuario;
import evovital.uniquindio.edu.co.dto.paciente.PacienteDTO;
import evovital.uniquindio.edu.co.dto.pqrs.PQRSDTOPaciente;
import evovital.uniquindio.edu.co.dto.pqrs.PQRSDTOPacienteReq;
import evovital.uniquindio.edu.co.repositories.PacienteRepository;
import evovital.uniquindio.edu.co.servicios.especificaciones.PacienteService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PacienteServiceImpl implements PacienteService {


    private final PacienteRepository pacienteRepository;



    @Override
    public boolean registrarse(PacienteDTO pacienteDTO) {
        return false;
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

    @Override
    public void agendarCita(InfoConsultaDTO consultaDTO) {

    }

    @Override
    public void crearPQR(PQRSDTOPacienteReq pqrsPaciente) {

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

    @Override
    public boolean isPaciente(AuthLoginDto loginDto) {
        return pacienteRepository.existsByEmailAndPassword(loginDto.email(), loginDto.password());
    }

    @Override
    public Paciente signIn(AuthLoginDto loginDto) {
        return pacienteRepository.getByEmailAndPassword(loginDto.email(), loginDto.password());
    }

}
