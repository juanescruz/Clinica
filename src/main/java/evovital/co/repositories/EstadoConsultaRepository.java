package evovital.uniquindio.edu.co.repositories;

import evovital.uniquindio.edu.co.domain.EstadoConsulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstadoConsultaRepository extends JpaRepository<EstadoConsulta, Long> {
    Optional<EstadoConsulta> findByEstado(String estado);
}
