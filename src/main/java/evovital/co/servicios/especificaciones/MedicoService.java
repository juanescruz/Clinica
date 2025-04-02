package evovital.uniquindio.edu.co.servicios.especificaciones;

import evovital.uniquindio.edu.co.dto.atencionConsulta.AtencionConsultaDTOMedico;
import evovital.uniquindio.edu.co.dto.consulta.ConsultaDTOMedico;
import evovital.uniquindio.edu.co.dto.consulta.DetalleConsultaDTOMedico;
import evovital.uniquindio.edu.co.dto.consulta.MetodoPagoDTO;
import evovital.uniquindio.edu.co.dto.paciente.PacienteDTO;

import java.time.LocalDate;
import java.util.List;

public interface MedicoService {

    List<ConsultaDTOMedico> listarConsultasPendientes(Long idMedico);

    DetalleConsultaDTOMedico verDetalleConsulta(Long idConsulta);

    void atenderConsulta(Long idConsulta, AtencionConsultaDTOMedico atencionConsultaMedico) throws Exception;

    List<ConsultaDTOMedico> listarConsultasPaciente(Long idMedico, Long idPaciente);

    boolean agendarDiaLibre(Long idMedico, LocalDate diaLibre) throws Exception;

    List<ConsultaDTOMedico> listarConsultasRealizadasMedico(Long idMedico);

    Boolean hacerFactura(Long idConsulta, MetodoPagoDTO pago);

    Boolean cancelarConsulta(Long idConsulta);

    List<ConsultaDTOMedico> listarTodasConsultas(Long idMedico);
    PacienteDTO getInfoPaciente(Long idPaciente);
}
