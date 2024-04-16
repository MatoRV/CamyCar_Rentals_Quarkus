package camycar_rentals.service.clienteService;

import base.dto.cliente.ClienteDtoResponse;
import camycar_rentals.service.ClienteService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class ObtenerClientesServiceTest {

    @InjectMock
    ClienteService clienteService;

    @Test
    @DisplayName("Obtiene todos los clientes")
    void obtenerClientesOk() {
        // Given
        ClienteDtoResponse clienteDtoResponse1 = new ClienteDtoResponse(1,"Roberto","robert","78496746T");
        ClienteDtoResponse clienteDtoResponse2 = new ClienteDtoResponse(2,"Alicia","Ali","96875489P");
        List<ClienteDtoResponse> clienteDtoResponseList = List.of(clienteDtoResponse1, clienteDtoResponse2);
        Mockito.when(clienteService.obtenerClientes()).thenReturn(clienteDtoResponseList);

        // When
        List<ClienteDtoResponse> clienteDtoResponseDevuelto = clienteService.obtenerClientes();

        // Then
        assertEquals(clienteDtoResponseList, clienteDtoResponseDevuelto);
    }
}
