package evovital.uniquindio.edu.co.servicios.implementaciones;

import evovital.uniquindio.edu.co.domain.HorarioAtencion;
import evovital.uniquindio.edu.co.domain.Medico;
import evovital.uniquindio.edu.co.domain.Mensaje;
import evovital.uniquindio.edu.co.domain.Pqrs;
import evovital.uniquindio.edu.co.dto.consulta.ConsultaDTOAdmin;
import evovital.uniquindio.edu.co.dto.horario.HorarioDTOActualizar;
import evovital.uniquindio.edu.co.dto.horario.HorarioDTOCrear;
import evovital.uniquindio.edu.co.dto.medico.InfoMedicoDTO;
import evovital.uniquindio.edu.co.dto.medico.MedicoDTOActualizar;
import evovital.uniquindio.edu.co.dto.medico.MedicoDTOAdmin;
import evovital.uniquindio.edu.co.dto.medico.MedicoDTOCrear;
import evovital.uniquindio.edu.co.dto.mensaje.MensajeDTOUsuario;
import evovital.uniquindio.edu.co.dto.pqrs.InfoPQRSDTO;
import evovital.uniquindio.edu.co.dto.pqrs.PQRSDTOAdmin;
import evovital.uniquindio.edu.co.repositories.*;
import evovital.uniquindio.edu.co.servicios.especificaciones.AdministradorService;
import evovital.uniquindio.edu.co.servicios.especificaciones.ImagenesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service // indica que esta clase es un servicio de manera semantica, se puede usar el "@Component"
@RequiredArgsConstructor // genera un constructor con los atributos "final"
public class AdministradorServiceImpl implements AdministradorService {

    private final MedicoRepository medicoRepository;
    private final EspecialidadRepository especialidadRepository;
    private final PqrsRepository pqrsRepository;
    private final MensajeRepository mensajeRepository;
    private final UsuarioRepository usuarioRepository;
    private final HorarioAtencionRepository horarioAtencionRepository;
    private final ConsultaRepository consultaRepository;

    private final ImagenesService imagenesService;

    /**
     * Crea un medico en la base de datos con la informacion necesaria y devuelve el id del medico que se acabo de crear
     * @param medico
     * @return
     * @throws Exception
     */
    @Override
    public Long crearMedico(MedicoDTOCrear medico) throws Exception {

        medicoRepository.findByCedulaOrEmail(medico.cedula(), medico.email()).ifPresent(m -> {
            throw new RuntimeException("Ya existe un medico con esa cedula o ese email");
        });

        Medico medicoRegistrado = medicoRepository.save(medicoDTOCrearToMedico(medico));

        if (!medico.horarios().isEmpty())
            medico.horarios().forEach(
                    horario -> crearHorario(medicoRegistrado.getId(), horario)
            );

        return medicoRegistrado.getId();
    }

    /**
     * Actualiza la informacion de un medico en la base de datos y devuelve el id del medico que se acabo de actualizar
     * @param codigo
     * @param medico
     * @return
     * @throws Exception
     */
    @Override
    public Long actualizarMedico(MedicoDTOActualizar medico) throws Exception {

        Medico medicoEncontrado = medicoRepository.findById(medico.id()).orElseThrow(() -> new Exception("No se encontró el medico que se quiere actualizar"));

        if (!medicoEncontrado.getCedula().equals(medico.cedula()))
            medicoRepository.findByCedula(medico.cedula()).ifPresent(m -> {
                throw new RuntimeException("Ya existe un medico con esa cedula");
            });

        if (!medicoEncontrado.getEmail().equals(medico.email()))
            medicoRepository.findByEmail(medico.email()).ifPresent(m -> {
                throw new RuntimeException("Ya existe un medico con ese email");
            });

        medicoEncontrado = medicoDTOActualizarToMedico(medico);

        Medico medicoActualizado = medicoRepository.save(medicoEncontrado);

        if (!medico.horarios().isEmpty())
            medico.horarios().forEach(
                    horario -> actualizarHorario(medicoActualizado.getId(), horario)
            );

        return medicoActualizado.getId();
    }

    /**
     * Elimina un medico de la base de datos y devuelve la informacion del medico que se acabo de eliminar
     * @param codigo
     * @return
     * @throws Exception
     */
    @Override
    public MedicoDTOCrear eliminarMedico(Long codigo) throws Exception {

        Medico medico = medicoRepository.findById(codigo).orElseThrow(() -> new Exception("No se encontró el medico que se quiere eliminar"));
        medico.setEstaActivo(false);

        return medicoToMedicoDTOCrear(medicoRepository.save(medico));
    }

    /**
     * Lista todos los medicos que estan en la base de datos
     * @return
     * @throws Exception
     */
    @Override
    public List<MedicoDTOAdmin> listarMedicos() throws Exception {

        if (medicoRepository.count() == 0) throw new Exception("No hay medicos para listar");

        return medicoRepository.findAllByEstaActivoTrue().stream().map(this::medicoToMedicoDTOAdmin).collect(Collectors.toList());
    }

    /**
     * Obtiene la informacion de un medico en especifico
     * @param codigo
     * @return
     * @throws Exception
     */
    @Transactional
    @Override
    public InfoMedicoDTO obtenerMedico(Long codigo) throws Exception {
        return medicoToInfoMedicoDTO(
                medicoRepository.findById(codigo).orElseThrow(() -> new Exception("No se encontró el medico que se quiere obtener"))
        );
    }

    /**
     * Lista todas las PQRS que estan con estado "En proceso" en la base de datos
     * @return
     * @throws Exception
     */
    @Override
    public List<PQRSDTOAdmin> listarPQRS() throws Exception {

        if (pqrsRepository.count() == 0) throw new Exception("No hay PQRS para listar");

        return pqrsRepository.findAllByEstadoPqrs_Estado("En proceso").stream().map(this::pqrsToPQRSDTOAdmin).collect(Collectors.toList());
    }

    /**
     * Responde una PQRS y devuelve el mensaje que se acaba de responder
     * @param mensajeUsuario
     * @return
     * @throws Exception
     */
    @Override
    public String responderPQRS(MensajeDTOUsuario mensajeUsuario) throws Exception {

        Mensaje mensaje = mensajeDTOToMensaje(mensajeUsuario);
        mensajeRepository.save(mensaje);

        return mensaje.getContenido();

    }

    /**
     * Obtiene la informacion de una PQRS en especifico con sus mensajes incluidos
     * @param idPQRS
     * @return
     * @throws Exception
     */
    @Transactional
    @Override
    public InfoPQRSDTO verDetallePQRS(Long idPQRS) throws Exception {
        return new InfoPQRSDTO(pqrsRepository.findById(idPQRS).orElseThrow(() -> new Exception("No se encontro la PQRS")));
    }

    /**
     * Lista todas las consultas que estan en la base de datos
     * @return
     * @throws Exception
     */
    @Override
    public List<ConsultaDTOAdmin> listarConsultas() throws Exception {

        if (consultaRepository.count() == 0) throw new Exception("No hay consultas para listar");

        return consultaRepository.findAll().stream().map(consulta -> new ConsultaDTOAdmin(consulta)).toList();
    }

    /**
     * crea un horario para un medico en particular
     * @param idMedico
     * @param horario
     * @return el id del horario que se acaba de crear
     */
    @Override
    public Long crearHorario(Long idMedico, HorarioDTOCrear horario) {

        HorarioAtencion horarioAtencion = horarioDTOToHorarioAtencion(horario);
        horarioAtencion.setMedico(medicoRepository.findById(idMedico).orElseThrow(() -> new RuntimeException("No se encontro el medico")));

        HorarioAtencion horarioAtencionCreado = horarioAtencionRepository.save(horarioAtencion);
        return horarioAtencionCreado.getId();
    }

    /**
     * Actualiza un horario de un medico en particular
     * @param idMedico
     * @param horario
     * @return el id del horario que se acaba de actualizar
     */
    @Override
    public Long actualizarHorario(Long idMedico, HorarioDTOActualizar horario) {

        HorarioAtencion horarioAtencion = horarioDTOActualizarToHorarioAtencion(horario);
        horarioAtencion.setMedico(medicoRepository.findById(idMedico).orElseThrow(() -> new RuntimeException("No se encontro el medico")));

        HorarioAtencion horarioAtencionActualizado = horarioAtencionRepository.save(horarioAtencion);
        return horarioAtencionActualizado.getId();

    }

    private Medico medicoDTOCrearToMedico(MedicoDTOCrear medico) throws Exception {

        return Medico.builder()
                .estaActivo(true)
                .cedula(medico.cedula())
                .nombre(medico.nombre())
                .email(medico.email())
                .password(medico.password())
                .ciudadResidencia(medico.ciudadResidencia())
                .telefono(medico.telefono())
                // TODO: .urlFotoPersonal(imagenesService.subirImagen())
                .especialidad(especialidadRepository.findById(medico.idEspecialidad()).orElseThrow(() -> new Exception("No se encontro la especialidad")))
                .build();
    }

    private Medico medicoDTOActualizarToMedico(MedicoDTOActualizar medico) throws Exception {

        return Medico.builder()
                .id(medico.id())
                .estaActivo(true)
                .cedula(medico.cedula())
                .nombre(medico.nombre())
                .email(medico.email())
                .password(medico.password())
                .ciudadResidencia(medico.ciudadResidencia())
                .telefono(medico.telefono())
                //TODO: .urlFotoPersonal(imagenesService.subirImagen())
                .especialidad(especialidadRepository.findById(medico.idEspecialidad()).orElseThrow(() -> new Exception("No se encontro la especialidad")))
                .build();
    }

    private MedicoDTOCrear medicoToMedicoDTOCrear(Medico medico) throws Exception {

        return new MedicoDTOCrear(
                medico.getCedula(),
                medico.getNombre(),
                medico.getEmail(),
                medico.getPassword(),
                medico.getCiudadResidencia(),
                medico.getTelefono(),
                medico.getUrlFotoPersonal(),
                medico.getEspecialidad().getId(),
                medico.getHorarios().stream().map(this::horarioAtencionToHorarioDTOCrear).collect(Collectors.toList())
        );
    }

    private HorarioAtencion horarioDTOActualizarToHorarioAtencion(HorarioDTOActualizar horario) {
        return HorarioAtencion.builder()
                        .id(horario.id())
                        .dia(DayOfWeek.of(horario.dia()))
                        .inicio(LocalTime.parse("%s:00".formatted(horario.horaInicio())))
                        .fin(LocalTime.parse("%s:00".formatted(horario.horaSalida())))
                        .build();
    }

    private HorarioDTOCrear horarioAtencionToHorarioDTOCrear(HorarioAtencion horario) {
        return new HorarioDTOCrear(
                (byte) horario.getDia().getValue(),
                horario.getInicio().toString(),
                horario.getFin().toString()
        );
    }

    private HorarioDTOActualizar horarioAtencionToHorarioDTOActualizar(HorarioAtencion horario) {
        return new HorarioDTOActualizar(
                horario.getId(),
                (byte) horario.getDia().getValue(),
                horario.getInicio().toString(),
                horario.getFin().toString()
        );
    }

    private HorarioAtencion horarioDTOToHorarioAtencion(HorarioDTOCrear horario) {
        return HorarioAtencion.builder()
                        .dia(DayOfWeek.of(horario.dia()))
                        .inicio(LocalTime.parse("%s:00".formatted(horario.horaInicio())))
                        .fin(LocalTime.parse("%s:00".formatted(horario.horaSalida())))
                        .build();
    }

    private InfoMedicoDTO medicoToInfoMedicoDTO(Medico medico) {
        return new InfoMedicoDTO(
                medico.getId(),
                medico.getNombre(),
                medico.getCedula(),
                medico.getCiudadResidencia(),
                medico.getEspecialidad().getNombre(),
                medico.getTelefono(),
                medico.getEmail(),
                medico.getHorarios().stream().map(this::horarioAtencionToHorarioDTOActualizar).collect(Collectors.toList())
        );
    }

    private MedicoDTOAdmin medicoToMedicoDTOAdmin(Medico medico) {
        return new MedicoDTOAdmin(
                medico.getId(),
                medico.getNombre(),
                medico.getUrlFotoPersonal(),
                medico.getEspecialidad().getNombre()
        );
    }

    private PQRSDTOAdmin pqrsToPQRSDTOAdmin(Pqrs pqrs) {
        return new PQRSDTOAdmin(
                pqrs.getId(),
                pqrs.getEstadoPqrs().getEstado(),
                pqrs.getFechaCreacion(),
                pqrs.getConsulta().getPaciente().getNombre()
        );
    }

    private Mensaje mensajeDTOToMensaje(MensajeDTOUsuario mensaje) {
        return Mensaje.builder()
                .contenido(mensaje.mensaje())
                .horaYFecha(LocalDateTime.now())
                .pqrs(pqrsRepository.findById(mensaje.idPqrs()).orElseThrow(() -> new RuntimeException("No se encontro la PQRS")))
                .usuario(usuarioRepository.findById(mensaje.idUsuario()).orElseThrow(() -> new RuntimeException("No se encontro el usuario")))
                .build();
    }
}
