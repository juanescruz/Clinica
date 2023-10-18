package evovital.uniquindio.edu.co.dto.auxiliar;

import jakarta.validation.constraints.NotBlank;

public record TokenDTO(

        @NotBlank
        String token

) {
}
