package evovital.uniquindio.edu.co.domain;

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
    @Column(nullable = false)
    private String urlFotoPersonal;

    @OneToMany(mappedBy = "medico")
    private List<Consulta> consultas;

    @JoinColumn
    @ManyToOne
    private Especialidad especialidad;

    @Column(nullable = false, length = 1)
    private boolean estaActivo;

    @OneToMany(mappedBy = "medico")
    private List<HorarioAtencion> horarios;

    @ManyToMany(mappedBy = "medicos")
    private List<DiaLibre> diasLibres;

}
