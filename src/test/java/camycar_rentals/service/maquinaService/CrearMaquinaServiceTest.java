package camycar_rentals.service.maquinaService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import base.dto.maquina.MaquinaDtoRequest;
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
public class CrearMaquinaServiceTest {

    @InjectMock
    TipoMaquinaRepository tipoMaquinaRepository;

    @InjectMock
    MaquinaService maquinaService;

    @Test
    @DisplayName("Prueba para crear una maquina")
    void crearMaquinaOk() {
        // Given
        TipoMaquina tipoMaquina = new TipoMaquina(1, "Torito");
        MaquinaDtoResponse maquinaDtoResponse = new MaquinaDtoResponse(1, "F1", "M1", 1500, EstadoEnum.DISPONIBLE, "Torito");
        MaquinaDtoRequest maquinaDtoRequest = new MaquinaDtoRequest("F1", "M1", 1500, 1, EstadoEnum.DISPONIBLE);
        Mockito.when(tipoMaquinaRepository.find(1)).thenReturn(tipoMaquina);
        Mockito.when(maquinaService.crearMaquina(maquinaDtoRequest)).thenReturn(maquinaDtoResponse);

        // When
        MaquinaDtoResponse maquinaDtoResponseDevuelto = maquinaService.crearMaquina(new MaquinaDtoRequest("F1", "M1", 1500, 1, EstadoEnum.DISPONIBLE));

        // Then
        assertEquals(maquinaDtoResponse, maquinaDtoResponseDevuelto);
    }
}
