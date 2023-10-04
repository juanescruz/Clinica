package evovital.uniquindio.edu.co.dto.medico;

import evovital.uniquindio.edu.co.dto.horario.HorarioDTOCrear;

import java.util.List;

public record InfoMedicoDTO(

        Long codigo,
        String nombre,
        String cedula,
        String ciudad,
        String especialidad,
        String telefono,
        String correo,
        List<HorarioDTOCrear> horarios

) {
}
