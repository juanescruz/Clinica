package evovital.uniquindio.edu.co.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AtencionConsulta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @JoinColumn(nullable = false)
    @OneToOne
    private Consulta consulta;

    @Column(nullable = false)
    private String sintomas;

    @Column(nullable = false)
    private String diagnostico;

    @Column(nullable = false)
    private String tratamiento;

    @Column(nullable = false)
    private String notasMedicas;

}
