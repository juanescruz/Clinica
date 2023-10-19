package evovital.uniquindio.edu.co.domain;

import evovital.uniquindio.edu.co.dto.medico.MedicoDTOPaciente;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
// @PrimaryKeyJoinColumn(name = "medico_id")
public class Medico extends Usuario implements Serializable {

    @Column(nullable = false, length = 31)
    private String ciudadResidencia;

    @Column(nullable = false, length = 31)
    private String telefono;

    @Lob
    private String urlFotoPersonal;

    @OneToMany(mappedBy = "medico")
    private List<Consulta> consultas;

    @JoinColumn
    @ManyToOne
    private Especialidad especialidad;

    // TODO: poner el está activo en el usuario y quitarlo de aquí
    @Column(nullable = false, length = 1)
    private boolean estaActivo;

    @OneToMany(mappedBy = "medico")
    private List<HorarioAtencion> horarios;

    @OneToMany(mappedBy = "medico")
    private List<DiaLibre> diasLibres;

    public MedicoDTOPaciente toMedicoDTOPaciente() {

        return new MedicoDTOPaciente(
                getId(),
                getNombre(),
                getEspecialidad().getNombre()
        );

    }
}
