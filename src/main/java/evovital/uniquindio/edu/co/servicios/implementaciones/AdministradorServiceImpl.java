package evovital.uniquindio.edu.co.servicios.implementaciones;

import evovital.uniquindio.edu.co.domain.HorarioAtencion;
import evovital.uniquindio.edu.co.domain.Medico;
import evovital.uniquindio.edu.co.domain.Mensaje;
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
import evovital.uniquindio.edu.co.repositories.*;
import evovital.uniquindio.edu.co.servicios.especificaciones.AdministradorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final PacienteRepository pacienteRepository;

    /**
     * Crea un medico en la base de datos con la informacion necesaria y devuelve el id del medico que se acabo de crear
     * @param medico
     * @return
     * @throws Exception
     */
    @Override
    public Long crearMedico(MedicoDTO medico) throws Exception {

        usuarioRepository.findByCedula(medico.cedula()).ifPresent(
                usuario -> new RuntimeException("ya existe un usuario con esta cedula")
        );

        usuarioRepository.findByEmail(medico.email()).ifPresent(
                usuario -> new RuntimeException("ya existe un usuario con ese email")
        );

        Medico medicoAux = medico.toEntity();
        medicoAux.setEstaActivo(true);
        medicoAux.setEspecialidad( especialidadRepository.findById(medico.idEspecialidad()).orElseThrow( () -> new RuntimeException("no existe la especialidad solicitada") ));
        medicoAux.setPassword(new BCryptPasswordEncoder().encode(medico.password()));

        Medico medicoRegistrado = medicoRepository.save(medicoAux);

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
    public Long actualizarMedico(MedicoDTO medico) throws Exception {

        Medico medicoEncontrado = medicoRepository.findByIdAndEstaActivoTrue(medico.id()).orElseThrow(
                () -> new Exception("No se encontró el medico que se quiere actualizar")
        );

        if (!medicoEncontrado.getCedula().equals(medico.cedula()))
            usuarioRepository.findByCedula(medico.cedula()).ifPresent(m -> {
                throw new RuntimeException("Ya existe un medico con esa cedula");
            });

        if (!medicoEncontrado.getEmail().equals(medico.email()))
            usuarioRepository.findByEmail(medico.email()).ifPresent(m -> {
                throw new RuntimeException("Ya existe un medico con ese email");
            });

        Medico medicoAux = medico.toEntity();
        medicoAux.setEspecialidad( especialidadRepository.findById(medico.idEspecialidad()).orElseThrow( () -> new RuntimeException("no existe la especialidad solicitada") ) );
        medicoAux.setPassword( medicoEncontrado.getPassword() );
        medicoAux.setEstaActivo( true );
        Medico medicoActualizado = medicoRepository.save(medicoAux);

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
    @Transactional
    @Override
    public MedicoDTO eliminarMedico(Long codigo) throws Exception {

        Medico medico = medicoRepository.findById(codigo).orElseThrow(() -> new Exception("No se encontró el medico que se quiere eliminar"));
        medico.setEstaActivo(false);

        return new MedicoDTO(medicoRepository.save(medico));
    }

    /**
     * Lista todos los medicos que estan en la base de datos
     * @return
     * @throws Exception
     */
    @Override
    public List<MedicoDTOAdmin> listarMedicos() throws Exception {

        if (medicoRepository.findAll().stream().count() == 0) throw new Exception("No hay medicos para listar");

        return medicoRepository.findAll().stream().map(MedicoDTOAdmin::new).toList();
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
        return new InfoMedicoDTO( medicoRepository.findById(codigo).orElseThrow(
                () -> new RuntimeException("No se encuentra el medico que se quiere obtener")
        ) );
    }

    /**
     * Lista todas las PQRS que estan con estado "En proceso" en la base de datos
     * @return
     * @throws Exception
     */
    @Override
    public List<PQRSDTOAdmin> listarPQRS() throws Exception {


        if (pqrsRepository.findAll().stream().count() == 0) throw new Exception("No hay PQRS para listar");

        return pqrsRepository.findAll().stream().map(PQRSDTOAdmin::new).collect(Collectors.toList());
    }

    /**
     * Responde una PQRS y devuelve el id del mensaje que se acaba de responder
     * @param mensajeUsuario
     * @return
     * @throws Exception
     */
    @Override
    public Long responderPQRS(MensajeDTOUsuario mensajeUsuario) throws Exception {

        Mensaje mensaje = mensajeUsuario.toEntity();
        mensaje.setUsuario(usuarioRepository.findById(mensajeUsuario.idUsuario()).orElseThrow(() -> new RuntimeException("No se encontró el usuario")));
        mensaje.setPqrs(pqrsRepository.findById(mensajeUsuario.idPqrs()).orElseThrow(() -> new RuntimeException("No se encontró el pqrs")));

        return mensajeRepository.save(mensaje).getId();
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

        return consultaRepository.findAll().stream().map(ConsultaDTOAdmin::new).toList();
    }

    /**
     * crea un horario para un medico en particular
     * @param idMedico
     * @param horario
     * @return el id del horario que se acaba de crear
     */
    @Override
    public Long crearHorario(Long idMedico, HorarioDTO horario) {

        HorarioAtencion horarioAtencion = horario.toEntity();
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
    public Long actualizarHorario(Long idMedico, HorarioDTO horario) {

        HorarioAtencion horarioAtencion = horario.toEntity();
        horarioAtencion.setMedico(medicoRepository.findById(idMedico).orElseThrow(() -> new RuntimeException("No se encontro el medico")));

        HorarioAtencion horarioAtencionActualizado = horarioAtencionRepository.save(horarioAtencion);
        return horarioAtencionActualizado.getId();

    }

    /**
     * Lista todos los pacientes que estan en la base de datos
     * @return
     */
    @Override
    public List<InfoPacienteDTO> listarPacientes() {
        return pacienteRepository.findAll().stream().map(InfoPacienteDTO::new).toList();
    }

    /**
     * Obtiene la informacion de un paciente en especifico
     * @param idPaciente
     * @return
     */
    @Override
    public PacienteDTO obtenerDetallePaciente(Long idPaciente) {
        return pacienteRepository.findById(idPaciente).map(PacienteDTO::new).orElseThrow(() -> new RuntimeException("No se encontro el paciente"));
    }

}
