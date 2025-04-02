package evovital.uniquindio.edu.co.dto.consulta;

import evovital.uniquindio.edu.co.domain.Consulta;
import evovital.uniquindio.edu.co.dto.atencionConsulta.AtencionConsultaDTOPaciente;
import evovital.uniquindio.edu.co.dto.medico.MedicoDTOPaciente;
import evovital.uniquindio.edu.co.dto.pqrs.PQRSDTOPaciente;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DetalleConsultaDTOPaciente(

        MedicoDTOPaciente medico,
        PQRSDTOPaciente pqrs,
        AtencionConsultaDTOPaciente atencionConsulta,
        LocalDate fechaCreacion,
        LocalDateTime fechaYHoraAtencion,
        String estadoConsulta,
        String motivo

) {
    public DetalleConsultaDTOPaciente(Consulta consulta) {

        this(
                new MedicoDTOPaciente( consulta.getMedico() ),
                consulta.getPqrs() == null ? null : new PQRSDTOPaciente( consulta.getPqrs() ),
                consulta.getAtencionConsulta() == null ? null : new AtencionConsultaDTOPaciente( consulta.getAtencionConsulta() ),
                consulta.getFechaCreacion(),
                consulta.getFechaYHoraAtencion(),
                consulta.getEstadoConsulta().getEstado(),
                consulta.getMotivo()
        );

    }
}
