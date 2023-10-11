package evovital.uniquindio.edu.co.repositories;

import evovital.uniquindio.edu.co.domain.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("MedicoRepository")
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Optional<Medico> findByCedulaOrEmail(String cedula, String email);

    Optional<Medico> findByEmail(String email);

    Optional<Medico> findByCedula(String cedula);

    List<Medico> findAllByEstaActivoTrue();

    boolean existsByEmailAndPassword(String email, String password);

    Medico getByEmailAndPassword(String email, String password);

}
