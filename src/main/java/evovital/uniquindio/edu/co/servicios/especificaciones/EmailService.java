package evovital.uniquindio.edu.co.servicios.especificaciones;

import evovital.uniquindio.edu.co.dto.auxiliar.EmailDTO;

public interface EmailService {

    void enviarEmail(EmailDTO emailDTO) throws Exception;

}
