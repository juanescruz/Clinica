package evovital.uniquindio.edu.co;

import evovital.uniquindio.edu.co.dto.atencionConsulta.AtencionConsultaDTOMedico;
import evovital.uniquindio.edu.co.servicios.especificaciones.MedicoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class MedicoTest {

    @Autowired
    private MedicoService medicoService;

    @Test
    public void listarConsultasRealizadasMedicoTest() {
        System.out.println(medicoService.listarConsultasRealizadasMedico(2L));
    }

    @Test
    public void agendarDiaLibreTest() {

        try {
            medicoService.agendarDiaLibre(2L, LocalDate.of(2023, 12, 31));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void listarConsultasPacientesTest() {
        System.out.println(medicoService.listarConsultasPaciente(2L, 4L));
    }

    @Test
    public void atenderConsultaTest() {
        try {
            medicoService.atenderConsulta(4L, new AtencionConsultaDTOMedico(
                    "Sintomas",
                    "Diagnostico",
                    "Tratamiento",
                    "Notas medicas"
            ));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void listarConsultasPendientesTest() {
        System.out.println(medicoService.listarConsultasPendientes(2L));
    }

    @Test
    public void verDetalleConsultaTest() {
        System.out.println(medicoService.verDetalleConsulta(4L));
    }

}
