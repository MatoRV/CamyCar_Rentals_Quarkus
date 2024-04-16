package camycar_rentals.dto.converters;

import base.dto.cliente.ClienteDtoRequest;
import camycar_rentals.domain.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi")
public interface ConverterDTOtoJPA {

    Cliente convertCliente(ClienteDtoRequest clienteDtoRequest);

    Cliente convertCliente(@MappingTarget Cliente clienteData, ClienteDtoRequest request);
}
