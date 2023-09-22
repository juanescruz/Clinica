package evovital.uniquindio.edu.co.dto.pqrs;

import java.time.LocalDate;

public record PQRSDTOAdmin(

        int codigo,
        String estado,
        LocalDate fechaYHora,
        String nombrePaciente

) {
}
