package evovital.uniquindio.edu.co.domain;

import jakarta.persistence.Id;

import java.util.List;

public class Medico {

    @Id
    private long medicoId;
    private String nombre;
    private String email;
    private String contraseña;
    private String sal;
    private String ciudadResidencia;
    private String telefono;
    private String fotoPersonal;
    private List<Consulta> consultas;
    private Especialidad especialidad;
    private boolean estaActivo;

}
