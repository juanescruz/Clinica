package evovital.uniquindio.edu.co.dto.login;

import jakarta.validation.constraints.NotNull;

public record AuthLoginDto(

        @NotNull String email,
        @NotNull String password


) {
}
