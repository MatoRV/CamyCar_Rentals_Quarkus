package camycar_rentals.dto.converters;

import base.dto.cliente.ClienteDtoResponse;
import base.dto.maquina.MaquinaDtoResponse;
import base.dto.tipomaquina.TipoMaquinaDtoResponse;
import camycar_rentals.domain.Cliente;
import camycar_rentals.domain.Maquina;
import camycar_rentals.domain.TipoMaquina;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface ConverterJpaToDto {

    ClienteDtoResponse convertCliente(Cliente cliente);

    List<ClienteDtoResponse> convertClienteList(List<Cliente> clientes);

    TipoMaquinaDtoResponse convertTipoMaquinaDtoResponse(TipoMaquina tipoMaquina);

    List<TipoMaquinaDtoResponse> convertTipoMaquinaDtoResponseList(List<TipoMaquina> tipoMaquinas);

    MaquinaDtoResponse convertMaquinaDtoResponse(Maquina maquina);

    List<MaquinaDtoResponse> convertMaquinaDtoResponseList(List<Maquina> maquinas);
}
