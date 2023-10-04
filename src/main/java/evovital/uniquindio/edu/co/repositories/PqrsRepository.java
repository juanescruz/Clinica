package evovital.uniquindio.edu.co.repositories;

import evovital.uniquindio.edu.co.domain.Pqrs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("PqrsRepository")
public interface PqrsRepository extends JpaRepository<Pqrs, Long> {
}
