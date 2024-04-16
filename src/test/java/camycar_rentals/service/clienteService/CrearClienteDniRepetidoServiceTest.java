package camycar_rentals.service.clienteService;

import base.constant.errores.ErroresGeneral;
import base.dto.cliente.ClienteDtoRequest;
import camycar_rentals.domain.Cliente;
import camycar_rentals.repository.ClienteRepository;
import camycar_rentals.service.ClienteService;
import io.quarkus.security.ForbiddenException;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
public class CrearClienteDniRepetidoServiceTest {

    @InjectMock
    ClienteRepository clienteRepository;

    @Inject
    ClienteService clienteService;

    @Test
    @DisplayName("Comprueba que devuelve ForbiddenException si se repite el dni")
    void crearClienteDniRepetido() {
        // Given
        Cliente cliente = new Cliente(null,"Prueba 1","cliente.1","1234","11111111C");
        ClienteDtoRequest clienteDtoRequest = new ClienteDtoRequest("Prueba 1","cliente.1","1234","11111111C");
        Mockito.when(clienteRepository.obtenerPorDni(cliente)).thenReturn(true);

        // When Then
        ForbiddenException exception = assertThrows(ForbiddenException.class, () -> {clienteService.crear(clienteDtoRequest);});
        assertEquals(ErroresGeneral.GEN_0002, exception.getMessage());
    }
}
