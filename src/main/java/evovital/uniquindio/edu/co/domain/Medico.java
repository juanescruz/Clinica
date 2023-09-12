package evovital.uniquindio.edu.co.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "medico_id")
public class Medico extends Usuario implements Serializable {

    @Column(name = "nombre_completo")
    private String nombreCompleto;

    @Column(name = "ciudad_residencia")
    private String ciudadResidencia;

    private String telefono;

    @Column(name = "foto_personal")
    private String fotoPersonal;

    @OneToMany(mappedBy = "medico")
    private List<Consulta> consultas;

    @ManyToOne
    private Especialidad especialidad;

    private boolean estaActivo;

    @OneToMany(mappedBy = "medico")
    private List<HorarioAtencion> horarios;

    @ManyToMany(mappedBy = "medicos")
    private List<DiaLibre> diasLibres;

}
