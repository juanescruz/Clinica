package evovital.uniquindio.edu.co.dto.pqrs;

import evovital.uniquindio.edu.co.domain.Pqrs;
import evovital.uniquindio.edu.co.dto.mensaje.MensajeDTOUsuario;

import java.time.LocalDate;

public record PQRSDTOPacienteReq(

        Long idConsulta,
        MensajeDTOUsuario mensaje

) {
    public Pqrs toEntity() {

        return Pqrs.builder()
                .fechaCreacion(LocalDate.now())
                .build();

    }
}
