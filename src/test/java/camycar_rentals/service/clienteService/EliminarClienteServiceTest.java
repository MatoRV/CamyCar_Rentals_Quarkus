package camycar_rentals.service.clienteService;

import base.dto.cliente.ClienteDtoResponse;
import camycar_rentals.domain.Cliente;
import camycar_rentals.service.ClienteService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class EliminarClienteServiceTest {

    @InjectMock
    ClienteService clienteService;

    @Test
    @DisplayName("Elimina un cliente")
    void eliminarClienteOk() {
        // Given
        Cliente cliente = new Cliente(1, "Cliente 1", "cliente.1", "1234", "11111111C");
        ClienteDtoResponse clienteDtoResponse = new ClienteDtoResponse(1, "Cliente 1", "cliente.1","11111111C");
        Mockito.when(clienteService.find(1)).thenReturn(cliente);
        Mockito.when(clienteService.eliminar(1)).thenReturn(clienteDtoResponse);

        // When
        ClienteDtoResponse clienteDtoResponseEliminado = clienteService.eliminar(1);

        // Then
        assertEquals(clienteDtoResponse, clienteDtoResponseEliminado);
    }
}
