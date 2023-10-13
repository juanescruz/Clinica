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
    public void registrarseTest() {
        try {
            pacienteService.registrarse(
                    new PacienteDTO(
                            "Paciente",
                            "123456789",
                            "hola@gmail.com",
                            "123456789",
                            LocalDate.now(),
                            "Alergias",
                            "EPS",
                            "O+",
                            "Cali",
                            "123456789",
                            "110000010101010010111010100101010010101010101010101111001010001010010100010100101"
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
