package evovital.uniquindio.edu.co.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class Administrador implements Serializable {

    @Id
    private long administradorId;
    private String nombre;
    private String email;
    private String contraseña;
    private String sal;

}
