package evovital.uniquindio.edu.co;

import evovital.uniquindio.edu.co.dto.consulta.InfoConsultaDTO;
import evovital.uniquindio.edu.co.dto.mensaje.MensajeDTOUsuario;
import evovital.uniquindio.edu.co.dto.paciente.PacienteDTO;
import evovital.uniquindio.edu.co.dto.pqrs.PQRSDTOPacienteReq;
import evovital.uniquindio.edu.co.servicios.especificaciones.PacienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class PacienteTest {

    @Autowired
    private PacienteService pacienteService;

    @Test
    public void calificarPQRSTest() {

        try {

            System.out.println(pacienteService.calificarPQRS(1L, 5));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void reagendarConsultaTest() {

        try {

            System.out.println(pacienteService.reagendarConsulta(1L, LocalDateTime.now()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void verDetalleConsultaTest() {

        try {

            System.out.println(pacienteService.verDetalleConsulta(1L));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void filtrarConsultasPorMedicoTest() {

        try {

            System.out.println(pacienteService.filtarConsultasPorMedico(2L, 1L));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void filtrarConsultasPorFechaTest() {

        try {

            System.out.println(pacienteService.filtrarConsultasPorFecha(2L, LocalDate.now()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void listarConsultasPacienteTest() {

        try {

            System.out.println(pacienteService.listarConsultasPaciente(2L));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void responderPQRSTest() {

        try {

            pacienteService.responderPQRS(new MensajeDTOUsuario(
                    "respuesta al primer mensaje puesto por el usuario",
                    2L,
                    1L
            ));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void listarPQRSPacienteTest() {

        try {

            System.out.println(pacienteService.listarPQRSPaciente(2L));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void cambiarPasswordTest() {

        try {

            pacienteService.cambiarPassword(3L, "holamundo");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void enviarLinkDeRecuperacionTest() {

        try {

            pacienteService.enviarLinkRecuperacion("juescaos42@gmail.com");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void editarPerfilTest() {
        try {
            System.out.println(
                    pacienteService.editarPerfil(
                        2L,
                        new PacienteDTO(
                                "Paciente",
                                "12345678",
                                "hola2@gmail.com",
                                "123456789",
                                LocalDate.now(),
                                "Alergias",
                                "EPS",
                                "O-",
                                "Cali",
                                "12345678"
                        )
                    )
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void registrarseTest() {
        try {
            pacienteService.registrarse(
                    new PacienteDTO(
                            "Paciente",
                            "123456789",
                            "juescaos42@gmail.com",
                            "123456789",
                            LocalDate.now(),
                            "Alergias",
                            "EPS",
                            "O+",
                            "Cali",
                            "123456789"
                    )
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void agendarConsultaTest() {
        try {
            pacienteService.agendarConsulta(new InfoConsultaDTO(
                    2L,
                    1L,
                    LocalDateTime.now(),
                    "Hola, soy un mensaje de prueba y necesito una consulta"
            ));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void crearPqrsTest() {
        try {
            pacienteService.crearPQRS(new PQRSDTOPacienteReq(
                    1L,
                    new MensajeDTOUsuario(
                            "Hola, soy un mensaje de prueba",
                            2L,
                            1L
                    )
            ));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
