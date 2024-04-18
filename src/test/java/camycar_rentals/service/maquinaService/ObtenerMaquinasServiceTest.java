package camycar_rentals.service.maquinaService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import base.dto.maquina.MaquinaDtoResponse;
import camycar_rentals.domain.TipoMaquina;
import camycar_rentals.domain.enumerados.EstadoEnum;
import camycar_rentals.repository.TipoMaquinaRepository;
import camycar_rentals.service.MaquinaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ObtenerMaquinasServiceTest {

    @InjectMock
    TipoMaquinaRepository tipoMaquinaRepository;

    @InjectMock
    MaquinaService maquinaService;

    @Test
    @DisplayName("Prueba para obtener maquinas")
    void obtenerMaquinasOk() {
        // Given
        TipoMaquina tipoMaquina = new TipoMaquina(1, "Torito");
        MaquinaDtoResponse maquinaDtoResponse1 = new MaquinaDtoResponse(1, "F1", "M1", 1500, EstadoEnum.DISPONIBLE, "Torito");
        MaquinaDtoResponse maquinaDtoResponse2 = new MaquinaDtoResponse(2, "F1", "M2", 2000, EstadoEnum.DISPONIBLE, "Torito");
        List<MaquinaDtoResponse> maquinaDtoResponses = List.of(maquinaDtoResponse1, maquinaDtoResponse2);
        Mockito.when(tipoMaquinaRepository.find(1)).thenReturn(tipoMaquina);
        Mockito.when(maquinaService.obtenerMaquinas()).thenReturn(maquinaDtoResponses);

        // When
        List<MaquinaDtoResponse> maquinaDtoResponseDevuelto = maquinaService.obtenerMaquinas();

        // Then
        assertEquals(maquinaDtoResponses, maquinaDtoResponseDevuelto);
    }
}
