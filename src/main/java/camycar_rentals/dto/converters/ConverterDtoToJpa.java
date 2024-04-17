package camycar_rentals.dto.converters;

import java.util.List;
import base.dto.cliente.ClienteDtoRequest;
import base.dto.maquina.MaquinaDtoRequest;
import base.dto.tipomaquina.TipoMaquinaDtoRequest;
import camycar_rentals.domain.Cliente;
import camycar_rentals.domain.Maquina;
import camycar_rentals.domain.TipoMaquina;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi")
public interface ConverterDtoToJpa {

    Cliente convertCliente(ClienteDtoRequest clienteDtoRequest);

    Cliente convertCliente(@MappingTarget Cliente clienteData, ClienteDtoRequest request);

    TipoMaquina convertTipoMaquina(TipoMaquinaDtoRequest tipoMaquinaDtoRequest);

    TipoMaquina convertTipoMaquina(@MappingTarget TipoMaquina tipoMaquina, TipoMaquinaDtoRequest tipoMaquinaDtoRequest);

    List<TipoMaquina> converTipoMaquinaList(List<TipoMaquinaDtoRequest> tipoMaquinaDtoRequestList);

    Maquina convertMaquina(MaquinaDtoRequest maquinaDtoRequest);

    Maquina convertMaquina(@MappingTarget Maquina maquina, MaquinaDtoRequest maquinaDtoRequest);

    List<Maquina> convertMaquinaList(List<MaquinaDtoRequest> maquinaDtoRequestList);
}
