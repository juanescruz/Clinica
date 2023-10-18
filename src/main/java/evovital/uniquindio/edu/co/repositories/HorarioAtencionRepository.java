package evovital.uniquindio.edu.co.repositories;

import evovital.uniquindio.edu.co.domain.HorarioAtencion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("HorarioAtencionRepository")
public interface HorarioAtencionRepository extends JpaRepository<HorarioAtencion, Long> {

}
