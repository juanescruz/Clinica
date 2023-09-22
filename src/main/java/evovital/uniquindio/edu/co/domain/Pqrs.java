package evovital.uniquindio.edu.co.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
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

    @JoinColumn(nullable = false)
    @OneToOne (mappedBy = "pqrs")
    private Consulta consulta;

    @JoinColumn(nullable = false)
    @ManyToOne
    private EstadoPqrs estadoPqrs;

    @Column(nullable = false)
    private LocalDate fechaCreacion;

}
