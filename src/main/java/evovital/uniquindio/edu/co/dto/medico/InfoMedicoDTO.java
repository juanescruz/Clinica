package evovital.uniquindio.edu.co.dto.medico;

import evovital.uniquindio.edu.co.domain.Medico;
import evovital.uniquindio.edu.co.dto.horario.HorarioDTO;

import java.util.List;

public record InfoMedicoDTO(

        Long codigo,
        String nombre,
        String cedula,
        String ciudad,
        String especialidad,
        String telefono,
        String correo,
        List<HorarioDTO> horarios

) {

    public InfoMedicoDTO(Medico medico) {
        this(
                medico.getId(),
                medico.getNombre(),
                medico.getCedula(),
                medico.getCiudadResidencia(),
                medico.getEspecialidad().getNombre(),
                medico.getTelefono(),
                medico.getEmail(),
                medico.getHorarios().stream().map(HorarioDTO::new).toList()
        );
    }

}
