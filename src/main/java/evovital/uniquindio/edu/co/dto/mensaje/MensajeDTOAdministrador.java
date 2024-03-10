package evovital.uniquindio.edu.co.dto.mensaje;

import evovital.uniquindio.edu.co.domain.Mensaje;

import java.time.LocalDateTime;

public record MensajeDTOAdministrador(

        Long idMensaje,
        String contenido,
        LocalDateTime horaYFecha,
        Long idUsuario,
        Long idPqrs

) {

    public MensajeDTOAdministrador(Mensaje mensaje) {
        this(
                mensaje.getId(),
                mensaje.getContenido(),
                mensaje.getHoraYFecha(),
                mensaje.getUsuario().getId(),
                mensaje.getPqrs().getId()
        );
    }

}
