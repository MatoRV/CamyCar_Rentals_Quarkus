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
public class ObtenerMaquinaPorEstadoServiceTest {

    @Inject
    MaquinaService maquinaService;

    @InjectMock
    MaquinaRepository maquinaRepository;

    @InjectMock
    DiaReservadoRepository diaReservadoRepository;

    @Test
    @DisplayName("Prueba para obtener maquinas según estado")
    void obtenerMaquinaPorEstadoDisponible() {
        // Given
        TipoMaquina tipoMaquina = new TipoMaquina(1, "Torito");
        TipoMaquinaNombreDtoResponse tipoMaquinaNombreDtoResponse = new TipoMaquinaNombreDtoResponse("Torito");
        Maquina maquina = new Maquina(1, "F1", "M1", 1500, EstadoEnum.DISPONIBLE, tipoMaquina, 4500);
        MaquinaDtoResponse maquinaDtoResponse = new MaquinaDtoResponse(1, "F1", "M1", 1500, EstadoEnum.DISPONIBLE, tipoMaquinaNombreDtoResponse, 4500, null);
        when(maquinaRepository.obtenerMaquinaPorTipoMaquinaYCapacidadCargaYFabricanteYEstado(null, null, null, EstadoEnum.DISPONIBLE)).thenReturn(
                List.of(maquina));
        when(diaReservadoRepository.obtenerDiasReservadosPorIdMaquina(1)).thenReturn(null);

        // When
        List<MaquinaDtoResponse> maquinaDtoResponseDevuelto =
                maquinaService.obtenerMaquinaPorTipoMaquinaYCapacidadCargaYFabricanteYEstado(null, null, null, EstadoEnum.DISPONIBLE);

        // Then
        assertEquals(List.of(maquinaDtoResponse), maquinaDtoResponseDevuelto);
    }
}
