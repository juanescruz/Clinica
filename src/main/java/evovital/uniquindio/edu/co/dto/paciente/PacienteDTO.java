package evovital.uniquindio.edu.co.dto.paciente;

import java.time.LocalDate;

public record PacienteDTO(

        String nombre,
        String cedula,
        String email,
        String password,
        LocalDate fechaNacimiento,
        String alergias,
        String eps,
        String tipoSangre,
        String ciudadResidencia,
        String telefono,
        String fotoPersonal

) {
}
