package camycar_rentals.service;

import java.util.List;
import base.constant.errores.ErroresGeneral;
import base.dto.cliente.ClienteDtoRequest;
import base.dto.cliente.ClienteDtoResponse;
import base.service.BaseService;
import camycar_rentals.domain.Cliente;
import camycar_rentals.dto.converters.ConverterDtoToJpa;
import camycar_rentals.dto.converters.ConverterJpaToDto;
import camycar_rentals.repository.ClienteRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import io.quarkus.security.ForbiddenException;

@RequestScoped
public class ClienteService extends BaseService<ClienteRepository, Cliente, Integer> {

    @Inject
    ConverterJpaToDto converterJpaToDto;

    @Inject
    ConverterDtoToJpa converterDtoToJpa;

    @Transactional
    public ClienteDtoResponse crear(ClienteDtoRequest clienteDtoRequest) {

        Cliente cliente = converterDtoToJpa.convertCliente(clienteDtoRequest);

        if (repository.obtenerPorDni(cliente)) {
            throw new ForbiddenException(ErroresGeneral.GEN_0002);
        }
        cliente = create(cliente);
        return converterJpaToDto.convertCliente(cliente);
    }

    public List<ClienteDtoResponse> obtenerClientes() {
        return converterJpaToDto.convertClienteList(findAll());
    }

    @Transactional
    public ClienteDtoResponse editar(Integer idCliente, ClienteDtoRequest clienteDtoRequest) {

        Cliente clienteData = find(idCliente);

        Cliente clienteEdit = converterDtoToJpa.convertCliente(clienteData, clienteDtoRequest);

        return converterJpaToDto.convertCliente(edit(clienteEdit));
    }

    @Transactional
    public ClienteDtoResponse eliminar(Integer idCliente) {

        Cliente cliente = find(idCliente);

        return converterJpaToDto.convertCliente(remove(cliente));
    }
}
