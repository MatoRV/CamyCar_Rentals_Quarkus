package base.dto.reserva;

import base.dto.cliente.ClienteDtoResponse;
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

    private ClienteDtoResponse cliente;

    @Date
    private String fechaInicio;

    @Date
    private String fechaFin;
}
