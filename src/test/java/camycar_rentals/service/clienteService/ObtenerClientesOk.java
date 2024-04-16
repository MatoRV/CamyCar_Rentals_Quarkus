package camycar_rentals.service.clienteService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import javax.inject.Inject;
import base.dto.cliente.ClienteDtoResponse;
import camycar_rentals.domain.Cliente;
import camycar_rentals.service.ClienteService;
import org.gradle.internal.impldep.javax.annotation.meta.When;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ObtenerClientesOk {

    @InjectMock
    ClienteService clienteService;

    @Test
    @DisplayName("Obtiene todos los clientes")
    void obtenerClientesOk() {
        // Given
        List<Cliente> clientes = List.of(new Cliente(1, "Roberto", "robert", "1234", "78496746T"), new Cliente(2, "Alicia", "ali", "alicita", "96874865Y"));
        ClienteDtoResponse clienteDtoResponse1 = new ClienteDtoResponse(1,"Roberto","robert","78496746T");
        ClienteDtoResponse clienteDtoResponse2 = new ClienteDtoResponse(2, "Alicia", "ali","96874865Y");
        List<ClienteDtoResponse> clienteDtoResponseList = List.of(clienteDtoResponse1, clienteDtoResponse2);
        Mockito.when(clienteService.obtenerClientes()).thenReturn(clienteDtoResponseList);

        // When
        List<ClienteDtoResponse> clienteDtoResponseDevuelto = clienteService.obtenerClientes();

        // Then
        assertEquals(clienteDtoResponseList, clienteDtoResponseDevuelto);
    }
}
