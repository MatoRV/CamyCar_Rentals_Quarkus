package camycar_rentals.service.clienteService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import base.dto.cliente.ClienteDtoRequest;
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
public class CrearClienteServiceTest {

    @Inject
    ClienteService clienteService;

    @InjectMock
    ClienteRepository clienteRepository;

    @Test
    @DisplayName("Comprueba que se crean los clientes")
    void crearClienteOk() {
        // Given
        Cliente clienteDado = new Cliente(null, "Prueba 1", "cliente.1", "1234", "11111111C");
        Cliente clienteEsperado = new Cliente(1, "Prueba 1", "cliente.1", "1234", "11111111C");
        ClienteDtoRequest clienteDtoRequest = new ClienteDtoRequest("Prueba 1", "cliente.1", "1234", "11111111C");
        ClienteDtoResponse clienteDtoResponse = new ClienteDtoResponse(1, "Prueba 1", "cliente.1", "11111111C");
        Mockito.when(clienteRepository.create(clienteDado)).thenReturn(clienteEsperado);

        // When
        ClienteDtoResponse clienteDtoResponseDevuelto = clienteService.crear(clienteDtoRequest);

        // Then
        assertEquals(clienteDtoResponse, clienteDtoResponseDevuelto);
    }
}
