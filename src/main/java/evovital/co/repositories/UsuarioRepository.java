package evovital.uniquindio.edu.co.repositories;

import evovital.uniquindio.edu.co.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByCedula(String cedula);

    Optional<Usuario> findByEmailAndEstaActivoTrue(String email);

}
