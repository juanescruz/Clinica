package evovital.uniquindio.edu.co.repositories;

import evovital.uniquindio.edu.co.domain.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Optional<Medico> findByIdAndEstaActivoTrue(Long id);

    Optional<Medico> findAllByEstaActivoTrue();
}
