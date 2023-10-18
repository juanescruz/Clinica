package evovital.uniquindio.edu.co.servicios.especificaciones;

import evovital.uniquindio.edu.co.dto.consulta.ConsultaDTOPaciente;
import evovital.uniquindio.edu.co.dto.consulta.DetalleConsultaDTOPaciente;
import evovital.uniquindio.edu.co.dto.consulta.InfoConsultaDTO;
import evovital.uniquindio.edu.co.dto.mensaje.MensajeDTOUsuario;
import evovital.uniquindio.edu.co.dto.paciente.PacienteDTO;
import evovital.uniquindio.edu.co.dto.pqrs.PQRSDTOPaciente;
import evovital.uniquindio.edu.co.dto.pqrs.PQRSDTOPacienteReq;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PacienteService {

    Long registrarse(PacienteDTO pacienteDTO);

    Long editarPerfil(Long idPaciente, PacienteDTO pacienteDTO) throws Exception;

    PacienteDTO eliminarCuenta(Long idPaciente);

    boolean enviarLinkRecuperacion(String emailPaciente);

    boolean cambiarPassword(Long idPaciente, String password);

    void agendarConsulta(InfoConsultaDTO consultaDTO);

    void crearPQRS(PQRSDTOPacienteReq pqrsPaciente);

    List<PQRSDTOPaciente> listarPQRSPaciente(Long idPaciente);

    void responderPQRS(Long idPQRS, MensajeDTOUsuario mensajeUsuario);

    List<ConsultaDTOPaciente> listarConsultasPaciente(Long idPaciente);

    List<ConsultaDTOPaciente> filtrarConsultasPorFecha(Long idPaciente, LocalDate fecha);

    List<ConsultaDTOPaciente> filtarConsultasPorMedico(Long idPaciente, Long idMedico);

    DetalleConsultaDTOPaciente verDetalleConsulta(Long idConsulta);

    boolean reagendarConsulta(Long idConsulta, LocalDateTime fechaYHora);

    boolean calificarPQRS(Long idPQRS, int calificacion);

}
