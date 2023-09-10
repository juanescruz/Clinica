package evovital.uniquindio.edu.co.domain;

import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.List;

public class Paciente {

    @Id
    private long administradorId;
    private String nombre;
    private String email;
    private String contraseña;
    private String sal;
    private String ciudadResidencia;
    private String telefono;
    private String fotoPersonal;
    private List<Consulta> consultas;
    private LocalDateTime fechaNacimiento;
    private String alergias;
    private String eps;
    private String tipoSangre;

}
