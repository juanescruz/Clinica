package evovital.uniquindio.edu.co.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

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

    @Column(nullable = false, length = 31)
    private String email;

    @Column(nullable = false, length = 31)
    private String password;

    @Column(length = 31)
    private String sal;

    @OneToMany(mappedBy = "usuario")
    private List<Mensaje> mensajes;

    @Column(nullable = false)
    private String cedula;

}
