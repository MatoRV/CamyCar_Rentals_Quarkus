package camycar_rentals.repository.usuarioRepository;

import camycar_rentals.repository.UsuarioRepository;
import jakarta.inject.Inject;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ObtenerPorDniRepositoryTest {

    @Inject
    UsuarioRepository usuarioRepository;

    //    @Test
    //    @DisplayName("prueba para obtener los usuario por dni")
    //    void obtenerPorDniRepositoryOk() {
    //        // Given
    //        Usuario usuario = new Usuario(1,"usuario 1","1","1234","11111111C");
    //
    //        // When
    //        Boolean existe = usuarioRepository.obtenerPorDni(usuario);
    //
    //        // Then
    //        assertTrue(existe);
    //    }
}
