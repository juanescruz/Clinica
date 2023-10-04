package evovital.uniquindio.edu.co.util;

import evovital.uniquindio.edu.co.domain.Especialidad;
import evovital.uniquindio.edu.co.repositories.EspecialidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ObjetoInicio implements CommandLineRunner {


    @Autowired
    private EspecialidadRepository especialidadRepository;

    private final List<Especialidad> especialidades = List.of(
            Especialidad.builder().nombre("Cardiologia").build(),
            Especialidad.builder().nombre("Psicologia").build(),
            Especialidad.builder().nombre("Urologia").build()
    );

    @Override
    public void run(String... args) {
        if(especialidadRepository.findAll().isEmpty()){
            especialidadRepository.saveAll(
                    especialidades
            );
        }
    }
}
