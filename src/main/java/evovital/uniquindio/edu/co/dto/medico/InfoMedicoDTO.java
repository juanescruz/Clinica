package evovital.uniquindio.edu.co.dto.medico;

import evovital.uniquindio.edu.co.dto.horario.HorarioDTO;

import java.util.List;

public record InfoMedicoDTO(

        int codigo,
        String nombre,
        String cedula,
        int codigoCiudad,
        int codigoEspecialidad,
        String telefono,
        String correo,
        List<HorarioDTO> horarios

) {
}
