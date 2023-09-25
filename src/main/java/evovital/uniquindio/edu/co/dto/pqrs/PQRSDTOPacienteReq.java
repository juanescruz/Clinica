package evovital.uniquindio.edu.co.dto.pqrs;

import evovital.uniquindio.edu.co.dto.mensaje.MensajeDTOUsuario;

import java.time.LocalDate;

public record PQRSDTOPacienteReq(

        Long idConsulta,
        LocalDate fechaCreacion,
        Long idPaciente,
        MensajeDTOUsuario mensaje

) {
}
