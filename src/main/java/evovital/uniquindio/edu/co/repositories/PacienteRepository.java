package evovital.uniquindio.edu.co.repositories;

import evovital.uniquindio.edu.co.domain.Medico;
import evovital.uniquindio.edu.co.domain.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("PacienteRepository")
public interface PacienteRepository extends JpaRepository<Medico, Long> {


    boolean existsByEmailAndPassword(String email, String password);

    Paciente getByEmailAndPassword(String email, String password);


}
