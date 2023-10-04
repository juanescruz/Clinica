package evovital.uniquindio.edu.co.dto.horario;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * este DTO representa el horario en el que el medico puede atender cada dia, se establecen la cantidad de dias que sean necesarios de una sola semana,
 * se sobreentiende que el medico atiende los mismos dias a la misma hora, semana tras semana, a menos que uno de los dias lo saque libre
 * @param dia el dia en numero del que se va a establecer el horario de atencion, desde el lunes (1) hasta el domingo (7)
 * @param horaInicio la hora a la que inicia el dia dado a atender el medico, en formato hh:mm iniciando desde 00:00 (media noche) hasta las 23:59 (un minuto antes de la media noche)
 * @param horaSalida la hora en la que el medico termina de atender a sus pacientes, en formato hh:mm iniciando desde 00:00 (media noche) hasta las 23:59 (un minuto antes de la media noche)
 */
public record HorarioDTOCrear(

        @NotNull @Max(7) @Min(1) byte dia,
        @NotNull @Length(max = 5) // TODO @Pattern(regexp = "[1-9]:") maybe bad
        String horaInicio,
        @NotNull @Length(max = 5) // TODO @Pattern(regexp = "[1-9]:") maybe bad
        String horaSalida

) {
}
