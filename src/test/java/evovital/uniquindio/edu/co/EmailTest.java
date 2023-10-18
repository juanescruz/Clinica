package evovital.uniquindio.edu.co;

import evovital.uniquindio.edu.co.dto.auxiliar.EmailDTO;
import evovital.uniquindio.edu.co.servicios.especificaciones.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailTest {

    @Autowired
    private EmailService emailService;

    @Test
    public void enviarEmailTest() {

        try {

            emailService.enviarEmail(new EmailDTO(
                    "juescaos42@gmail.com",
                    "prueba de test con EvoVital",
                    "Hola, este es un mensaje de prueba de test con EvoVital"
            ));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
