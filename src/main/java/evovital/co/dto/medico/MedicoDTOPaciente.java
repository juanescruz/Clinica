package evovital.uniquindio.edu.co.dto.medico;

import evovital.uniquindio.edu.co.domain.Medico;

public record MedicoDTOPaciente(

        Long idMedico,
        String nombre,
        String especialidad
) {

    public MedicoDTOPaciente(Medico medico) {

        this(
                medico.getId(),
                medico.getNombre(),
                medico.getEspecialidad().getNombre()
        );
    }

}
