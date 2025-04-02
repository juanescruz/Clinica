package evovital.uniquindio.edu.co.domain;

import evovital.uniquindio.edu.co.dto.pqrs.PQRSDTOPaciente;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SuperBuilder
public class Pqrs implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(nullable = false)
    @OneToOne
    private Consulta consulta;

    @JoinColumn(nullable = false)
    @ManyToOne
    private EstadoPqrs estadoPqrs;

    @Column(nullable = false)
    private LocalDate fechaCreacion;

    @OneToMany(mappedBy = "pqrs")
    private List<Mensaje> mensajes;

    @Column
    private int calificacion;

    public PQRSDTOPaciente toPQRSDTOPaciente() {

        return new PQRSDTOPaciente(
                getId(),
                getEstadoPqrs().getEstado(),
                getFechaCreacion()
        );

    }
}
