package evovital.uniquindio.edu.co.dto.pqrs;

import evovital.uniquindio.edu.co.domain.Pqrs;
import evovital.uniquindio.edu.co.dto.mensaje.MensajeDTOAdministrador;

import java.time.LocalDate;
import java.util.List;

public record InfoPQRSDTO(

        Long idPQRS,
        String estado,
        Long idConsulta,
        String nombrePaciente,
        LocalDate fecha,
        List<MensajeDTOAdministrador> mensajes

) {
    public InfoPQRSDTO(Pqrs pqrs) {
        this(
                pqrs.getId(),
                pqrs.getEstadoPqrs().getEstado(),
                pqrs.getConsulta().getId(),
                pqrs.getConsulta().getPaciente().getNombre(),
                pqrs.getFechaCreacion(),
                pqrs.getMensajes().stream().map(MensajeDTOAdministrador::new).toList()
        );
    }
}
