package evovital.uniquindio.edu.co.dto.medico;

import evovital.uniquindio.edu.co.domain.Medico;
import evovital.uniquindio.edu.co.dto.horario.HorarioDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record MedicoDTO(

        @Pattern(regexp = "\\d+") Long id,
        @NotNull @Length(max = 255) @Pattern(regexp = "\\d+") String cedula,
        @NotNull @Length(max = 63) @Pattern(regexp = "^[a-zA-Z]+$") String nombre,

        @NotNull @Length(max = 31) @Email String email,

        @Length(max = 31) String password,

        @NotNull @Length(max = 31) @Pattern(regexp = "^[a-zA-Z]+$") String ciudadResidencia,

        @NotNull @Length(max = 31) @Pattern(regexp = "\\d+") String telefono,

        @NotNull @Pattern(regexp = "\\d+") Long idEspecialidad,

        List<HorarioDTO> horarios

) {

    public MedicoDTO(Medico medico) {

        this(
                medico.getId(),
                medico.getCedula(),
                medico.getNombre(),
                medico.getEmail(),
                medico.getPassword(),
                medico.getCiudadResidencia(),
                medico.getTelefono(),
                medico.getEspecialidad().getId(),
                medico.getHorarios().stream().map(HorarioDTO::new).toList()
        );

    }

    public Medico toEntity() {

        return Medico.builder()
                .id(id)
                .cedula(cedula)
                .nombre(nombre)
                .email(email)
                .password(password)
                .ciudadResidencia(ciudadResidencia)
                .telefono(telefono)
                .build();
    }

}
