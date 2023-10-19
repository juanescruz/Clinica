package evovital.uniquindio.edu.co.dto.paciente;

import evovital.uniquindio.edu.co.domain.Paciente;

import java.time.LocalDate;

public record DetallePacienteDTOMedico(

        LocalDate fechaNacimiento,
        String alergias,
        String tipoSangre,
        String urlFotoPersonal

) {
    public DetallePacienteDTOMedico(Paciente paciente) {
        this(
                paciente.getFechaNacimiento(),
                paciente.getAlergias(),
                paciente.getTipoSangre(),
                paciente.getUrlFotoPersonal()
        );
    }
}
