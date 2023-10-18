package evovital.uniquindio.edu.co.servicios.especificaciones;

import evovital.uniquindio.edu.co.dto.consulta.ConsultaDTOAdmin;
import evovital.uniquindio.edu.co.dto.horario.HorarioDTOActualizar;
import evovital.uniquindio.edu.co.dto.horario.HorarioDTOCrear;
import evovital.uniquindio.edu.co.dto.medico.InfoMedicoDTO;
import evovital.uniquindio.edu.co.dto.medico.MedicoDTOActualizar;
import evovital.uniquindio.edu.co.dto.medico.MedicoDTOAdmin;
import evovital.uniquindio.edu.co.dto.medico.MedicoDTOCrear;
import evovital.uniquindio.edu.co.dto.mensaje.MensajeDTOUsuario;
import evovital.uniquindio.edu.co.dto.pqrs.InfoPQRSDTO;
import evovital.uniquindio.edu.co.dto.pqrs.PQRSDTOAdmin;

import java.util.List;

public interface AdministradorService {

    Long crearMedico(MedicoDTOCrear medico) throws Exception;

    Long actualizarMedico(MedicoDTOActualizar medico) throws Exception;

    MedicoDTOCrear eliminarMedico(Long codigo) throws Exception;

    List<MedicoDTOAdmin> listarMedicos() throws Exception;

    InfoMedicoDTO obtenerMedico(Long codigo) throws Exception;

    List<PQRSDTOAdmin> listarPQRS() throws Exception;

    String responderPQRS(MensajeDTOUsuario mensajeUsuario) throws Exception;

    InfoPQRSDTO verDetallePQRS(Long idPQRS) throws Exception;

    List<ConsultaDTOAdmin> listarConsultas() throws Exception;

    Long crearHorario(Long idMedico, HorarioDTOCrear horario);

    Long actualizarHorario(Long idMedico, HorarioDTOActualizar horario);

}
