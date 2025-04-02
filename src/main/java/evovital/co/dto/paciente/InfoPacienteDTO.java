package evovital.uniquindio.edu.co.dto.paciente;

import evovital.uniquindio.edu.co.domain.Paciente;

import java.time.LocalDate;

public record InfoPacienteDTO(

        String nombre,
        String cedula,
        String email,
        LocalDate fechaNacimiento,
        String telefono

) {

    public InfoPacienteDTO(Paciente paciente) {
        this(
                paciente.getNombre(),
                paciente.getCedula(),
                paciente.getEmail(),
                paciente.getFechaNacimiento(),
                paciente.getTelefono());
    }

}
