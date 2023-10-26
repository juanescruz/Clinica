package evovital.uniquindio.edu.co.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/login")
@AllArgsConstructor
@CrossOrigin
public class LoginController {

    //private final UsuarioServiceImpl usuarioService;

    /**
     * Método de prueba para conectar datos del frontend con el backend
     * usando un dto Login
     * @return true si el usuario existe en la base de datos
     */
    /* @PostMapping()
    public ResponseEntity<Boolean> signIn(@RequestBody AuthLoginDto loginDto){
        System.out.println("loginDto.email() = " + loginDto.email() + " loginDto.password() = " + loginDto.password());
        return ResponseEntity.ok(usuarioService.login(loginDto));

    } */


}
