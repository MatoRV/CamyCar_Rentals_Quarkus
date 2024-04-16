package camycar_rentals.service;

import base.dto.cliente.ClienteDtoRequest;
import base.dto.cliente.ClienteDtoResponse;
import base.service.BaseService;
import camycar_rentals.domain.Cliente;
import camycar_rentals.dto.converters.ConverterDTOtoJPA;
import camycar_rentals.dto.converters.ConverterJPAtoDTO;
import camycar_rentals.repository.ClienteRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@RequestScoped
public class ClienteService extends BaseService<ClienteRepository, Cliente, Integer> {

    @Inject
    ConverterJPAtoDTO converterJPAtoDTO;

    @Inject
    ConverterDTOtoJPA converterDTOtoJPA;

    @Transactional
    public ClienteDtoResponse crear(ClienteDtoRequest clienteDtoRequest) {

        Cliente cliente = converterDTOtoJPA.convertCliente(clienteDtoRequest);

        return converterJPAtoDTO.convertCliente(repository.create(cliente));
    }

    public List<ClienteDtoResponse> obtenerClientes() {
        return converterJPAtoDTO.convertClienteList(findAll());
    }

    @Transactional
    public ClienteDtoResponse editar(Integer idCliente, ClienteDtoRequest clienteDtoRequest) {

        Cliente clienteData = find(idCliente);

        Cliente clienteEdit = converterDTOtoJPA.convertCliente(clienteData, clienteDtoRequest);

        return converterJPAtoDTO.convertCliente(edit(clienteEdit));
    }

    @Transactional
    public ClienteDtoResponse eliminar(Integer idCliente) {

        Cliente cliente = find(idCliente);

        return converterJPAtoDTO.convertCliente(remove(cliente));
    }
}
