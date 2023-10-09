package evovital.uniquindio.edu.co.servicios.especificaciones;

import evovital.uniquindio.edu.co.dto.login.AuthLoginDto;

public interface UsuarioService {

    boolean login(AuthLoginDto loginDto) throws Exception;

}
