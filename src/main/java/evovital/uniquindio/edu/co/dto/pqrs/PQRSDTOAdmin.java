package evovital.uniquindio.edu.co.dto.pqrs;

import java.time.LocalDate;

public record PQRSDTOAdmin(

        Long codigo,
        String estado,
        LocalDate fechaCreacion,
        String nombrePaciente

) {
}
