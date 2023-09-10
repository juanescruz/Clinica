package evovital.uniquindio.edu.co.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import org.springframework.cglib.core.Local;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Consulta implements Serializable {

    @Id
    private long id;

    @ManyToOne
    private Medico medico;
    private Pqrs pqrs;
    private String respuesta;
    private Paciente paciente;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaAtencion;
    private LocalDateTime horaAtencion;
    private EstadoConsulta estadoConsulta;
    private String motivo;

}
