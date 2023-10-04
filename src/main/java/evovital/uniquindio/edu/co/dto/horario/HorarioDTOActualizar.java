package evovital.uniquindio.edu.co.dto.horario;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record HorarioDTOActualizar(

        @NotNull @Pattern(regexp = "\\d+") Long id,
        @NotNull @Max(7) @Min(1) byte dia,
        @NotNull @Length(max = 5) // TODO @Pattern(regexp = "[1-9]:") maybe bad
        String horaInicio,
        @NotNull @Length(max = 5) // TODO @Pattern(regexp = "[1-9]:") maybe bad
        String horaSalida

) {
}
