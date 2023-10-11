package evovital.uniquindio.edu.co.repositories;

import evovital.uniquindio.edu.co.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("UsuarioRepository")
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

}
