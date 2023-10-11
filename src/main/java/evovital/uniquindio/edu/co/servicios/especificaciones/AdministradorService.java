package evovital.uniquindio.edu.co.servicios.especificaciones;

import evovital.uniquindio.edu.co.domain.Administrador;
import evovital.uniquindio.edu.co.domain.Medico;
import evovital.uniquindio.edu.co.dto.consulta.ConsultaDTOAdmin;
import evovital.uniquindio.edu.co.dto.horario.HorarioDTOActualizar;
import evovital.uniquindio.edu.co.dto.horario.HorarioDTOCrear;
import evovital.uniquindio.edu.co.dto.login.AuthLoginDto;
import evovital.uniquindio.edu.co.dto.medico.InfoMedicoDTO;
import evovital.uniquindio.edu.co.dto.medico.MedicoDTOActualizar;
import evovital.uniquindio.edu.co.dto.medico.MedicoDTOCrear;
import evovital.uniquindio.edu.co.dto.medico.MedicoDTOAdmin;
import evovital.uniquindio.edu.co.dto.mensaje.MensajeDTOUsuario;
import evovital.uniquindio.edu.co.dto.pqrs.InfoPQRSDTO;
import evovital.uniquindio.edu.co.dto.pqrs.PQRSDTOAdmin;

import java.util.List;

public interface AdministradorService {

    Long crearMedico(MedicoDTOCrear medico) throws Exception;

    Long actualizarMedico(Long codigo, MedicoDTOActualizar medico) throws Exception;

    MedicoDTOCrear eliminarMedico(Long codigo) throws Exception;

    List<MedicoDTOAdmin> listarMedicos() throws Exception;

    InfoMedicoDTO obtenerMedico(Long codigo) throws Exception;

    List<PQRSDTOAdmin> listarPQRS() throws Exception;

    String responderPQRS(Long idPqrs, MensajeDTOUsuario mensajeUsuario) throws Exception;

    InfoPQRSDTO verDetallePQRS(int codigo) throws Exception;

    List<ConsultaDTOAdmin> listarCitas() throws Exception;

    void crearHorario(Medico medico, HorarioDTOCrear horario);

    void actualizarHorario(Medico medico, HorarioDTOActualizar horario);

    boolean isAdmin(AuthLoginDto loginDto);

    Administrador signIn(AuthLoginDto loginDto);
}
