package evovital.uniquindio.edu.co.dto.consulta;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ConsultaDTOAdmin(

        int codigoCita,
        String idPaciente,
        String idMedico,
        LocalDate fechaCreacion,
        LocalDateTime fechaYHoraDeCreacion,
        String motivo

) {
}
