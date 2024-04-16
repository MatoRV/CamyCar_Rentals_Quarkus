package camycar_rentals.service.clienteService;

import base.dto.cliente.ClienteDtoRequest;
import base.dto.cliente.ClienteDtoResponse;
import camycar_rentals.service.ClienteService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class CrearClienteServiceTest {

    @InjectMock
    ClienteService clienteService;

    @Test
    @DisplayName("Comprueba que se crean los clientes")
    void crearClienteOk() {
        // Given
        ClienteDtoRequest clienteDtoRequest = new ClienteDtoRequest("Prueba 1","cliente.1","1234","11111111C");
        ClienteDtoResponse clienteDtoResponse = new ClienteDtoResponse(1,"Prueba 1","cliente.1","11111111C");
        Mockito.when(clienteService.crear(clienteDtoRequest)).thenReturn(clienteDtoResponse);

        // When
        ClienteDtoResponse clienteDtoResponseDevuelto = clienteService.crear(clienteDtoRequest);

        // Then
        assertEquals(clienteDtoResponse, clienteDtoResponseDevuelto);
    }
}
