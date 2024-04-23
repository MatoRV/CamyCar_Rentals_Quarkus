package camycar_rentals.service.clienteService;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
public class EliminarClienteServiceTest {

    @Inject
    ClienteService clienteService;

    @InjectMock
    ClienteRepository clienteRepository;

    @Test
    @DisplayName("Elimina un cliente")
    void eliminarClienteOk() {
        // Given
        Cliente cliente = new Cliente(1, "Cliente 1", "cliente.1", "1234", "11111111C");
        ClienteDtoResponse clienteDtoResponse = new ClienteDtoResponse(1, "Cliente 1", "cliente.1", "11111111C");
        Mockito.when(clienteRepository.find(1)).thenReturn(cliente);
        Mockito.when(clienteRepository.remove(cliente)).thenReturn(cliente);

        // When
        ClienteDtoResponse clienteDtoResponseEliminado = clienteService.eliminar(1);

        // Then
        assertEquals(clienteDtoResponse, clienteDtoResponseEliminado);
    }
}
