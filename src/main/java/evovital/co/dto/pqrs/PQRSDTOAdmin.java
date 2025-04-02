package evovital.uniquindio.edu.co.dto.pqrs;

import evovital.uniquindio.edu.co.domain.Pqrs;

import java.time.LocalDate;

public record PQRSDTOAdmin(

        Long codigo,
        String estado,
        LocalDate fechaCreacion,
        String nombrePaciente

) {

    public PQRSDTOAdmin(Pqrs pqrs) {
        this(
                pqrs.getId(),
                pqrs.getEstadoPqrs().getEstado(),
                pqrs.getFechaCreacion(),
                pqrs.getConsulta().getPaciente().getNombre()
        );
    }

}
