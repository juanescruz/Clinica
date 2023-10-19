package evovital.uniquindio.edu.co.dto.atencionConsulta;

import evovital.uniquindio.edu.co.domain.AtencionConsulta;

public record AtencionConsultaDTOMedico(

        String sintomas,
        String diagnostico,
        String tratamiento,
        String notasMedicas

) {
    public AtencionConsulta toAtencionConsulta() {

        return AtencionConsulta.builder()
                .sintomas(sintomas)
                .diagnostico(diagnostico)
                .tratamiento(tratamiento)
                .notasMedicas(notasMedicas)
                .build();

    }
}
