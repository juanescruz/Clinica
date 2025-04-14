package evovital.uniquindio.edu.co.dto.consulta;

import evovital.uniquindio.edu.co.domain.Consulta;
import evovital.uniquindio.edu.co.dto.medico.MedicoDTOPaciente;

import java.time.LocalDateTime;

public record ConsultaDTOPaciente(

        MedicoDTOPaciente medico,
        String estadoConsulta,
        LocalDateTime fechaYHoraAtencion,

        String motivo

) {

    public ConsultaDTOPaciente(Consulta consulta) {

        this(
                new MedicoDTOPaciente(consulta.getMedico()),
                consulta.getEstadoConsulta().getEstado(),
                consulta.getFechaYHoraAtencion(),
                consulta.getMotivo()
        );
    }

    }
