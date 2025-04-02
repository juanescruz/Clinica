package evovital.uniquindio.edu.co.repositories;

import evovital.uniquindio.edu.co.domain.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
}
