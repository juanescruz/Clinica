package evovital.uniquindio.edu.co.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SuperBuilder
public class EstadoPqrs implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, length = 31)
    private String estado;

    @OneToMany(mappedBy = "estadoPqrs")
    private List<Pqrs> pqrs;

}