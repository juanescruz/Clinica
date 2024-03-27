package evovital.uniquindio.edu.co.dto.consulta;

import evovital.uniquindio.edu.co.domain.Consulta;
import evovital.uniquindio.edu.co.domain.MetodoPago;

import java.time.LocalDate;

public record MetodoPagoDTO(
        String descripcion,
        String estado,
        String fecha,
        String metodo_pago,
        String valor

) {

    public MetodoPago toEntity() {

        return MetodoPago.builder()
                .descripcion(descripcion)
                .estado(estado)
                .fecha(fecha)
                .metodoPago(metodo_pago)
                .valor(valor)
                .build();

    }

}
