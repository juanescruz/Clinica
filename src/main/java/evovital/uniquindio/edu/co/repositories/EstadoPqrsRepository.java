package evovital.uniquindio.edu.co.repositories;

import evovital.uniquindio.edu.co.domain.EstadoPqrs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstadoPqrsRepository extends JpaRepository<EstadoPqrs, Long> {

    Optional<EstadoPqrs> findByEstado(String estado);

}
