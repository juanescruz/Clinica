package evovital.uniquindio.edu.co.repositories;

import evovital.uniquindio.edu.co.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
