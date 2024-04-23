package camycar_rentals.repository.clienteRepository;

import static org.wildfly.common.Assert.assertTrue;
import camycar_rentals.domain.Cliente;
import camycar_rentals.repository.ClienteRepository;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ObtenerPorDniRepositoryTest {

    @Inject
    ClienteRepository clienteRepository;

    @Test
    @DisplayName("prueba para obtener los cliente por dni")
    void obtenerPorDniRepositoryOk() {
        // Given
        Cliente cliente = new Cliente(1,"Cliente 1","cliente.1","1234","11111111C");

        // When
        Boolean existe = clienteRepository.obtenerPorDni(cliente);

        // Then
        assertTrue(existe);
    }
}
