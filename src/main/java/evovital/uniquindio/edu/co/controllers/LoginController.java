package evovital.uniquindio.edu.co.controllers;

import evovital.uniquindio.edu.co.dto.login.AuthLoginDto;
import evovital.uniquindio.edu.co.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/login")
@AllArgsConstructor
@CrossOrigin
public class LoginController {

    private final UsuarioRepository usuarioRepository;

    /**
     * Método de prueba para conectar datos del frontend con el backend
     * usando un dto Login
     * @return
     */
    @PostMapping("")
    public ResponseEntity<Boolean> signIn(@RequestBody AuthLoginDto loginDto){
        System.out.println("loginDto.email() = " + loginDto.email() + "loginDto.password() = " + loginDto.password());
        return ResponseEntity.ok(usuarioRepository.existsByEmailAndPassword(loginDto.email(), loginDto.password()));

    }


}
