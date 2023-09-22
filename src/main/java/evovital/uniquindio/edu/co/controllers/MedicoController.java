package evovital.uniquindio.edu.co.controllers;

import evovital.uniquindio.edu.co.domain.Medico;
import evovital.uniquindio.edu.co.domain.Usuario;
import evovital.uniquindio.edu.co.repositories.MedicoRepository;
import evovital.uniquindio.edu.co.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<Page<Medico>> getMedicos(@PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(medicoRepository.findByEstaActivoTrue(paginacion));
    }

    @PostMapping
    public ResponseEntity<Usuario> crearMedico() {

        Usuario medico = Medico.builder().cedula("12345").email("holamundo@hola.com").ciudadResidencia("Bogotá").estaActivo(true).nombre("Edwin").password("hola").telefono("12355").urlFotoPersonal("hola.com").build();

        return ResponseEntity.ok(usuarioRepository.save(medico));
    }

}
