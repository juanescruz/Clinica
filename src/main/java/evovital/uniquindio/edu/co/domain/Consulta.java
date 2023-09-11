package evovital.uniquindio.edu.co.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "consultas")
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

    @ManyToOne
    private Medico medico;

    @OneToOne(mappedBy = "consulta")
    private Pqrs pqrs;

    @OneToOne(mappedBy = "consulta")
    private AtencionConsulta atencionConsulta;

    private String respuesta;

    @ManyToOne
    private Paciente paciente;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @Column(name = "fecha_y_hora_atencion")
    private LocalDateTime fechaYHoraAtencion;

    @ManyToOne
    private EstadoConsulta estadoConsulta;

    private String motivo;

}
