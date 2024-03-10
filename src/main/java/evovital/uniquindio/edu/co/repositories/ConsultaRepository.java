package evovital.uniquindio.edu.co.repositories;

import evovital.uniquindio.edu.co.domain.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    List<Consulta> findAllByPaciente_Id(Long idPaciente);

    List<Consulta> findAllByPaciente_IdAndFechaCreacion(Long idPaciente, LocalDate fechaCreacion);

    List<Consulta> findAllByPaciente_IdAndMedico_Id(Long idPaciente, Long idMedico);

    List<Consulta> findAllByMedico_IdAndEstadoConsulta_Estado(Long idMedico, String estado);

    List<Consulta> findAllByFechaYHoraAtencion(LocalDateTime fechaYHoraAtencion);

    List<Consulta> findAllByEstadoConsulta_EstadoAndMedico_Id(String estado, Long idMedico);

    List<Consulta> findAllByFechaYHoraAtencionAndEstadoConsulta_Estado(LocalDateTime fechaYHoraDeAtencion, String estado);

}
