package camycar_rentals.service.usuarioService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
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
public class ObtenerUsuariosServiceTest {

    @Inject
    UsuarioService usuarioService;

    @InjectMock
    UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Obtiene todos los usuarios")
    void obtenerUsuariosOk() {
        // Given
        Usuario usuario1 = new Usuario(1, "Roberto", "1234", "78496746T", "apellido1", "apellido2", "prueba@example.com");
        Usuario usuario2 = new Usuario(2, "Alicia", "1234", "96875489P", "apellido1", "apellido2", "prueba@example.com");
        UsuarioDtoResponse usuarioDtoResponse1 = new UsuarioDtoResponse(1, "Roberto", "apellido1", "apellido2", "prueba@example.com", "78496746T");
        UsuarioDtoResponse usuarioDtoResponse2 = new UsuarioDtoResponse(2, "Alicia", "apellido1", "apellido2", "prueba@example.com", "96875489P");
        List<UsuarioDtoResponse> usuarioDtoResponseList = List.of(usuarioDtoResponse1, usuarioDtoResponse2);
        Mockito.when(usuarioRepository.findAll(List.of())).thenReturn(List.of(usuario1, usuario2));

        // When
        List<UsuarioDtoResponse> usuarioDtoResponseDevuelto = usuarioService.obtenerUsuarios();

        // Then
        assertEquals(usuarioDtoResponseList, usuarioDtoResponseDevuelto);
    }
}
