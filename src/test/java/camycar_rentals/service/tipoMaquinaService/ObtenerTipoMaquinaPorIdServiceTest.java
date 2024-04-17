package camycar_rentals.service.tipoMaquinaService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import base.dto.tipomaquina.TipoMaquinaDtoResponse;
import camycar_rentals.domain.TipoMaquina;
import camycar_rentals.repository.TipoMaquinaRepository;
import camycar_rentals.service.TipoMaquinaService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ObtenerTipoMaquinaPorIdServiceTest {

    @Inject
    TipoMaquinaService tipoMaquinaService;

    @InjectMock
    TipoMaquinaRepository tipoMaquinaRepository;

    @Test
    @DisplayName("Prueba que se obtiene el tipo de maquina según su id")
    void obtenerTipoMaquinaPorIdOk() {
        // Given
        TipoMaquina tipoMaquina = new TipoMaquina(1, "Torito");
        TipoMaquinaDtoResponse tipoMaquinaDtoResponse = new TipoMaquinaDtoResponse(1, "Torito");
        Mockito.when(tipoMaquinaRepository.find(1)).thenReturn(tipoMaquina);

        // When
        TipoMaquinaDtoResponse tipoMaquinaDtoResponseDevuelto = tipoMaquinaService.obtenerTipoMaquinaPorId(1);

        // Then
        assertEquals(tipoMaquinaDtoResponse, tipoMaquinaDtoResponseDevuelto);
    }
}