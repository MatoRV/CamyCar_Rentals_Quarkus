package camycar_rentals.service.clienteService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import base.dto.cliente.ClienteDtoResponse;
import camycar_rentals.domain.Cliente;
import camycar_rentals.repository.ClienteRepository;
import camycar_rentals.service.ClienteService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ObtenerClientesServiceTest {

    @Inject
    ClienteService clienteService;

    @InjectMock
    ClienteRepository clienteRepository;

    @Test
    @DisplayName("Obtiene todos los clientes")
    void obtenerClientesOk() {
        // Given
        Cliente cliente1 = new Cliente(1, "Roberto", "robert", "1234", "78496746T");
        Cliente cliente2 = new Cliente(2, "Alicia", "Ali", "1234", "96875489P");
        ClienteDtoResponse clienteDtoResponse1 = new ClienteDtoResponse(1, "Roberto", "robert", "78496746T");
        ClienteDtoResponse clienteDtoResponse2 = new ClienteDtoResponse(2, "Alicia", "Ali", "96875489P");
        List<ClienteDtoResponse> clienteDtoResponseList = List.of(clienteDtoResponse1, clienteDtoResponse2);
        Mockito.when(clienteRepository.findAll(List.of())).thenReturn(List.of(cliente1, cliente2));

        // When
        List<ClienteDtoResponse> clienteDtoResponseDevuelto = clienteService.obtenerClientes();

        // Then
        assertEquals(clienteDtoResponseList, clienteDtoResponseDevuelto);
    }
}
