package evovital.uniquindio.edu.co.dto.auxiliar;

public record MensajeDTO<T>(
        boolean error,
        T respuesta
) {

    public MensajeDTO(T respuesta) {
        this(false, respuesta);
    }

}
