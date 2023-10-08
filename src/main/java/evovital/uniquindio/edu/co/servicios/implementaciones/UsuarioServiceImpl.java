package evovital.uniquindio.edu.co.servicios.implementaciones;

import evovital.uniquindio.edu.co.dto.login.AuthLoginDto;
import evovital.uniquindio.edu.co.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class UsuarioServiceImpl {


    private final UsuarioRepository usuarioRepository;



    public boolean login(AuthLoginDto loginDto) {

        return usuarioRepository.existsByEmailAndPassword(loginDto.email(), loginDto.password());

    }



}
