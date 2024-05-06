package camycar_rentals.service.usuarioService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import base.constant.errores.ErroresGeneral;
import base.dto.usuario.UsuarioDtoRequest;
import camycar_rentals.domain.Usuario;
import camycar_rentals.repository.UsuarioRepository;
import camycar_rentals.service.UsuarioService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import io.quarkus.security.ForbiddenException;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class CrearUsuarioDniRepetidoServiceTest {

    @InjectMock
    UsuarioRepository usuarioRepository;

    @Inject
    UsuarioService usuarioService;

    @Test
    @DisplayName("Comprueba que devuelve ForbiddenException si se repite el dni")
    void crearClienteDniRepetido() {
        // Given
        Usuario usuario = new Usuario(null, "Prueba 1", "1234", "11111111C", "apellido1", "apellido2", "prueba@example.com");
        UsuarioDtoRequest usuarioDtoRequest = new UsuarioDtoRequest("Prueba 1", "apellido1", "apellido2", "prueba@example.com", "1234", "11111111C");
        Mockito.when(usuarioRepository.obtenerPorDni(usuario)).thenReturn(true);

        // When Then
        ForbiddenException exception = assertThrows(ForbiddenException.class, () -> {usuarioService.crear(usuarioDtoRequest);});
        assertEquals(ErroresGeneral.GEN_0002, exception.getMessage());
    }
}
