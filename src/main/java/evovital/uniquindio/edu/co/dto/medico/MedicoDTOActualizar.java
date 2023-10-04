package evovital.uniquindio.edu.co.dto.medico;

import evovital.uniquindio.edu.co.dto.horario.HorarioDTOActualizar;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record MedicoDTOActualizar(

        @NotNull @Pattern(regexp = "\\d+") Long id,
        @NotNull @Length(max = 255) @Pattern(regexp = "\\d+") String cedula,
        @NotNull @Length(max = 63) @Pattern(regexp = "^[a-zA-Z]+$") String nombre,

        @NotNull @Length(max = 31) @Email String email,

        @NotNull @Length(max = 31) String password,

        @NotNull @Length(max = 31) @Pattern(regexp = "^[a-zA-Z]+$") String ciudadResidencia,

        @NotNull @Length(max = 31) @Pattern(regexp = "\\d+") String telefono,

        @NotNull String fotoPersonal,

        @NotNull @Pattern(regexp = "\\d+") Long idEspecialidad,

        List<HorarioDTOActualizar> horarios

) {
}
