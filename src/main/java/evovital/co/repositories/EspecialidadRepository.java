package evovital.uniquindio.edu.co.repositories;

import evovital.uniquindio.edu.co.domain.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("EspecialidadRepository")
public interface EspecialidadRepository extends JpaRepository<Especialidad, Long> {

}
