package evovital.uniquindio.edu.co.dto.medico;

import evovital.uniquindio.edu.co.domain.Medico;

public record MedicoDTOAdmin(

        Long id,
        String nombre,
        String urlFoto,
        String especialidad,

        boolean estaActivo

) {

    public MedicoDTOAdmin(Medico medico) {
        this(
                medico.getId(),
                medico.getNombre(),
                medico.getUrlFotoPersonal(),
                medico.getEspecialidad().getNombre(),
                medico.isEstaActivo()
        );
    }

}
