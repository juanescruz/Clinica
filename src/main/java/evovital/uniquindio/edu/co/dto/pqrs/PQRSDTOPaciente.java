package evovital.uniquindio.edu.co.dto.pqrs;

import java.time.LocalDateTime;

public record PQRSDTOPaciente(

        int codigo,
        String estado,
        LocalDateTime fechaYHora

) {
}
