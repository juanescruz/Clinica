package evovital.uniquindio.edu.co.dto.consulta;

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
}
