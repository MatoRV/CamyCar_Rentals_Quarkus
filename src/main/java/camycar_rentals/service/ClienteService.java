package camycar_rentals.service;

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

import java.util.List;

@RequestScoped
public class ClienteService extends BaseService<ClienteRepository, Cliente, Integer> {

    @Inject
    ConverterJpaToDto converterJpaToDto;

    @Inject
    ConverterDtoToJpa converterDtoToJpa;

    @Transactional
    public ClienteDtoResponse crear(ClienteDtoRequest clienteDtoRequest) {

        Cliente cliente = converterDtoToJpa.convertCliente(clienteDtoRequest);

        return converterJpaToDto.convertCliente(repository.create(cliente));
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
