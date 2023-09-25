package evovital.uniquindio.edu.co.dto.consulta;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record InfoConsultaDTO(

        String idPaciente,
        String idMedico,
        LocalDate fechaCreacion,
        LocalDateTime fechaYHoraDeAtencion,
        String motivo

) {
}
