package evovital.uniquindio.edu.co.servicios.especificaciones;

import evovital.uniquindio.edu.co.dto.auxiliar.TokenDTO;
import evovital.uniquindio.edu.co.dto.login.AuthLoginDto;

public interface AutenticacionService {

    TokenDTO login(AuthLoginDto loginDTO) throws Exception;

}
