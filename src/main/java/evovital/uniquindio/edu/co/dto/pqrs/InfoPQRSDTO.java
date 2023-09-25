package evovital.uniquindio.edu.co.dto.pqrs;

import evovital.uniquindio.edu.co.domain.Mensaje;

import java.time.LocalDateTime;
import java.util.List;

public record InfoPQRSDTO(

        Long idPQRS,
        String estado,
        Long idConsulta,
        String motivo,
        String nombrePaciente,
        LocalDateTime fecha,
        List<Mensaje> mensajes

) {
}
