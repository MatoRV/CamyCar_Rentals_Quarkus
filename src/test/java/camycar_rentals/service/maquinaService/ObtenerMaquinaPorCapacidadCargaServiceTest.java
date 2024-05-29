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
import camycar_rentals.service.TipoMaquinaService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ObtenerMaquinaPorCapacidadCargaServiceTest {

    @Inject
    MaquinaService maquinaService;

    @InjectMock
    MaquinaRepository maquinaRepository;

    @InjectMock
    TipoMaquinaService tipoMaquinaService;

    @InjectMock
    DiaReservadoRepository diaReservadoRepository;

    @Test
    @DisplayName("Prueba si se obtiene una lista de maquina según su capacidad de carga")
    void obtenerMaquinaPorCapacidadCargaOk() {
        // Given
        TipoMaquina tipoMaquina = new TipoMaquina(1, "Torito");
        TipoMaquinaNombreDtoResponse tipoMaquinaNombreDtoResponse = new TipoMaquinaNombreDtoResponse("Torito");
        Maquina maquina1 = new Maquina(1, "F1", "M1", 1500, EstadoEnum.DISPONIBLE, tipoMaquina, 4500);
        MaquinaDtoResponse maquinaDtoResponse1 = new MaquinaDtoResponse(1, "F1", "M1", 1500, EstadoEnum.DISPONIBLE, tipoMaquinaNombreDtoResponse, 4500, null);
        when(maquinaRepository.obtenerMaquinaPorTipoMaquinaYCapacidadCargaYFabricanteYEstado(null, 1500, null, null)).thenReturn(List.of(maquina1));
        when(diaReservadoRepository.obtenerDiasReservadosPorIdMaquina(1)).thenReturn(null);

        // When
        List<MaquinaDtoResponse> maquinaDtoResponsesDevuelto =
                maquinaService.obtenerMaquinaPorTipoMaquinaYCapacidadCargaYFabricanteYEstado(null, 1500, null, null);

        // Then
        assertEquals(List.of(maquinaDtoResponse1), maquinaDtoResponsesDevuelto);
    }
}
