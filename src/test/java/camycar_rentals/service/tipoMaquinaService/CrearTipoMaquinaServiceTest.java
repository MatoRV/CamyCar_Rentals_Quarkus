package camycar_rentals.service.tipoMaquinaService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import base.dto.tipomaquina.TipoMaquinaDtoRequest;
import base.dto.tipomaquina.TipoMaquinaDtoResponse;
import camycar_rentals.service.TipoMaquinaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class CrearTipoMaquinaServiceTest {

    @InjectMock
    TipoMaquinaService tipoMaquinaService;

    @Test
    @DisplayName("Prueba si crea una maquina")
    void crearTipoMaquinaOk() {
        // Given
        TipoMaquinaDtoResponse tipoMaquinaDtoResponse = new TipoMaquinaDtoResponse(1, "Torito");
        TipoMaquinaDtoRequest tipoMaquinaDtoRequest = new TipoMaquinaDtoRequest("Torito");
        Mockito.when(tipoMaquinaService.crearTipoMaquina(tipoMaquinaDtoRequest)).thenReturn(tipoMaquinaDtoResponse);

        // When
        TipoMaquinaDtoResponse tipoMaquinaDtoResponseDevuelto = tipoMaquinaService.crearTipoMaquina(new TipoMaquinaDtoRequest("Torito"));

        // Then
        assertEquals(tipoMaquinaDtoResponse, tipoMaquinaDtoResponseDevuelto);
    }
}
