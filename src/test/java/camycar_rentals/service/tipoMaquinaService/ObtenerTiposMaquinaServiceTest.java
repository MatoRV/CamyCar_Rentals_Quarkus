package camycar_rentals.service.tipoMaquinaService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import base.dto.tipomaquina.TipoMaquinaDtoResponse;
import camycar_rentals.service.TipoMaquinaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ObtenerTiposMaquinaServiceTest {

    @InjectMock
    TipoMaquinaService tipoMaquinaService;

    @Test
    @DisplayName("Prueba que se devuelven todos los tipos de maquina")
    void obtenerTiposMaquinaServiceTestOk() {
        // Given
        TipoMaquinaDtoResponse tipoMaquinaDtoResponse1 = new TipoMaquinaDtoResponse(1, "Torito");
        TipoMaquinaDtoResponse tipoMaquinaDtoResponse2 = new TipoMaquinaDtoResponse(2, "Elevadora");
        List<TipoMaquinaDtoResponse> tipoMaquinaDtoResponseList = List.of(tipoMaquinaDtoResponse1, tipoMaquinaDtoResponse2);
        Mockito.when(tipoMaquinaService.obtenerTiposMaquina()).thenReturn(tipoMaquinaDtoResponseList);

        // When
        List<TipoMaquinaDtoResponse> tipoMaquinaDtoResponseListDevuelto = tipoMaquinaService.obtenerTiposMaquina();

        // Then
        assertEquals(tipoMaquinaDtoResponseList, tipoMaquinaDtoResponseListDevuelto);
    }
}
