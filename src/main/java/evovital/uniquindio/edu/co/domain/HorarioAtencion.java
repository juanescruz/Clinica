package evovital.uniquindio.edu.co.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Time;
import java.time.DayOfWeek;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class HorarioAtencion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private DayOfWeek dia;

    @Column(nullable = false)
    private Time inicio;

    @Column(nullable = false)
    private Time fin;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Medico medico;
}
