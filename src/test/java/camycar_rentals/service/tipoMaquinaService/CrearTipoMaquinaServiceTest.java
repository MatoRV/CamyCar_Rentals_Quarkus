package camycar_rentals.service.tipoMaquinaService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import base.dto.tipomaquina.TipoMaquinaDtoRequest;
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
public class CrearTipoMaquinaServiceTest {

    @Inject
    TipoMaquinaService tipoMaquinaService;

    @InjectMock
    TipoMaquinaRepository tipoMaquinaRepository;

    @Test
    @DisplayName("Prueba si crea una maquina")
    void crearTipoMaquinaOk() {
        // Given
        TipoMaquina tipoMaquinaEsperado = new TipoMaquina(1, "Torito");
        TipoMaquinaDtoResponse tipoMaquinaDtoResponse = new TipoMaquinaDtoResponse(1, "Torito");
        TipoMaquinaDtoRequest tipoMaquinaDtoRequest = new TipoMaquinaDtoRequest("Torito");
        Mockito.when(tipoMaquinaRepository.create(any(TipoMaquina.class))).thenReturn(tipoMaquinaEsperado);

        // When
        TipoMaquinaDtoResponse tipoMaquinaDtoResponseDevuelto = tipoMaquinaService.crearTipoMaquina(tipoMaquinaDtoRequest);

        // Then
        assertEquals(tipoMaquinaDtoResponse, tipoMaquinaDtoResponseDevuelto);
    }
}
