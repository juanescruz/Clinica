package evovital.uniquindio.edu.co.dto.medico;

import evovital.uniquindio.edu.co.dto.horario.HorarioDTO;

import java.util.List;

public record MedicoDTO(

        String cedula,
        String nombre,

        String email,

        String password,

        String ciudadResidencia,

        String telefono,

        String fotoPersonal,

        String nombreEspecialidad,

        List<HorarioDTO> horarios


) {
}
