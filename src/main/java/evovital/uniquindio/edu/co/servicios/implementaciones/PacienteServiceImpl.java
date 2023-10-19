package evovital.uniquindio.edu.co.servicios.implementaciones;

import evovital.uniquindio.edu.co.domain.*;
import evovital.uniquindio.edu.co.dto.auxiliar.EmailDTO;
import evovital.uniquindio.edu.co.dto.consulta.ConsultaDTOPaciente;
import evovital.uniquindio.edu.co.dto.consulta.DetalleConsultaDTOPaciente;
import evovital.uniquindio.edu.co.dto.consulta.InfoConsultaDTO;
import evovital.uniquindio.edu.co.dto.mensaje.MensajeDTOUsuario;
import evovital.uniquindio.edu.co.dto.paciente.PacienteDTO;
import evovital.uniquindio.edu.co.dto.pqrs.PQRSDTOPaciente;
import evovital.uniquindio.edu.co.dto.pqrs.PQRSDTOPacienteReq;
import evovital.uniquindio.edu.co.repositories.*;
import evovital.uniquindio.edu.co.servicios.especificaciones.EmailService;
import evovital.uniquindio.edu.co.servicios.especificaciones.ImagenesService;
import evovital.uniquindio.edu.co.servicios.especificaciones.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final PacienteRepository pacienteRepository;
    private final EstadoConsultaRepository estadoConsultaRepository;
    private final MedicoRepository medicoRepository;

    private final ImagenesService imagenesService;
    private final EmailService emailService;

    /**
     * Registra un nuevo paciente en la base de datos y en el sistema
     * @param pacienteDTO
     * @return
     */
    @Override
    public Long registrarse(PacienteDTO pacienteDTO) {

        Paciente paciente = pacienteDTO.toEntity();
        paciente.setPassword(new BCryptPasswordEncoder().encode(paciente.getPassword()));
        // TODO: paciente.setFotoPersonal(imagenesService.subirImagen());

        paciente = pacienteRepository.save(paciente);
        return paciente.getId();
    }

    /**
     * Edita el perfil del paciente en el sistema
     * @param idPaciente
     * @param pacienteDTO
     * @return
     * @throws Exception
     */
    @Override
    public Long editarPerfil(Long idPaciente, PacienteDTO pacienteDTO) throws Exception {

        Paciente pacienteEncontrado = pacienteRepository.findById(idPaciente).orElseThrow( () -> new Exception("No se encontró el paciente"));

        if (!pacienteEncontrado.getCedula().equals(pacienteDTO.cedula()))
            pacienteRepository.findByCedula(pacienteDTO.cedula()).ifPresent(m -> {
                throw new RuntimeException("Ya existe un paciente con esa cedula");
            });

        if (!pacienteEncontrado.getEmail().equals(pacienteDTO.email()))
            pacienteRepository.findByEmail(pacienteDTO.email()).ifPresent(m -> {
                throw new RuntimeException("Ya existe un paciente con ese email");
            });

        Paciente paciente = pacienteDTO.toEntity();
        paciente.setPassword(new BCryptPasswordEncoder().encode(paciente.getPassword()));
        // TODO: paciente.setFotoPersonal(imagenesService.subirImagen());
        paciente.setId(idPaciente);

        return pacienteRepository.save(paciente).getId();

    }

    // TODO: implementar luego de cambiar la propiedad "estaActivo" de Medico a Usuario
    @Override
    public PacienteDTO eliminarCuenta(Long idPaciente) {
        return null;
    }

    /**
     * Envia un email al paciente con un link para recuperar su contraseña
     * @param emailPaciente
     */
    @Override
    public void enviarLinkRecuperacion(String emailPaciente) {

        try {
            emailService.enviarEmail(new EmailDTO(
                    emailPaciente,
                    "Recuperación de contraseña",
                    "Hola, para recuperar tu contraseña ingresa al siguiente link: http://localhost:8080/recuperar-contrasena/%d".formatted(
                            pacienteRepository.findByEmail(emailPaciente).orElseThrow(() -> new RuntimeException("No se encontró el paciente")).getId()
                    )
            ));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Cambia la contraseña del paciente
     * @param idPaciente
     * @param newPassword
     */
    @Override
    public void cambiarPassword(Long idPaciente, String newPassword) {

        Paciente pacienteEncontrado = pacienteRepository.findById(idPaciente).orElseThrow(() -> new RuntimeException("No se encontró el paciente"));

        pacienteEncontrado.setPassword(new BCryptPasswordEncoder().encode(newPassword));
        pacienteRepository.save(pacienteEncontrado);

    }

    /**
     * Crea una nueva consulta a nombre del paciente
     * @param consultaDTO
     */
    @Override
    public void agendarConsulta(InfoConsultaDTO consultaDTO) {

        Consulta consulta = consultaDTO.toEntity();
        consulta.setPaciente(pacienteRepository.findById(consultaDTO.idPaciente()).orElseThrow(() -> new RuntimeException("No se encontró el paciente")));
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

    /**
     * Lista todas las PQRS del paciente
     * @param idPaciente
     * @return
     */
    @Override
    public List<PQRSDTOPaciente> listarPQRSPaciente(Long idPaciente) {

        List<Pqrs> pqrsPaciente = pqrsRepository.findAllByConsulta_Paciente_Id(idPaciente);

        return pqrsPaciente.stream().map(PQRSDTOPaciente::new).toList();

    }

    /**
     * Responde una PQRS respondida por el administrador
     * @param mensajeUsuario
     * @return
     */
    @Override
    public Long responderPQRS(MensajeDTOUsuario mensajeUsuario) {

        Mensaje mensaje = mensajeUsuario.toEntity();
        mensaje.setUsuario(usuarioRepository.findById(mensajeUsuario.idUsuario()).orElseThrow(() -> new RuntimeException("No se encontró el usuario")));
        mensaje.setPqrs(pqrsRepository.findById(mensajeUsuario.idPqrs()).orElseThrow(() -> new RuntimeException("No se encontró el pqrs")));

        return mensajeRepository.save(mensaje).getId();
    }

    /**
     * Lista todas las consultas que ha hecho un paciente en especifico
     * @param idPaciente
     * @return
     */
    @Override
    public List<ConsultaDTOPaciente> listarConsultasPaciente(Long idPaciente) {
        return consultaRepository.findAllByPaciente_Id(idPaciente).stream().map(ConsultaDTOPaciente::new).toList();
    }

    /**
     * trae todas las consultas de un paciente que haya hecho en una fecha en especifico
     * @param idPaciente
     * @param fecha
     * @return
     */
    @Override
    public List<ConsultaDTOPaciente> filtrarConsultasPorFecha(Long idPaciente, LocalDate fecha) {
        return consultaRepository.findAllByPaciente_IdAndFechaCreacion(idPaciente, fecha).stream().map(ConsultaDTOPaciente::new).toList();
    }

    /**
     * trae todas las consultas de una paciente que haya agendado con un medico en particular
     * @param idPaciente
     * @param idMedico
     * @return
     */
    @Override
    public List<ConsultaDTOPaciente> filtarConsultasPorMedico(Long idPaciente, Long idMedico) {
        return consultaRepository.findAllByPaciente_IdAndMedico_Id(idPaciente, idMedico).stream().map(ConsultaDTOPaciente::new).toList();
    }

    /**
     * obtiene el detalle de una consulta en especifico
     * @param idConsulta
     * @return
     */
    @Override
    public DetalleConsultaDTOPaciente verDetalleConsulta(Long idConsulta) {
        return consultaRepository.findById(idConsulta).orElseThrow(() -> new RuntimeException("No se encontró la consulta")).toDetalleConsultaDTOPaciente();
    }

    /**
     * reagenda una consulta en especifico
     * @param idConsulta
     * @param fechaYHora
     * @return
     */
    @Override
    public Long reagendarConsulta(Long idConsulta, LocalDateTime fechaYHora) {

        Consulta consultaEncontrada = consultaRepository.findById(idConsulta).orElseThrow(() -> new RuntimeException("No se encontró la consulta"));
        consultaEncontrada.setFechaYHoraAtencion(fechaYHora);

        return consultaRepository.save(consultaEncontrada).getId();

    }

    /**
     * califica una PQRS en especifico
     * @param idPQRS
     * @param calificacion
     * @return
     */
    @Override
    public Long calificarPQRS(Long idPQRS, int calificacion) {
        Pqrs pqrsEncontrada = pqrsRepository.findById(idPQRS).orElseThrow(() -> new RuntimeException("No se encontró la pqrs"));
        pqrsEncontrada.setCalificacion(calificacion);

        return pqrsRepository.save(pqrsEncontrada).getId();
    }
}
