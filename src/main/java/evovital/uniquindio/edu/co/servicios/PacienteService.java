package evovital.uniquindio.edu.co.servicios;

public interface PacienteService {

    void registrarse();

    void editarPerfil();

    void eliminarCuenta();

    void enviarLinkRecuperacion();

    void cambiarPassword();

    void agendarCita();

    void crearPQR();

    void listarPQRSPaciente();

    void responderPQRS();

    void listarCitasPaciente();

    void filtrarCitasPorFecha();

    void filtarCitasPorMedico();

    void verDetalleConsulta();

}
