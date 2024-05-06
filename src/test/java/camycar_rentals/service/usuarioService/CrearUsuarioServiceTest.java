package camycar_rentals.service.usuarioService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import base.dto.usuario.UsuarioDtoRequest;
import base.dto.usuario.UsuarioDtoResponse;
import camycar_rentals.domain.Usuario;
import camycar_rentals.repository.UsuarioRepository;
import camycar_rentals.service.UsuarioService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class CrearUsuarioServiceTest {

    @Inject
    UsuarioService usuarioService;

    @InjectMock
    UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Comprueba que se crean los clientes")
    void crearClienteOk() {
        // Given
        Usuario usuarioDado = new Usuario(null, "Prueba 1", "1234", "11111111C", "apellido1", "apellido2", "prueba@example.com");
        Usuario usuarioEsperado = new Usuario(1, "Prueba 1", "apellido1", "apellido2", "prueba@example.com", "1234", "11111111C");
        UsuarioDtoRequest usuarioDtoRequest = new UsuarioDtoRequest("Prueba 1", "apellido1", "apellido2", "prueba@example.com", "1234", "11111111C");
        UsuarioDtoResponse usuarioDtoResponse = new UsuarioDtoResponse(1, "Prueba 1", "apellido1", "apellido2", "prueba@example.com", "11111111C");
        Mockito.when(usuarioRepository.create(usuarioDado)).thenReturn(usuarioEsperado);

        // When
        UsuarioDtoResponse usuarioDtoResponseDevuelto = usuarioService.crear(usuarioDtoRequest);

        // Then
        assertEquals(usuarioDtoResponse, usuarioDtoResponseDevuelto);
    }
}
