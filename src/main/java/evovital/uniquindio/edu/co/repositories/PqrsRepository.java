package evovital.uniquindio.edu.co.repositories;

import evovital.uniquindio.edu.co.domain.Pqrs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("PqrsRepository")
public interface PqrsRepository extends JpaRepository<Pqrs, Long> {

    List<Pqrs> findAllByEstadoPqrs_Estado(String estado);

}
