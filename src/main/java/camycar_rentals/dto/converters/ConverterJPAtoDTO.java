package camycar_rentals.dto.converters;

import base.dto.cliente.ClienteDtoResponse;
import camycar_rentals.domain.Cliente;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface ConverterJPAtoDTO {

    ClienteDtoResponse convertCliente(Cliente cliente);

    List<ClienteDtoResponse> convertClienteList(List<Cliente> clientes);
}
