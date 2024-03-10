package evovital.uniquindio.edu.co.dto.consulta;

import evovital.uniquindio.edu.co.domain.Consulta;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record InfoConsultaDTO(

        Long idPaciente,
        Long idMedico,
        LocalDateTime fechaYHoraDeAtencion,
        String motivo

) {
    public Consulta toEntity() {

        return Consulta.builder()
                .fechaCreacion(LocalDate.now())
                .fechaYHoraAtencion(fechaYHoraDeAtencion)
                .motivo(motivo)
                .build();

    }
}
