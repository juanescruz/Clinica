package evovital.uniquindio.edu.co.util;

import evovital.uniquindio.edu.co.domain.Especialidad;
import evovital.uniquindio.edu.co.domain.EstadoConsulta;
import evovital.uniquindio.edu.co.domain.EstadoDiaLibre;
import evovital.uniquindio.edu.co.domain.EstadoPqrs;
import evovital.uniquindio.edu.co.repositories.EspecialidadRepository;
import evovital.uniquindio.edu.co.repositories.EstadoConsultaRepository;
import evovital.uniquindio.edu.co.repositories.EstadoDiaLibreRepository;
import evovital.uniquindio.edu.co.repositories.EstadoPqrsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ObjetoInicio implements CommandLineRunner {

    private final EspecialidadRepository especialidadRepository;
    private final EstadoPqrsRepository estadoPqrsRepository;
    private final EstadoDiaLibreRepository estadoDiaLibreRepository;
    private final EstadoConsultaRepository estadoConsultaRepository;

    private final List<Especialidad> especialidades = List.of(
            Especialidad.builder().nombre("Cardiologia").build(),
            Especialidad.builder().nombre("Psicologia").build(),
            Especialidad.builder().nombre("Urologia").build()
    );

    private final List<EstadoPqrs> estadosPqrs = List.of(
            EstadoPqrs.builder().estado("Archivado").build(),
            EstadoPqrs.builder().estado("En proceso").build(),
            EstadoPqrs.builder().estado("Cerrado").build()
    );

    private final List<EstadoConsulta> estadosConsulta = List.of(
            EstadoConsulta.builder().estado("Pendiente").build(),
            EstadoConsulta.builder().estado("Atendida").build(),
            EstadoConsulta.builder().estado("En Proceso").build(),
            EstadoConsulta.builder().estado("Cancelada").build()
    );

    private final List<EstadoDiaLibre> estadoDiaLibre = List.of(
            EstadoDiaLibre.builder().estado("Culminado").build(),
            EstadoDiaLibre.builder().estado("Cancelado").build(),
            EstadoDiaLibre.builder().estado("Tomado").build()
    );

    /**
     * Metodo que se ejecuta al iniciar la aplicacion, guarda los estados que son valores constantes en la base de datos
     * @param args incoming main method arguments
     */
    @Override
    public void run(String... args) {
        if(especialidadRepository.findAll().isEmpty()){
            especialidadRepository.saveAll(
                    especialidades
            );
        }
        if (estadoPqrsRepository.findAll().isEmpty()){
            estadoPqrsRepository.saveAll(
                    estadosPqrs
            );
        }
        if (estadoConsultaRepository.findAll().isEmpty()){
            estadoConsultaRepository.saveAll(
                    estadosConsulta
            );
        }
        if (estadoDiaLibreRepository.findAll().isEmpty()){
            estadoDiaLibreRepository.saveAll(
                    estadoDiaLibre
            );
        }
    }
}
