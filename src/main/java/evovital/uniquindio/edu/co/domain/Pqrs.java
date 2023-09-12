package evovital.uniquindio.edu.co.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "pqrs")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pqrs implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne (mappedBy = "pqrs")
    private Consulta consulta; //TODO aqui va el mappedBy

    @ManyToOne
    private EstadoPqrs estadoPqrs;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    private String detalle;
}
