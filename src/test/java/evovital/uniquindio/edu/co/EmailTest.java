package evovital.uniquindio.edu.co;

import evovital.uniquindio.edu.co.config.MailProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmailTest {



    @Autowired
    private MailProperties mailProperties;


    @Test
    void verifyEmail(){
        //obtengo la variable de entorno llamada email
        String email = System.getenv("MAIL");
        System.out.println(email);

        assert mailProperties.getUsername().equals(email);
    }


}
