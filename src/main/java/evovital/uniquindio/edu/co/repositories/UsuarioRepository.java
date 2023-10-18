package evovital.uniquindio.edu.co.repositories;

import evovital.uniquindio.edu.co.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("UsuarioRepository")
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmailAndPassword(String email, String password);

    Optional<Usuario> findByEmail(String email);
}
