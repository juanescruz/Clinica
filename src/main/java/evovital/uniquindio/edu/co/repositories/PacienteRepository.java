package evovital.uniquindio.edu.co.repositories;

import evovital.uniquindio.edu.co.domain.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Optional<Paciente> findByCedula(String cedula);

    Optional<Paciente> findByEmail(String email);

}
