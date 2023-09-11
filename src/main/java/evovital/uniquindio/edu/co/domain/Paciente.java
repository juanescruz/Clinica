package evovital.uniquindio.edu.co.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "pacientes")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@PrimaryKeyJoinColumn(name = "paciente_id")
public class Paciente extends Usuario implements Serializable {

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
    private String alergias;
    private String eps;

    @Column(name = "tipo_sangre")
    private String tipoSangre;

    @Column(name = "nombre_completo")
    private String nombreCompleto;

    @Column(name = "ciudad_residencia")
    private String ciudadResidencia;

    private String telefono;

    @Column(name = "foto_personal")
    private String fotoPersonal;

    @OneToMany(mappedBy = "paciente")
    private List<Consulta> consultas;

}
