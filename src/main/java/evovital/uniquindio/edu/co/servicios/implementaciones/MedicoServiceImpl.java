package evovital.uniquindio.edu.co.servicios.implementaciones;

import evovital.uniquindio.edu.co.domain.AtencionConsulta;
import evovital.uniquindio.edu.co.domain.DiaLibre;
import evovital.uniquindio.edu.co.dto.atencionConsulta.AtencionConsultaDTOMedico;
import evovital.uniquindio.edu.co.dto.consulta.ConsultaDTOMedico;
import evovital.uniquindio.edu.co.dto.consulta.DetalleConsultaDTOMedico;
import evovital.uniquindio.edu.co.repositories.*;
import evovital.uniquindio.edu.co.servicios.especificaciones.MedicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicoServiceImpl implements MedicoService {

    private final ConsultaRepository consultaRepository;
    private final AtencionConsultaRepository atencionConsultaRepository;
    private final DiaLibreRepository diaLibreRepository;
    private final MedicoRepository medicoRepository;
    private final EstadoDiaLibreRepository estadoDiaLibreRepository;

    /**
     * Metodo que lista las consultas pendientes de un medico
     * @param idMedico
     * @return
     */
    @Override
    public List<ConsultaDTOMedico> listarConsultasPendientes(Long idMedico) {
        return consultaRepository.findAllByMedico_IdAAndAndEstadoConsulta_Estado(idMedico, "Pendiente").stream().map(ConsultaDTOMedico::new).toList();
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
    @Override
    public void atenderConsulta(Long idConsulta, AtencionConsultaDTOMedico atencionConsultaMedico) {

        AtencionConsulta atencionConsulta = atencionConsultaMedico.toAtencionConsulta();
        atencionConsulta.setConsulta(consultaRepository.findById(idConsulta).orElseThrow(() -> new RuntimeException("No se encontro la consulta")));

        atencionConsultaRepository.save(atencionConsulta);

    }

    /**
     * Metodo que lista las consultas de un paciente
     * @param idMedico
     * @param idPaciente
     * @return
     */
    @Override
    public List<ConsultaDTOMedico> listarConsultasPacientes(Long idMedico, Long idPaciente) {
        return consultaRepository.findAllByPaciente_IdAndMedico_Id(idPaciente, idMedico).stream().map(ConsultaDTOMedico::new).toList();
    }

    /**
     * Metodo que agrega un dia libre a un medico
     * @param idMedico
     * @param diaLibre
     * @return
     */
    @Override
    public boolean agendarDiaLibre(Long idMedico, LocalDate diaLibre) {

        if (diaLibre.isAfter(LocalDate.now()) || diaLibre.isEqual(LocalDate.now())) return false;
        if (medicoRepository.findById(idMedico).isEmpty()) return false;
        if (!consultaRepository.findAllByFechaYHoraAtencion(diaLibre.atStartOfDay()).isEmpty()) return false;

        DiaLibre diaLibreAux = DiaLibre.builder()
                .fecha(diaLibre)
                .estadoDiaLibre(
                    estadoDiaLibreRepository.findByEstado("Tomado").orElseThrow(() -> new RuntimeException("No se encontro el estado"))
                ).medico(
                    medicoRepository.findById(idMedico).orElseThrow(() -> new RuntimeException("No se encontro el medico"))
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
}
