package evovital.uniquindio.edu.co;

import evovital.uniquindio.edu.co.dto.horario.HorarioDTO;
import evovital.uniquindio.edu.co.dto.medico.MedicoDTO;
import evovital.uniquindio.edu.co.dto.medico.MedicoDTOAdmin;
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

            Long idHorario = administradorService.actualizarHorario(2L, new HorarioDTO(4L, (byte) 4, "08:00", "23:00"));
            System.out.println(idHorario);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void crearHorarioTest() {

        try {

            Long idHorario = administradorService.crearHorario(2L, new HorarioDTO(null, (byte) 3L, "08:00", "23:00"));
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
            System.out.println(administradorService.verDetallePQRS(6L));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void responderPQRSTest() {

        try {
            System.out.println(administradorService.responderPQRS(new MensajeDTOUsuario(
                    "Hola, soy un mensaje de prueba enviado por el administrador",
                    1L,
                    6L
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
            System.out.println(administradorService.obtenerMedico(2L));
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
            administradorService.eliminarMedico(2L);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void actualizarMedicoTest() {

        List<HorarioDTO> horarios = new ArrayList<>(){{
            add(new HorarioDTO(1L, (byte) 4, "09:00", "19:00"));
        }};

        try {
            administradorService.actualizarMedico(new MedicoDTO(
                    2L,
                    "1234556",
                    "Juan",
                    "osmajuan760@gmail.com",
                    null,
                    "Bogotá",
                    "1234567",
                    1L,
                    horarios
            ));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void crearMedicoTest() {

        List<HorarioDTO> horarios = new ArrayList<>(){{
            add(new HorarioDTO(null, (byte) 1, "08:00", "18:00"));
        }};

        MedicoDTO medico = new MedicoDTO(
                null,
                "1234556",
                "JuanE",
                "osmajuan760@gmail.com",
                "1234567",
                "Bogotá",
                "1234567",
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
