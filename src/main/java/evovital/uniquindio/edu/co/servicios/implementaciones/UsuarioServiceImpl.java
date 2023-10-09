package evovital.uniquindio.edu.co.servicios.implementaciones;

import evovital.uniquindio.edu.co.dto.login.AuthLoginDto;
import evovital.uniquindio.edu.co.exceptions.exceptions.LoginValidationException;
import evovital.uniquindio.edu.co.repositories.UsuarioRepository;
import evovital.uniquindio.edu.co.servicios.especificaciones.UsuarioService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {


    private final UsuarioRepository usuarioRepository;


    @Override
    public boolean login(AuthLoginDto loginDto) {
        if(usuarioRepository.existsByEmailAndPassword(loginDto.email(), loginDto.password())) return true;
        else throw new LoginValidationException("El usuario no existe");


    }
}
