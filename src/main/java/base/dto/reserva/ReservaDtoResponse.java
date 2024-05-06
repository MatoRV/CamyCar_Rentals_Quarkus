package base.dto.reserva;

import base.dto.maquina.MaquinaDtoResponse;
import base.validator.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaDtoResponse {

    private Integer idReserva;

    private MaquinaDtoResponse maquina;

    private String nombreUsuario;

    private String direccion;

    @Date
    private String fechaInicio;

    @Date
    private String fechaFin;
}
