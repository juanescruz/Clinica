package evovital.uniquindio.edu.co.repositories;

import evovital.uniquindio.edu.co.domain.EstadoConsulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoConsultaRepository extends JpaRepository<EstadoConsulta, Long> {
    EstadoConsulta findByEstado(String estado);
}
