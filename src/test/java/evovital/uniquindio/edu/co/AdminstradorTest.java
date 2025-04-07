package evovital.uniquindio.edu.co;

import evovital.uniquindio.edu.co.dto.horario.HorarioDTO;
import evovital.uniquindio.edu.co.dto.medico.MedicoDTOAdmin;
import evovital.uniquindio.edu.co.dto.mensaje.MensajeDTOUsuario;
import evovital.uniquindio.edu.co.servicios.especificaciones.AdministradorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
class AdminstradorTest {

    @Autowired
    private AdministradorService administradorService;

    @Test
    void actualizarHorarioTest() {

        try {

            Long idHorario = administradorService.actualizarHorario(2L, new HorarioDTO(4L, (byte) 4, "08:00", "23:00"));
            System.out.println(idHorario);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void crearHorarioTest() {

        try {

            Long idHorario = administradorService.crearHorario(2L, new HorarioDTO(null, (byte) 3L, "08:00", "23:00"));
            System.out.println(idHorario);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void listarConsultasTest() {

        try {
            System.out.println(administradorService.listarConsultas());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void verDetallePQRSTest() {

        try {
            System.out.println(administradorService.verDetallePQRS(6L));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void responderPQRSTest() {

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
     void listarPqrsTest() {

        try {
            System.out.println(administradorService.listarPQRS());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void obtenerMedicoTest() {

        try {
            System.out.println(administradorService.obtenerMedico(2L));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void listarMedicosTest() {

        try {
            List<MedicoDTOAdmin> medicos = administradorService.listarMedicos();
            System.out.println(medicos);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void eliminarMedicoTest() {

        try {
            administradorService.eliminarMedico(2L);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
