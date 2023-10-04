package evovital.uniquindio.edu.co.controllers;

import evovital.uniquindio.edu.co.domain.Medico;
import evovital.uniquindio.edu.co.domain.Usuario;
import evovital.uniquindio.edu.co.repositories.MedicoRepository;
import evovital.uniquindio.edu.co.repositories.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoRepository medicoRepository;
    private final UsuarioRepository usuarioRepository;

    public MedicoController(MedicoRepository medicoRepository, UsuarioRepository usuarioRepository) {
        this.medicoRepository = medicoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public ResponseEntity<Usuario> crearMedico() {

        Usuario medico = Medico.builder()
                .cedula("12345")
                .email("holamundo@hola.com")
                .ciudadResidencia("Bogotá")
                .estaActivo(true)
                .nombre("Edwin")
                .password("hola")
                .telefono("12355")
                .urlFotoPersonal("hola.com")
                .build();

        return ResponseEntity.ok(usuarioRepository.save(medico));
    }

}
