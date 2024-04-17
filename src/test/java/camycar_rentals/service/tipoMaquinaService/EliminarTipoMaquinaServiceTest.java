package camycar_rentals.service.tipoMaquinaService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import base.dto.tipomaquina.TipoMaquinaDtoResponse;
import camycar_rentals.domain.TipoMaquina;
import camycar_rentals.repository.TipoMaquinaRepository;
import camycar_rentals.service.TipoMaquinaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class EliminarTipoMaquinaServiceTest {

    @InjectMock
    TipoMaquinaService tipoMaquinaService;

    @InjectMock
    TipoMaquinaRepository tipoMaquinaRepository;

    @Test
    @DisplayName("Prueba que elimina un tipo maquina")
    void eliminarTipoMaquinaOk() {
        // Given
        TipoMaquina tipoMaquina = new TipoMaquina(1, "Torito");
        TipoMaquinaDtoResponse tipoMaquinaDtoResponse = new TipoMaquinaDtoResponse(1, "Torito");
        Mockito.when(tipoMaquinaRepository.find(1)).thenReturn(tipoMaquina);
        Mockito.when(tipoMaquinaService.eliminarTipoMaquinaPorId(1)).thenReturn(tipoMaquinaDtoResponse);

        // When
        TipoMaquinaDtoResponse tipoMaquinaDtoResponseDevuelto = tipoMaquinaService.eliminarTipoMaquinaPorId(1);

        // Then
        assertEquals(tipoMaquinaDtoResponse, tipoMaquinaDtoResponseDevuelto);
    }
}
