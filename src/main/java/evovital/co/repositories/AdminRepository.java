package evovital.uniquindio.edu.co.repositories;

import evovital.uniquindio.edu.co.domain.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("AdminRepository")
public interface AdminRepository extends JpaRepository<Administrador, Long> {

    boolean existsByEmailAndPassword(String email, String password);

    Administrador getByEmailAndPassword(String email, String password);
}
