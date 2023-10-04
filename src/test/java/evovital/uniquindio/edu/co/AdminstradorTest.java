package evovital.uniquindio.edu.co;

import evovital.uniquindio.edu.co.dto.horario.HorarioDTOActualizar;
import evovital.uniquindio.edu.co.dto.horario.HorarioDTOCrear;
import evovital.uniquindio.edu.co.dto.medico.MedicoDTOCrear;
import evovital.uniquindio.edu.co.servicios.especificaciones.AdministradorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
// @Transactional
public class AdminstradorTest {

    @Autowired
    private AdministradorService administradorService;

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
            administradorService.actualizarMedico(1L, new evovital.uniquindio.edu.co.dto.medico.MedicoDTOActualizar(
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
                "1234567",
                "Juan",
                "Perez2",
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
