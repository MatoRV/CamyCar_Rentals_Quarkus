package camycar_rentals.service.maquinaService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.List;
import base.dto.maquina.MaquinaDtoResponse;
import base.dto.tipomaquina.TipoMaquinaNombreDtoResponse;
import camycar_rentals.domain.Maquina;
import camycar_rentals.domain.TipoMaquina;
import camycar_rentals.domain.enumerados.EstadoEnum;
import camycar_rentals.repository.DiaReservadoRepository;
import camycar_rentals.repository.MaquinaRepository;
import camycar_rentals.service.MaquinaService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ObtenerMaquinasServiceTest {

    @InjectMock
    MaquinaRepository maquinaRepository;

    @Inject
    MaquinaService maquinaService;

    @InjectMock
    DiaReservadoRepository diaReservadoRepository;

    @Test
    @DisplayName("Prueba para obtener maquinas")
    void obtenerMaquinasOk() {
        // Given
        TipoMaquina tipoMaquina = new TipoMaquina(1, "Torito");
        TipoMaquinaNombreDtoResponse tipoMaquinaNombreDtoResponse = new TipoMaquinaNombreDtoResponse("Torito");
        Maquina maquina1 = new Maquina(1, "F1", "M1", 1500, EstadoEnum.DISPONIBLE, tipoMaquina, 4500);
        Maquina maquina2 = new Maquina(2, "F1", "M2", 2000, EstadoEnum.DISPONIBLE, tipoMaquina, 4500);
        MaquinaDtoResponse maquinaDtoResponse1 = new MaquinaDtoResponse(1, "F1", "M1", 1500, EstadoEnum.DISPONIBLE, tipoMaquinaNombreDtoResponse, 4500, null);
        MaquinaDtoResponse maquinaDtoResponse2 = new MaquinaDtoResponse(2, "F1", "M2", 2000, EstadoEnum.DISPONIBLE, tipoMaquinaNombreDtoResponse, 4500, null);
        List<MaquinaDtoResponse> maquinaDtoResponses = List.of(maquinaDtoResponse1, maquinaDtoResponse2);
        when(maquinaRepository.findAll(List.of())).thenReturn(List.of(maquina1, maquina2));
        when(diaReservadoRepository.obtenerDiasReservadosPorIdMaquina(1)).thenReturn(null);

        // When
        List<MaquinaDtoResponse> maquinaDtoResponseDevuelto = maquinaService.obtenerMaquinas();

        // Then
        assertEquals(maquinaDtoResponses, maquinaDtoResponseDevuelto);
    }
}
