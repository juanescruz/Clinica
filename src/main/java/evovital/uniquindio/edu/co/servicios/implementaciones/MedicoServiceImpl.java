package evovital.uniquindio.edu.co.servicios.implementaciones;

import evovital.uniquindio.edu.co.domain.AtencionConsulta;
import evovital.uniquindio.edu.co.domain.Consulta;
import evovital.uniquindio.edu.co.domain.DiaLibre;
import evovital.uniquindio.edu.co.dto.atencionConsulta.AtencionConsultaDTOMedico;
import evovital.uniquindio.edu.co.dto.consulta.ConsultaDTOMedico;
import evovital.uniquindio.edu.co.dto.consulta.DetalleConsultaDTOMedico;
import evovital.uniquindio.edu.co.dto.consulta.MetodoPagoDTO;
import evovital.uniquindio.edu.co.repositories.*;
import evovital.uniquindio.edu.co.servicios.especificaciones.MedicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicoServiceImpl implements MedicoService {

    private final ConsultaRepository consultaRepository;
    private final AtencionConsultaRepository atencionConsultaRepository;
    private final DiaLibreRepository diaLibreRepository;
    private final MedicoRepository medicoRepository;
    private final EstadoDiaLibreRepository estadoDiaLibreRepository;
    private final EstadoConsultaRepository estadoConsultaRepository;

    /**
     * Metodo que lista las consultas pendientes de un medico
     * @param idMedico
     * @return
     */
    @Override
    public List<ConsultaDTOMedico> listarConsultasPendientes(Long idMedico) {
        return consultaRepository.findAllByMedico_IdAndEstadoConsulta_Estado(idMedico, "Pendiente").stream().map(ConsultaDTOMedico::new).toList();
    }

    /**
     * Metodo que trae el detalle de una consulta
     * @param idConsulta
     * @return
     */
    @Override
    public DetalleConsultaDTOMedico verDetalleConsulta(Long idConsulta) {
        return new DetalleConsultaDTOMedico(consultaRepository.findById(idConsulta).orElseThrow(() -> new RuntimeException("No se encontro la consulta")));
    }

    /**
     * Metodo que atiende una consulta
     * @param idConsulta
     * @param atencionConsultaMedico
     */
    @Transactional
    @Override
    public void atenderConsulta(Long idConsulta, AtencionConsultaDTOMedico atencionConsultaMedico) throws Exception {

        AtencionConsulta atencionConsulta = atencionConsultaMedico.toEntity();
        Consulta consulta = consultaRepository.findById(idConsulta).orElseThrow(() -> new RuntimeException("No se encontro la consulta"));

        if (consulta.getMedico().getHorarios().stream().noneMatch(horarioAtencion -> horarioAtencion.getDia().equals(LocalDate.now().getDayOfWeek()) &&
                horarioAtencion.getInicio().isBefore(LocalTime.now()) &&
                horarioAtencion.getFin().isAfter(LocalTime.now())
        )) throw new Exception("El medico no atiende en el horario seleccionado");

        atencionConsulta.setConsulta( consulta );
        consulta.setEstadoConsulta( estadoConsultaRepository.findByEstado("Atendida").orElseThrow(() -> new Exception("Estado no encontrado")));
        atencionConsultaRepository.save(atencionConsulta);
        consultaRepository.save(consulta);
    }

    /**
     * Metodo que lista las consultas de un paciente
     * @param idMedico
     * @param idPaciente
     * @return
     */
    @Override
    public List<ConsultaDTOMedico> listarConsultasPaciente(Long idMedico, Long idPaciente) {
        return consultaRepository.findAllByPaciente_IdAndMedico_Id(idPaciente, idMedico).stream().map(ConsultaDTOMedico::new).toList();
    }

    /**
     * Metodo que agrega un dia libre a un medico
     * @param idMedico
     * @param diaLibre
     * @return
     */
    @Override
    public boolean agendarDiaLibre(Long idMedico, LocalDate diaLibre) throws Exception {

        medicoRepository.findByIdAndEstaActivoTrue(idMedico).orElseThrow(() -> new Exception("El medico no fue encontrado"));
        if (diaLibre.isBefore(LocalDate.now()) || diaLibre.isEqual(LocalDate.now())) throw new Exception("Debe solicitar los dias libres al menos con un dia de anticipacion");
        if (!consultaRepository.findAllByFechaYHoraAtencionAndEstadoConsulta_Estado(diaLibre.atStartOfDay(), "Pendiente").isEmpty()) throw new Exception("El medico ya tiene citas asignadas ese dia");

        DiaLibre diaLibreAux = DiaLibre.builder()
                .fecha(diaLibre)
                .estadoDiaLibre(
                    estadoDiaLibreRepository.findByEstado("Tomado").orElseThrow(() -> new RuntimeException("No se encontro el estado"))
                ).medico(
                    medicoRepository.findByIdAndEstaActivoTrue(idMedico).orElseThrow(() -> new RuntimeException("No se encontro el medico"))
                ).build();

        diaLibreRepository.save(diaLibreAux);
        return true;

    }

    /**
     * Metodo que lista las consultas realizadas por un medico
     * @param idMedico
     * @return
     */
    @Override
    public List<ConsultaDTOMedico> listarConsultasRealizadasMedico(Long idMedico) {
        return consultaRepository.findAllByEstadoConsulta_EstadoAndMedico_Id("Atendida", idMedico).stream().map(ConsultaDTOMedico::new).toList();
    }

    @Override
    public Boolean hacerFactura(Long idConsulta, MetodoPagoDTO pago) {

        Consulta consulta = consultaRepository.findById(idConsulta).orElseThrow(() -> new RuntimeException("No se encontro la consulta"));
        consulta.setMetodoPago(pago.toEntity());
        consultaRepository.save(consulta);
        return true;

    }

    @Override
    public Boolean cancelarConsulta(Long idConsulta) {

        Consulta consulta = consultaRepository.findById(idConsulta).orElseThrow(() -> new RuntimeException("No se encontro la consulta"));
        consulta.setEstadoConsulta(estadoConsultaRepository.findByEstado("Cancelada").orElseThrow(() -> new RuntimeException("No se encontro el estado")));
        consultaRepository.save(consulta);
        return true;
    }

    @Override
    public List<ConsultaDTOMedico> listarTodasConsultas(Long idMedico) {
        return consultaRepository.findAllByMedico_Id(idMedico).stream().map(ConsultaDTOMedico::new).toList();
    }
}
