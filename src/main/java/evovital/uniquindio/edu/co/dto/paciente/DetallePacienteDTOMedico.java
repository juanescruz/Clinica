package evovital.uniquindio.edu.co.dto.paciente;

import java.time.LocalDate;

public record DetallePacienteDTOMedico(

        LocalDate fechaNacimiento,
        String alergias,
        String tipoSangre,
        String urlFotoPersonal

) {
}
