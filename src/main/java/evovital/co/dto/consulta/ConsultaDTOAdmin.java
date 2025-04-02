package evovital.uniquindio.edu.co.dto.consulta;

import evovital.uniquindio.edu.co.domain.Consulta;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ConsultaDTOAdmin(

        Long idCita,
        Long idPaciente,
        Long idMedico,
        LocalDate fechaCreacion,
        LocalDateTime fechaYHoraDeAtencion,
        String motivo

) {
    public ConsultaDTOAdmin(Consulta consulta) {
        this(
                consulta.getId(),
                consulta.getPaciente().getId(),
                consulta.getMedico().getId(),
                consulta.getFechaCreacion(),
                consulta.getFechaYHoraAtencion(),
                consulta.getMotivo()
        );
    }
}
