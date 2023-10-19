package evovital.uniquindio.edu.co.servicios.implementaciones;

import evovital.uniquindio.edu.co.dto.auxiliar.EmailDTO;
import evovital.uniquindio.edu.co.servicios.especificaciones.EmailService;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    /**
     * Envia un correo electronico
     * @param emailDTO
     * @throws Exception
     */
    @Override
    public void enviarEmail(EmailDTO emailDTO) throws Exception {

        MimeMessage mensaje = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje);

        helper.setSubject(emailDTO.asunto());
        helper.setText(emailDTO.mensaje(), true);
        helper.setTo(emailDTO.para());
        helper.setFrom("no_reply@dominio.com");

        javaMailSender.send(mensaje);

    }
}
