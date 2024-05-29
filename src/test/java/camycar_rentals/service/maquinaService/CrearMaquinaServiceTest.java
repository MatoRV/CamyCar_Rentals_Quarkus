package camycar_rentals.service.maquinaService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.List;
import base.dto.diareservado.DiasReservadosDtoResponse;
import base.dto.maquina.MaquinaDtoRequest;
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
public class CrearMaquinaServiceTest {

    @InjectMock
    TipoMaquinaService tipoMaquinaService;

    @Inject
    MaquinaService maquinaService;

    @InjectMock
    MaquinaRepository maquinaRepository;

    @InjectMock
    DiaReservadoRepository diaReservadoRepository;

    @Test
    @DisplayName("Prueba para crear una maquina")
    void crearMaquinaOk() {
        // Given
        TipoMaquina tipoMaquina = new TipoMaquina(1, "Torito");
        TipoMaquinaNombreDtoResponse tipoMaquinaNombreDtoResponse = new TipoMaquinaNombreDtoResponse("Torito");
        Maquina maquinaEsperada = new Maquina(1, "F1", "M1", 1500, EstadoEnum.DISPONIBLE, tipoMaquina, 4500);
        DiasReservadosDtoResponse diasReservadosDtoResponse = new DiasReservadosDtoResponse(List.of());
        MaquinaDtoResponse maquinaDtoResponse =
                new MaquinaDtoResponse(1, "F1", "M1", 1500, EstadoEnum.DISPONIBLE, tipoMaquinaNombreDtoResponse, 4500, diasReservadosDtoResponse);
        MaquinaDtoRequest maquinaDtoRequest = new MaquinaDtoRequest("F1", "M1", 1500, 1, EstadoEnum.DISPONIBLE, 4500);
        when(tipoMaquinaService.find(1)).thenReturn(tipoMaquina);
        when(maquinaRepository.create(any(Maquina.class))).thenReturn(maquinaEsperada);
        when(diaReservadoRepository.obtenerDiasReservadosPorIdMaquina(1)).thenReturn(List.of());

        // When
        MaquinaDtoResponse maquinaDtoResponseDevuelto = maquinaService.crearMaquina(maquinaDtoRequest);

        // Then
        verify(tipoMaquinaService, times(1)).find(1);
        assertEquals(maquinaDtoResponse, maquinaDtoResponseDevuelto);
    }
}
