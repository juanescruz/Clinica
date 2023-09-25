package evovital.uniquindio.edu.co.dto.consulta;

import evovital.uniquindio.edu.co.dto.paciente.PacienteDTOMedico;

import java.time.LocalDateTime;

public record ConsultaDTOMedico(

        Long idConsulta,
        PacienteDTOMedico paciente,
        LocalDateTime fechaYHoraAtencion,
        String motivo

) {
}
