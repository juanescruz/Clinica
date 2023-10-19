package evovital.uniquindio.edu.co.dto.pqrs;

import evovital.uniquindio.edu.co.domain.Pqrs;

import java.time.LocalDate;

public record PQRSDTOPaciente(

        Long codigo,
        String estado,
        LocalDate fecha

) {

    public PQRSDTOPaciente(Pqrs pqrs) {
        this(pqrs.getId(), pqrs.getEstadoPqrs().getEstado(), pqrs.getFechaCreacion());
    }

}
