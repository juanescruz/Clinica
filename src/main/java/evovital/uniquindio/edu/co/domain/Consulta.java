package evovital.uniquindio.edu.co.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Consulta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Medico medico;

    @JoinColumn(nullable = false)
    @OneToOne
    private Pqrs pqrs;

    @OneToOne
    private AtencionConsulta atencionConsulta;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Paciente paciente;

    @Column(nullable = false)
    private LocalDate fechaCreacion;

    @Column(nullable = false)
    private LocalDateTime fechaYHoraAtencion;

    @JoinColumn(nullable = false)
    @ManyToOne
    private EstadoConsulta estadoConsulta;

    @Lob
    @Column(nullable = false)
    private String motivo;

}
