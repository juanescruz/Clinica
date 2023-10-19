package evovital.uniquindio.edu.co;

import evovital.uniquindio.edu.co.dto.horario.HorarioDTOActualizar;
import evovital.uniquindio.edu.co.dto.horario.HorarioDTOCrear;
import evovital.uniquindio.edu.co.dto.medico.MedicoDTOActualizar;
import evovital.uniquindio.edu.co.dto.medico.MedicoDTOAdmin;
import evovital.uniquindio.edu.co.dto.medico.MedicoDTOCrear;
import evovital.uniquindio.edu.co.dto.mensaje.MensajeDTOUsuario;
import evovital.uniquindio.edu.co.servicios.especificaciones.AdministradorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
// @Transactional
public class AdminstradorTest {

    // :D
    // (._. )
    // >~<
    // (^_^)
    // (TnT)
    // (-_- )
    // B)
    // (@_@)
    // (0-0)
    // ( *o* )
    @Autowired
    private AdministradorService administradorService;

    @Test
    public void actualizarHorarioTest() {

        try {

            Long idHorario = administradorService.actualizarHorario(1L, new HorarioDTOActualizar(1L, (byte) 5, "08:00", "18:00"));
            System.out.println(idHorario);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void crearHorarioTest() {

        try {

            Long idHorario = administradorService.crearHorario(1L, new HorarioDTOCrear((byte) 1, "08:00", "18:00"));
            System.out.println(idHorario);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void listarConsultasTest() {

        try {
            System.out.println(administradorService.listarConsultas());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void verDetallePQRSTest() {

        try {
            System.out.println(administradorService.verDetallePQRS(1L));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void responderPQRSTest() {

        try {
            System.out.println(administradorService.responderPQRS(new MensajeDTOUsuario(
                    "Hola, soy un mensaje de prueba",
                    1L,
                    1L
            )));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void listarPqrsTest() {

        try {
            System.out.println(administradorService.listarPQRS());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void obtenerMedicoTest() {

        try {
            System.out.println(administradorService.obtenerMedico(1L));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void listarMedicosTest() {

        try {
            List<MedicoDTOAdmin> medicos = administradorService.listarMedicos();
            System.out.println(medicos);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void eliminarMedicoTest() {

        try {
            administradorService.eliminarMedico(1L);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void actualizarMedicoTest() {

        List<HorarioDTOActualizar> horarios = new ArrayList<>(){{
            add(new HorarioDTOActualizar(1L, (byte) 4, "09:00", "19:00"));
        }};

        try {
            administradorService.actualizarMedico(new MedicoDTOActualizar(
                    1L,
                    "1234567",
                    "Juan",
                    "Perez2",
                    "holaMundo",
                    "Bogotá",
                    "1234567",
                    "https://www.google.com",
                    2L,
                    horarios
            ));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void crearMedicoTest() {

        List<HorarioDTOCrear> horarios = new ArrayList<>(){{
            add(new HorarioDTOCrear((byte) 1, "08:00", "18:00"));
        }};

        MedicoDTOCrear medico = new MedicoDTOCrear(
                "12345678",
                "JuanE",
                "osmajuan760@gmail.com",
                "holaMundo",
                "Bogotá",
                "1234567",
                "https://www.google.com",
                1L,
                horarios
        );

        try {
            Long idMedicoRegistrado = administradorService.crearMedico(medico);
            System.out.println(idMedicoRegistrado);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
