package evovital.uniquindio.edu.co.dto.atencionConsulta;

import evovital.uniquindio.edu.co.domain.AtencionConsulta;

public record AtencionConsultaDTOPaciente(

        String sintomas,
        String diagnostico,
        String tratamiento,
        String notasMedicas

) {
    public AtencionConsultaDTOPaciente(AtencionConsulta atencionConsulta) {
        this(
                atencionConsulta.getSintomas(),
                atencionConsulta.getDiagnostico(),
                atencionConsulta.getTratamiento(),
                atencionConsulta.getNotasMedicas()
        );
    }
}
