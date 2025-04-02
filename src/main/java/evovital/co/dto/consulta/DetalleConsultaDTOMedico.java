package evovital.uniquindio.edu.co.dto.consulta;

import evovital.uniquindio.edu.co.domain.Consulta;
import evovital.uniquindio.edu.co.dto.paciente.DetallePacienteDTOMedico;

import java.time.LocalDateTime;

public record DetalleConsultaDTOMedico(

        Long idConsulta,
        LocalDateTime fechaYHoraAtencion,
        DetallePacienteDTOMedico paciente,
        String motivo

) {
    public DetalleConsultaDTOMedico(Consulta consulta) {
        this(
                consulta.getId(),
                consulta.getFechaYHoraAtencion(),
                new DetallePacienteDTOMedico(consulta.getPaciente()),
                consulta.getMotivo()
        );
    }
}
