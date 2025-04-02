package evovital.uniquindio.edu.co.servicios.especificaciones;

import evovital.uniquindio.edu.co.dto.consulta.ConsultaDTOAdmin;
import evovital.uniquindio.edu.co.dto.horario.HorarioDTO;
import evovital.uniquindio.edu.co.dto.medico.InfoMedicoDTO;
import evovital.uniquindio.edu.co.dto.medico.MedicoDTO;
import evovital.uniquindio.edu.co.dto.medico.MedicoDTOAdmin;
import evovital.uniquindio.edu.co.dto.mensaje.MensajeDTOUsuario;
import evovital.uniquindio.edu.co.dto.paciente.InfoPacienteDTO;
import evovital.uniquindio.edu.co.dto.paciente.PacienteDTO;
import evovital.uniquindio.edu.co.dto.pqrs.InfoPQRSDTO;
import evovital.uniquindio.edu.co.dto.pqrs.PQRSDTOAdmin;

import java.util.List;

public interface AdministradorService {

    Long crearMedico(MedicoDTO medico) throws Exception;

    Long actualizarMedico(MedicoDTO medico) throws Exception;

    MedicoDTO eliminarMedico(Long codigo) throws Exception;

    List<MedicoDTOAdmin> listarMedicos() throws Exception;

    InfoMedicoDTO obtenerMedico(Long codigo) throws Exception;

    List<PQRSDTOAdmin> listarPQRS() throws Exception;

    Long responderPQRS(MensajeDTOUsuario mensajeUsuario) throws Exception;

    InfoPQRSDTO verDetallePQRS(Long idPQRS) throws Exception;

    List<ConsultaDTOAdmin> listarConsultas() throws Exception;

    Long crearHorario(Long idMedico, HorarioDTO horario);

    Long actualizarHorario(Long idMedico, HorarioDTO horario);

    List<InfoPacienteDTO> listarPacientes();

    PacienteDTO obtenerDetallePaciente(Long idPaciente);
}
