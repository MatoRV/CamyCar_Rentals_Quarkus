package camycar_rentals.service.maquinaService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import base.dto.maquina.MaquinaDtoRequest;
import base.dto.maquina.MaquinaDtoResponse;
import camycar_rentals.domain.Maquina;
import camycar_rentals.domain.TipoMaquina;
import camycar_rentals.domain.enumerados.EstadoEnum;
import camycar_rentals.repository.MaquinaRepository;
import camycar_rentals.repository.TipoMaquinaRepository;
import camycar_rentals.service.MaquinaService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class EditarMaquinaPorIdServiceTest {

    @Inject
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
        Maquina maquina = new Maquina(1, "F1", "M1", 1500, EstadoEnum.DISPONIBLE, tipoMaquina);
        Maquina maquinaEdit = new Maquina(1, "F1", "M2", 2000, EstadoEnum.DISPONIBLE, tipoMaquina);
        MaquinaDtoResponse maquinaDtoResponse = new MaquinaDtoResponse(1, "F1", "M2", 2000, EstadoEnum.DISPONIBLE, "Torito");
        MaquinaDtoRequest maquinaDtoRequest = new MaquinaDtoRequest("F1", "M2", 2000, 1, EstadoEnum.DISPONIBLE);
        Mockito.when(tipoMaquinaRepository.find(1)).thenReturn(tipoMaquina);
        Mockito.when(maquinaRepository.find(1)).thenReturn(maquina);
        Mockito.when(maquinaRepository.edit(maquina)).thenReturn(maquinaEdit);

        // When
        MaquinaDtoResponse maquinaDtoResponseDevuelto = maquinaService.editarMaquinaPorId(1, maquinaDtoRequest);

        // Then
        assertEquals(maquinaDtoResponse, maquinaDtoResponseDevuelto);
    }
}
