package camycar_rentals.repository.clienteRepository;

import camycar_rentals.repository.UsuarioRepository;
import jakarta.inject.Inject;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ObtenerPorDniRepositoryTest {

    @Inject
    UsuarioRepository usuarioRepository;

    //    @Test
    //    @DisplayName("prueba para obtener los cliente por dni")
    //    void obtenerPorDniRepositoryOk() {
    //        // Given
    //        Cliente cliente = new Cliente(1,"Cliente 1","cliente.1","1234","11111111C");
    //
    //        // When
    //        Boolean existe = clienteRepository.obtenerPorDni(cliente);
    //
    //        // Then
    //        assertTrue(existe);
    //    }
}
