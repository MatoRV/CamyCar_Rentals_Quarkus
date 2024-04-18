package camycar_rentals.service.maquinaService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import base.dto.maquina.MaquinaDtoRequest;
import base.dto.maquina.MaquinaDtoResponse;
import camycar_rentals.domain.Maquina;
import camycar_rentals.domain.TipoMaquina;
import camycar_rentals.repository.MaquinaRepository;
import camycar_rentals.repository.TipoMaquinaRepository;
import camycar_rentals.service.MaquinaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class EditarMaquinaPorIdServiceTest {

    @InjectMock
    MaquinaService maquinaService;

    @InjectMock
    MaquinaRepository maquinaRepository;

    @InjectMock
    TipoMaquinaRepository tipoMaquinaRepository;

    @Test
    @DisplayName("Prueba que se edita una maquina")
    void editarMaquinaPorIdOk() {
        // Given
        TipoMaquina tipoMaquina = new TipoMaquina(1, "Torito");
        Maquina maquina = new Maquina(1, "F1", "M1", 1500, tipoMaquina);
        MaquinaDtoResponse maquinaDtoResponse = new MaquinaDtoResponse(1, "F1", "M2", 2000, "Torito");
        MaquinaDtoRequest maquinaDtoRequest = new MaquinaDtoRequest("F1", "M2", 2000, 1);
        Mockito.when(tipoMaquinaRepository.find(1)).thenReturn(tipoMaquina);
        Mockito.when(maquinaRepository.find(1)).thenReturn(maquina);
        Mockito.when(maquinaService.editarMaquinaPorId(1, maquinaDtoRequest)).thenReturn(maquinaDtoResponse);

        // When
        MaquinaDtoResponse maquinaDtoResponseDevuelto = maquinaService.editarMaquinaPorId(1, new MaquinaDtoRequest("F1","M2",2000,1));

        // Then
        assertEquals(maquinaDtoResponse, maquinaDtoResponseDevuelto);
    }
}
