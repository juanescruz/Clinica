package evovital.uniquindio.edu.co.dto.mensaje;

import evovital.uniquindio.edu.co.domain.Mensaje;

import java.time.LocalDateTime;

public record MensajeDTOUsuario(

            String mensaje,
            Long idUsuario,
            Long idPqrs
) {
    public Mensaje toEntity() {

        return Mensaje.builder()
                .contenido(mensaje)
                .horaYFecha(LocalDateTime.now())
                .build();
    }
}
