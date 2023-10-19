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
        System.out.println(medicoService.listarConsultasRealizadasMedico(1L));
    }

    @Test
    public void agendarDiaLibreTest() {
        medicoService.agendarDiaLibre(1L, LocalDate.of(2021, 10, 10));
    }

    @Test
    public void listarConsultasPacientesTest() {
        System.out.println(medicoService.listarConsultasPacientes(1L, 2L));
    }

    @Test
    public void atenderConsultaTest() {
        medicoService.atenderConsulta(1L, new AtencionConsultaDTOMedico(
                "Sintomas",
                "Diagnostico",
                "Tratamiento",
                "Notas medicas"
        ));
    }

    @Test
    public void listarConsultasPendientesTest() {
        System.out.println(medicoService.listarConsultasPendientes(1L));
    }

    @Test
    public void verDetalleConsultaTest() {
        System.out.println(medicoService.verDetalleConsulta(1L));
    }

}
