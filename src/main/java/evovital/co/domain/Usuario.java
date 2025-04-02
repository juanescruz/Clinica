package evovital.co.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SuperBuilder
public abstract class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, length = 63)
    private String nombre;

    @Column(nullable = false, length = 31, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(length = 31)
    private String sal;

    @OneToMany(mappedBy = "usuario")
    private List<Mensaje> mensajes;

    @Column(nullable = false, unique = true)
    private String cedula;

    @Column(nullable = false, length = 1)
    private boolean estaActivo;
}
