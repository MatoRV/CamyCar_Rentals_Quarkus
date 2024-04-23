package camycar_rentals.service.tipoMaquinaService;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
public class EditarTipoMaquinaServiceTest {

    @Inject
    TipoMaquinaService tipoMaquinaService;

    @InjectMock
    TipoMaquinaRepository tipoMaquinaRepository;

    @Test
    @DisplayName("Prueba que edita un tipo de maquina")
    void editarTipoMaquinaOk() {
        // Given
        TipoMaquinaDtoRequest tipoMaquinaDtoRequest = new TipoMaquinaDtoRequest("Elevadora");
        TipoMaquinaDtoResponse tipoMaquinaDtoResponse = new TipoMaquinaDtoResponse(1, "Elevadora");
        TipoMaquina tipoMaquina = new TipoMaquina(1, "Torito");
        TipoMaquina tipoMaquinaEdit = new TipoMaquina(1, "Elevadora");
        Mockito.when(tipoMaquinaRepository.find(1)).thenReturn(tipoMaquina);
        Mockito.when(tipoMaquinaRepository.edit(tipoMaquina)).thenReturn(tipoMaquinaEdit);

        // When
        TipoMaquinaDtoResponse tipoMaquinaDtoResponseDevuelto = tipoMaquinaService.editarTipoMaquinaPorId(1, tipoMaquinaDtoRequest);

        // Then
        assertEquals(tipoMaquinaDtoResponse, tipoMaquinaDtoResponseDevuelto);
    }
}
