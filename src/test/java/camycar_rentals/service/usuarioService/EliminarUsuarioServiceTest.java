package camycar_rentals.service.usuarioService;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
public class EliminarUsuarioServiceTest {

    @Inject
    UsuarioService usuarioService;

    @InjectMock
    UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Elimina un usuario")
    void eliminarClienteOk() {
        // Given
        Usuario usuario = new Usuario(1, "usuario 1", "1234", "11111111C", "apellido1", "apellido2", "prueba@example.com");
        UsuarioDtoResponse usuarioDtoResponse = new UsuarioDtoResponse(1, "usuario 1", "apellido1", "apellido2", "prueba@example.com", "11111111C");
        Mockito.when(usuarioRepository.find(1)).thenReturn(usuario);
        Mockito.when(usuarioRepository.remove(usuario)).thenReturn(usuario);

        // When
        UsuarioDtoResponse usuarioDtoResponseEliminado = usuarioService.eliminar(1);

        // Then
        assertEquals(usuarioDtoResponse, usuarioDtoResponseEliminado);
    }
}
