package base.dto.reserva;

import base.validator.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDtoRequest {

    private Integer idMaquina;

    private Integer idUsuario;

    private String direccion;

    @Date
    private String fechaInicio;

    @Date
    private String fechaFin;
}
