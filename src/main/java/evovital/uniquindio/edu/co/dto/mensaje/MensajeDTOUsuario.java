package evovital.uniquindio.edu.co.dto.mensaje;

import java.time.LocalDateTime;

public record MensajeDTOUsuario(

            String mensaje,
            Long idUsuario,
            LocalDateTime fechaYHora
) {
}
