package evovital.uniquindio.edu.co.dto.horario;

import evovital.uniquindio.edu.co.domain.HorarioAtencion;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record HorarioDTO(

        @Pattern(regexp = "\\d+") Long id,
        @NotNull @Max(7) @Min(1) byte dia,
        @NotNull @Length(max = 5) // TODO @Pattern(regexp = "[1-9]:") maybe bad
        String horaInicio,
        @NotNull @Length(max = 5) // TODO @Pattern(regexp = "[1-9]:") maybe bad
        String horaSalida

) {

        public HorarioDTO(HorarioAtencion horarioAtencion){
                this(
                        horarioAtencion.getId(),
                        (byte) horarioAtencion.getDia().getValue(),
                        horarioAtencion.getInicio().toString(),
                        horarioAtencion.getFin().toString()
                );
        }

        public HorarioAtencion toEntity() {
                return HorarioAtencion.builder()
                        .id(id)
                        .dia(DayOfWeek.of(dia))
                        .inicio(LocalTime.parse(horaInicio))
                        .fin(LocalTime.parse(horaSalida))
                        .build();
        }
}
