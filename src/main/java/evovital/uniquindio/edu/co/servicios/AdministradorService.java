package evovital.uniquindio.edu.co.servicios;

import evovital.uniquindio.edu.co.dto.cita.ConsultaDTOAdmin;
import evovital.uniquindio.edu.co.dto.medico.InfoMedicoDTO;
import evovital.uniquindio.edu.co.dto.medico.MedicoDTO;
import evovital.uniquindio.edu.co.dto.medico.MedicoDTOAdmin;
import evovital.uniquindio.edu.co.dto.pqrs.InfoPQRSDTO;
import evovital.uniquindio.edu.co.dto.pqrs.PQRSDTOAdmin;

import java.util.List;

public interface AdministradorService {

    String crearMedico(MedicoDTO medico) throws Exception;

    String actualizarMedico(int codigo, MedicoDTO medico) throws Exception;

    String eliminarMedico(int codigo) throws Exception;

    List<MedicoDTOAdmin> listarMedicos() throws Exception;

    InfoMedicoDTO obtenerMedico(int codigo) throws Exception;

    List<PQRSDTOAdmin> listarPQRS() throws Exception;

    String responderPQRS(int codigo) throws Exception;

    InfoPQRSDTO verDetallePQRS(int codigo) throws Exception;

    List<ConsultaDTOAdmin> listarCitas() throws Exception;

}
