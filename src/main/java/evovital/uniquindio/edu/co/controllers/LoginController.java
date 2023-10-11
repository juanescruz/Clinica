package evovital.uniquindio.edu.co.controllers;

import evovital.uniquindio.edu.co.domain.Administrador;
import evovital.uniquindio.edu.co.domain.Paciente;
import evovital.uniquindio.edu.co.domain.Usuario;
import evovital.uniquindio.edu.co.dto.login.AuthLoginDto;
import evovital.uniquindio.edu.co.dto.login.ResponseLoginDto;
import evovital.uniquindio.edu.co.exceptions.exceptions.LoginValidationException;
import evovital.uniquindio.edu.co.servicios.especificaciones.AdministradorService;
import evovital.uniquindio.edu.co.servicios.especificaciones.PacienteService;
import evovital.uniquindio.edu.co.servicios.implementaciones.AdministradorServiceImpl;
import evovital.uniquindio.edu.co.servicios.implementaciones.PacienteServiceImpl;
import evovital.uniquindio.edu.co.util.Roles;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/login")
@AllArgsConstructor
@CrossOrigin
public class LoginController {

    private final AdministradorServiceImpl administradorService;

    private final PacienteServiceImpl pacienteService;

    /**
     * Método de prueba para conectar datos del frontend con el backend
     * usando un dto Login
     * @return true si el usuario existe en la base de datos
     */
    @PostMapping()
    public ResponseEntity<ResponseLoginDto> signIn(@RequestBody AuthLoginDto loginDto){
        if(administradorService.isAdmin(loginDto)){
            return ResponseEntity.ok(createResponseDto(administradorService.signIn(loginDto), Roles.ADMIN));
        }
        if (pacienteService.isPaciente(loginDto)) {
            return ResponseEntity.ok(createResponseDto(administradorService.signIn(loginDto), Roles.PACIENTE));
        }

        throw new LoginValidationException("No existe el usuario");

    }

    private ResponseLoginDto createResponseDto(Usuario usuario, String rol) {
        return new ResponseLoginDto(usuario.getId(), usuario.getNombre(), rol);
    }


}
