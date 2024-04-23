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
public class ObtenerMaquinasServiceTest {

    @InjectMock
    MaquinaRepository maquinaRepository;

    @Inject
    MaquinaService maquinaService;

    @Test
    @DisplayName("Prueba para obtener maquinas")
    void obtenerMaquinasOk() {
        // Given
        TipoMaquina tipoMaquina = new TipoMaquina(1, "Torito");
        Maquina maquina1 = new Maquina(1, "F1", "M1", 1500, EstadoEnum.DISPONIBLE, tipoMaquina);
        Maquina maquina2 = new Maquina(2, "F1", "M2", 2000, EstadoEnum.DISPONIBLE, tipoMaquina);
        MaquinaDtoResponse maquinaDtoResponse1 = new MaquinaDtoResponse(1, "F1", "M1", 1500, EstadoEnum.DISPONIBLE, "Torito");
        MaquinaDtoResponse maquinaDtoResponse2 = new MaquinaDtoResponse(2, "F1", "M2", 2000, EstadoEnum.DISPONIBLE, "Torito");
        List<MaquinaDtoResponse> maquinaDtoResponses = List.of(maquinaDtoResponse1, maquinaDtoResponse2);
        Mockito.when(maquinaRepository.findAll(List.of())).thenReturn(List.of(maquina1, maquina2));

        // When
        List<MaquinaDtoResponse> maquinaDtoResponseDevuelto = maquinaService.obtenerMaquinas();

        // Then
        assertEquals(maquinaDtoResponses, maquinaDtoResponseDevuelto);
    }
}
