package base.dto.reserva;

import base.dto.diareservado.DiasReservadosDtoResponse;
import base.dto.maquina.MaquinaDtoResponse;
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

    private DiasReservadosDtoResponse diasReservados;
}
