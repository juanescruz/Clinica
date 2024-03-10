package evovital.uniquindio.edu.co.servicios.implementaciones;

import evovital.uniquindio.edu.co.domain.Consulta;
import evovital.uniquindio.edu.co.domain.Mensaje;
import evovital.uniquindio.edu.co.domain.Paciente;
import evovital.uniquindio.edu.co.domain.Pqrs;
import evovital.uniquindio.edu.co.dto.auxiliar.EmailDTO;
import evovital.uniquindio.edu.co.dto.consulta.ConsultaDTOPaciente;
import evovital.uniquindio.edu.co.dto.consulta.DetalleConsultaDTOPaciente;
import evovital.uniquindio.edu.co.dto.consulta.InfoConsultaDTO;
import evovital.uniquindio.edu.co.dto.mensaje.MensajeDTOUsuario;
import evovital.uniquindio.edu.co.dto.paciente.PacienteDTO;
import evovital.uniquindio.edu.co.dto.paciente.PacienteDTOPaciente;
import evovital.uniquindio.edu.co.dto.pqrs.PQRSDTOPaciente;
import evovital.uniquindio.edu.co.dto.pqrs.PQRSDTOPacienteReq;
import evovital.uniquindio.edu.co.repositories.*;
import evovital.uniquindio.edu.co.servicios.especificaciones.EmailService;
import evovital.uniquindio.edu.co.servicios.especificaciones.PacienteService;
import evovital.uniquindio.edu.co.util.EnvironmentVariables;
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
        paciente.setEstaActivo( true );

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
            usuarioRepository.findByCedula(pacienteDTO.cedula()).ifPresent(m -> {
                throw new RuntimeException("Ya existe un usuario con esa cedula");
            });

        if (!pacienteEncontrado.getEmail().equals(pacienteDTO.email()))
            usuarioRepository.findByEmail(pacienteDTO.email()).ifPresent(m -> {
                throw new RuntimeException("Ya existe un paciente con ese email");
            });

        Paciente paciente = pacienteDTO.toEntity();
        paciente.setId(idPaciente);
        paciente.setEstaActivo( true );
        paciente.setPassword(pacienteEncontrado.getPassword());

        return pacienteRepository.save(paciente).getId();

    }

    /**
     * elimina una cuenta de paciente
     * @param idPaciente
     * @return
     */
    @Override
    public PacienteDTO eliminarCuenta(Long idPaciente) {

        Paciente paciente = pacienteRepository.findById(idPaciente).orElseThrow(
                () -> new RuntimeException("paciente no encontrado")
        );

        paciente.setEstaActivo(false);
        return new PacienteDTO( pacienteRepository.save(paciente) );
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
                    "Hola, para recuperar tu contraseña ingresa al siguiente link: %s/cambiar-contrasenia/%d".formatted(
                            EnvironmentVariables.frontDomainName,
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

        if (LocalDateTime.now().plusDays(5).isBefore(consultaDTO.fechaYHoraDeAtencion()))
            throw new RuntimeException("la fecha y hora de atencion son invalidas");

        Consulta consulta = consultaDTO.toEntity();
        consulta.setPaciente(pacienteRepository.findById(consultaDTO.idPaciente()).orElseThrow(() -> new RuntimeException("No se encontró el paciente")));
        consulta.setEstadoConsulta(estadoConsultaRepository.findByEstado("Pendiente").orElseThrow(() -> new RuntimeException("No existe el estado")));
        consulta.setMedico(medicoRepository.findById(consultaDTO.idMedico()).orElseThrow(() -> new RuntimeException("No se encontró el medico")));

        consultaRepository.save(consulta);
    }

    /**
     * Crea una nueva PQRS a nombre del paciente asociado a la consulta, se asocia el paciente al mensaje del PQRS pero no al PQRS como tal, por facilidad
     */
    @Override
    public void crearPQRS(PQRSDTOPacienteReq pqrsPaciente) {

        Pqrs pqrs = pqrsPaciente.toEntity();
        pqrs.setEstadoPqrs(estadoPqrsRepository.findByEstado("En proceso").orElseThrow(() -> new RuntimeException("No existe el estado del pqrs")));
        pqrs.setConsulta(consultaRepository.findById(pqrsPaciente.idConsulta()).orElseThrow(() -> new RuntimeException("No se encontró la consulta")));
        pqrs = pqrsRepository.save(pqrs);

        Mensaje mensaje = pqrsPaciente.mensaje().toEntity();
        mensaje.setPqrs(pqrs);
        mensaje.setUsuario(usuarioRepository.findById(pqrsPaciente.mensaje().idUsuario()).orElseThrow(() -> new RuntimeException("No se encontró el usuario")));
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
        return new DetalleConsultaDTOPaciente( consultaRepository.findById(idConsulta).orElseThrow(() -> new RuntimeException("No se encontró la consulta")) );
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

    /**
     * trae el perfil de un paciente en especifico
     * @param idPaciente
     * @return
     */
    @Override
    public PacienteDTOPaciente verPerfil(Long idPaciente) {
        Paciente pacienteEncontrado = pacienteRepository.findById(idPaciente).orElseThrow(() -> new RuntimeException("No se encontró el paciente"));
        return new PacienteDTOPaciente(pacienteEncontrado);
    }
}
