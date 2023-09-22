package evovital.uniquindio.edu.co.servicios;

import evovital.uniquindio.edu.co.dto.auxiliar.EmailDTO;

public interface EmailService {

    String enviarEmail(EmailDTO emailDTO) throws Exception;

}
