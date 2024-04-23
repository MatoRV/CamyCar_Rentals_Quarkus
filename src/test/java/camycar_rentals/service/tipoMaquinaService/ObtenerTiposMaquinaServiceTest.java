package camycar_rentals.service.tipoMaquinaService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
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
public class ObtenerTiposMaquinaServiceTest {

    @Inject
    TipoMaquinaService tipoMaquinaService;

    @InjectMock
    TipoMaquinaRepository tipoMaquinaRepository;

    @Test
    @DisplayName("Prueba que se devuelven todos los tipos de maquina")
    void obtenerTiposMaquinaServiceTestOk() {
        // Given
        TipoMaquina tipoMaquina1 = new TipoMaquina(1, "Torito");
        TipoMaquina tipoMaquina2 = new TipoMaquina(2, "Elevadora");
        TipoMaquinaDtoResponse tipoMaquinaDtoResponse1 = new TipoMaquinaDtoResponse(1, "Torito");
        TipoMaquinaDtoResponse tipoMaquinaDtoResponse2 = new TipoMaquinaDtoResponse(2, "Elevadora");
        List<TipoMaquinaDtoResponse> tipoMaquinaDtoResponseList = List.of(tipoMaquinaDtoResponse1, tipoMaquinaDtoResponse2);
        Mockito.when(tipoMaquinaRepository.findAll(List.of())).thenReturn(List.of(tipoMaquina1, tipoMaquina2));

        // When
        List<TipoMaquinaDtoResponse> tipoMaquinaDtoResponseListDevuelto = tipoMaquinaService.obtenerTiposMaquina();

        // Then
        assertEquals(tipoMaquinaDtoResponseList, tipoMaquinaDtoResponseListDevuelto);
    }
}
