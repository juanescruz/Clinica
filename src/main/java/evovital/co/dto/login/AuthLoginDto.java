package evovital.uniquindio.edu.co.dto.login;

import jakarta.validation.constraints.NotBlank;

public record AuthLoginDto(
        @NotBlank String email,
        @NotBlank String password
) {
}
