package evovital.uniquindio.edu.co.dto.paciente;

import evovital.uniquindio.edu.co.domain.Paciente;

public record PacienteDTOMedico(

        String nombre

) {

    public PacienteDTOMedico(Paciente paciente) {
        this(
                paciente.getNombre()
        );
    }

}
