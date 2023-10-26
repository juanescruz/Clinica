package evovital.uniquindio.edu.co.repositories;

import evovital.uniquindio.edu.co.domain.Pqrs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PqrsRepository extends JpaRepository<Pqrs, Long> {

    List<Pqrs> findAllByEstadoPqrs_Estado(String estado);

    List<Pqrs> findAllByConsulta_Paciente_Id(Long idPaciente);

}
