package camycar_rentals.service.maquinaService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import base.dto.maquina.MaquinaDtoResponse;
import camycar_rentals.domain.Maquina;
import camycar_rentals.domain.TipoMaquina;
import camycar_rentals.domain.enumerados.EstadoEnum;
import camycar_rentals.repository.MaquinaRepository;
import camycar_rentals.service.MaquinaService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ObtenerMaquinasPorFabricanteServiceTest {

    @InjectMock
    MaquinaRepository maquinaRepository;

    @Inject
    MaquinaService maquinaService;

    @Test
    @DisplayName("Prueba para obtener maquinas según fabricante")
    void obtenerMaquinasPorFabricanteOk() {
        // Given
        TipoMaquina tipoMaquina = new TipoMaquina(1, "Torito");
        Maquina maquina1 = new Maquina(1, "F1", "M1", 1500, EstadoEnum.DISPONIBLE, tipoMaquina);
        MaquinaDtoResponse maquinaDtoResponse = new MaquinaDtoResponse(1, "F1", "M1", 1500, EstadoEnum.DISPONIBLE, "Torito");
        Mockito.when(maquinaRepository.obtenerMaquinaPorTipoMaquinaYCapacidadCargaYFabricanteYEstado(null, null, "F1", null)).thenReturn(List.of(maquina1));

        // When
        List<MaquinaDtoResponse> maquinaDtoResponseDevuelto =
                maquinaService.obtenerMaquinaPorTipoMaquinaYCapacidadCargaYFabricanteYEstado(null, null, "F1", null);

        // Then
        assertEquals(List.of(maquinaDtoResponse), maquinaDtoResponseDevuelto);
    }
}
