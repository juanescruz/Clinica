package evovital.uniquindio.edu.co.dto.paciente;

import evovital.uniquindio.edu.co.domain.Paciente;

import java.time.LocalDate;

public record PacienteDTOPaciente(

        String nombre,
        String cedula,
        String email,
        LocalDate fechaNacimiento,
        String alergias,
        String urlFotoPersonal,
        String eps,
        String tipoSangre,
        String ciudadResidencia,
        String telefono

) {

    public PacienteDTOPaciente(Paciente paciente) {
        this(
                paciente.getNombre(),
                paciente.getCedula(),
                paciente.getEmail(),
                paciente.getFechaNacimiento(),
                paciente.getAlergias(),
                paciente.getUrlFotoPersonal(),
                paciente.getEps(),
                paciente.getTipoSangre(),
                paciente.getCiudadResidencia(),
                paciente.getTelefono());
    }

    public Paciente toEntity() {

        return Paciente.builder()
                .alergias(alergias)
                .eps(eps)
                .ciudadResidencia(ciudadResidencia)
                .fechaNacimiento(fechaNacimiento)
                .nombre(nombre)
                .urlFotoPersonal(urlFotoPersonal)
                .telefono(telefono)
                .tipoSangre(tipoSangre)
                .cedula(cedula)
                .email(email)
                .build();

    }

}
