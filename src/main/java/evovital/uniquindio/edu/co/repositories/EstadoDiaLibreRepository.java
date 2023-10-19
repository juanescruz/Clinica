package evovital.uniquindio.edu.co.repositories;

import evovital.uniquindio.edu.co.domain.EstadoDiaLibre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstadoDiaLibreRepository extends JpaRepository<EstadoDiaLibre, Long> {

    Optional<EstadoDiaLibre> findByEstado(String estado);

}
