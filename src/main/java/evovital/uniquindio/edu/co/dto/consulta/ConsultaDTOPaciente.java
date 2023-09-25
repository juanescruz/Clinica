package evovital.uniquindio.edu.co.dto.consulta;

import evovital.uniquindio.edu.co.dto.medico.MedicoDTOPaciente;

import java.time.LocalDateTime;

public record ConsultaDTOPaciente(

        MedicoDTOPaciente medico,
        String estadoConsulta,
        LocalDateTime fechaYHoraAtencion

) {
}
