package evovital.uniquindio.edu.co.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EstadoConsulta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, length = 31)
    private String estado;

}
