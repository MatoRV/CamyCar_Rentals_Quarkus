package camycar_rentals.service.maquinaService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import base.dto.maquina.MaquinaDtoResponse;
import camycar_rentals.domain.Maquina;
import camycar_rentals.domain.TipoMaquina;
import camycar_rentals.repository.MaquinaRepository;
import camycar_rentals.service.MaquinaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ObtenerMaquinaPorCapacidadCargaServiceTest {

    @InjectMock
    MaquinaService maquinaService;

    @InjectMock
    MaquinaRepository maquinaRepository;

    @Test
    @DisplayName("Prueba si se obtiene una lista de maquina según su capacidad de carga")
    void obtenerMaquinaPorCapacidadCargaOk() {
        // Given
        TipoMaquina tipoMaquina = new TipoMaquina(1, "Torito");
        Maquina maquina1 = new Maquina(1, "F1", "M1", 1500, tipoMaquina);
        MaquinaDtoResponse maquinaDtoResponse1 = new MaquinaDtoResponse(1, "F1", "M1", 1500, "Torito");
        Mockito.when(maquinaRepository.obtenerMaquinasPorCapacidadCarga(1500)).thenReturn(List.of(maquina1));
        Mockito.when(maquinaService.obtenerMaquinasPorCapacidadCarga(1500)).thenReturn(List.of(maquinaDtoResponse1));

        // When
        List<MaquinaDtoResponse> maquinaDtoResponsesDevuelto = maquinaService.obtenerMaquinasPorCapacidadCarga(1500);

        // Then
        assertEquals(List.of(maquinaDtoResponse1), maquinaDtoResponsesDevuelto);
    }
}
