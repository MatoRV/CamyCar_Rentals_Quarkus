package camycar_rentals.service.maquinaService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import base.dto.maquina.MaquinaDtoResponse;
import camycar_rentals.domain.Maquina;
import camycar_rentals.domain.TipoMaquina;
import camycar_rentals.domain.enumerados.EstadoEnum;
import camycar_rentals.repository.MaquinaRepository;
import camycar_rentals.service.MaquinaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ObtenerMaquinasPorIdServiceTest {

    @InjectMock
    MaquinaService maquinaService;

    @InjectMock
    MaquinaRepository maquinaRepository;

    @Test
    @DisplayName("Prueba para obtener una maquina por su id")
    void obtenerMaquinasPorIdOk() {
        // Given
        TipoMaquina tipoMaquina = new TipoMaquina(1, "Torito");
        Maquina maquina = new Maquina(1, "F1", "M1", 1500, EstadoEnum.DISPONIBLE, tipoMaquina);
        MaquinaDtoResponse maquinaDtoResponse = new MaquinaDtoResponse(1, "F1", "M1", 1500, EstadoEnum.DISPONIBLE, "Torito");
        Mockito.when(maquinaRepository.find(1)).thenReturn(maquina);
        Mockito.when(maquinaService.obtenerMaquinaPorId(1)).thenReturn(maquinaDtoResponse);

        // When
        MaquinaDtoResponse maquinaDtoResponseDevuelto = maquinaService.obtenerMaquinaPorId(1);

        // Then
        assertEquals(maquinaDtoResponse, maquinaDtoResponseDevuelto);
    }
}
