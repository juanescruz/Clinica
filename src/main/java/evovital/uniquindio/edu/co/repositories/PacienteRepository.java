package evovital.uniquindio.edu.co.repositories;

import evovital.uniquindio.edu.co.domain.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
