package evovital.uniquindio.edu.co.dto.cita;

import java.time.LocalDateTime;

public record ConsultaDTOAdmin(

        int codigoCita,
        String nombrePaciente,
        String nombreMedico,
        LocalDateTime fecha,
        String motivo

) {
}
