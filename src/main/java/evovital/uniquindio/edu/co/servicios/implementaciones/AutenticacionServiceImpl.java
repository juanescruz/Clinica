package evovital.uniquindio.edu.co.servicios.implementaciones;

import evovital.uniquindio.edu.co.domain.Medico;
import evovital.uniquindio.edu.co.domain.Paciente;
import evovital.uniquindio.edu.co.domain.Usuario;
import evovital.uniquindio.edu.co.dto.auxiliar.TokenDTO;
import evovital.uniquindio.edu.co.dto.login.AuthLoginDto;
import evovital.uniquindio.edu.co.repositories.UsuarioRepository;
import evovital.uniquindio.edu.co.servicios.especificaciones.AutenticacionService;
import evovital.uniquindio.edu.co.util.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AutenticacionServiceImpl implements AutenticacionService {

    private final UsuarioRepository usuarioRepository;
    private final JWTUtils jwtUtils;

    // TODO: test missing
    @Override
    public TokenDTO login(AuthLoginDto authLoginDto) throws Exception {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Usuario usuario = usuarioRepository.findByEmailAndEstaActivoTrue(authLoginDto.email()).orElseThrow(
                () -> new Exception("No existe el correo ingresado")
        );

        if( !passwordEncoder.matches(authLoginDto.password(), usuario.getPassword()) ){
            throw new Exception("La contraseña ingresada es incorrecta");
        }

        return new TokenDTO( crearToken(usuario) );
    }

    private String crearToken(Usuario usuario){

        String rol;
        String nombre;

        if( usuario instanceof Paciente){

            rol = "paciente";
            nombre = ((Paciente) usuario).getNombre();

        }else if( usuario instanceof Medico){

            rol = "medico";
            nombre = ((Medico) usuario).getNombre();

        }else{

            rol = "admin";
            nombre = "Administrador";

        }

        Map<String, Object> map = new HashMap<>();
        map.put("rol", rol);
        map.put("nombre", nombre);
        map.put("id", usuario.getId());
        return jwtUtils.generarToken(usuario.getEmail(), map);
    }
}
