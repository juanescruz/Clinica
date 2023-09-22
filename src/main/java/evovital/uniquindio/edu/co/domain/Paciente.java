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
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
// @PrimaryKeyJoinColumn(name = "paciente_id")
public class Paciente extends Usuario implements Serializable {

    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @Lob
    private String alergias;

    @Column(nullable = false, length = 31)
    private String eps;

    @Column(nullable = false, length = 3)
    private String tipoSangre;

    @Column(nullable = false, length = 63)
    private String ciudadResidencia;

    @Column(nullable = false, length = 31)
    private String telefono;

    @Lob
    @Column(nullable = false)
    private String fotoPersonal;

    @OneToMany(mappedBy = "paciente")
    private List<Consulta> consultas;

}
