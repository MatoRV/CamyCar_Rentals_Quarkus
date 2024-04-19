package camycar_rentals.dto.converters;

import java.util.List;
import base.dto.cliente.ClienteDtoResponse;
import base.dto.maquina.MaquinaDtoResponse;
import base.dto.reserva.ReservaDtoResponse;
import base.dto.tipomaquina.TipoMaquinaDtoResponse;
import camycar_rentals.domain.Cliente;
import camycar_rentals.domain.Maquina;
import camycar_rentals.domain.Reserva;
import camycar_rentals.domain.TipoMaquina;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface ConverterJpaToDto {

    ClienteDtoResponse convertCliente(Cliente cliente);

    List<ClienteDtoResponse> convertClienteList(List<Cliente> clientes);

    TipoMaquinaDtoResponse convertTipoMaquinaDtoResponse(TipoMaquina tipoMaquina);

    List<TipoMaquinaDtoResponse> convertTipoMaquinaDtoResponseList(List<TipoMaquina> tipoMaquinas);

    @Mapping(source = "maquina.tipoMaquina.nombre", target = "tipoMaquina")
    MaquinaDtoResponse convertMaquinaDtoResponse(Maquina maquina);

    List<MaquinaDtoResponse> convertMaquinaDtoResponseList(List<Maquina> maquinas);

    ReservaDtoResponse convertReservaDtoResponse(Reserva reserva);

    List<ReservaDtoResponse> convertReservaDtoResponseList(List<Reserva> reservas);
}
