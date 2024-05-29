package camycar_rentals.service.maquinaService;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @InjectMock
    DiaReservadoRepository diaReservadoRepository;

    @Test
    @DisplayName("Prueba que se edita una maquina")
    void editarMaquinaPorIdOk() {
        // Given
        TipoMaquina tipoMaquina = new TipoMaquina(1, "Torito");
        TipoMaquinaNombreDtoResponse tipoMaquinaNombreDtoResponse = new TipoMaquinaNombreDtoResponse("Torito");
        Maquina maquina = new Maquina(1, "F1", "M1", 1500, EstadoEnum.DISPONIBLE, tipoMaquina, 4500);
        Maquina maquinaEdit = new Maquina(1, "F1", "M2", 2000, EstadoEnum.DISPONIBLE, tipoMaquina, 4500);
        DiasReservadosDtoResponse diasReservadosDtoResponse = new DiasReservadosDtoResponse(List.of());
        MaquinaDtoResponse maquinaDtoResponse =
                new MaquinaDtoResponse(1, "F1", "M2", 2000, EstadoEnum.DISPONIBLE, tipoMaquinaNombreDtoResponse, 4500, diasReservadosDtoResponse);
        MaquinaDtoRequest maquinaDtoRequest = new MaquinaDtoRequest("F1", "M2", 2000, 1, EstadoEnum.DISPONIBLE, 4500);
        Mockito.when(tipoMaquinaRepository.find(1)).thenReturn(tipoMaquina);
        Mockito.when(maquinaRepository.find(1)).thenReturn(maquina);
        Mockito.when(maquinaRepository.edit(maquina)).thenReturn(maquinaEdit);
        when(diaReservadoRepository.obtenerDiasReservadosPorIdMaquina(1)).thenReturn(List.of());

        // When
        MaquinaDtoResponse maquinaDtoResponseDevuelto = maquinaService.editarMaquinaPorId(1, maquinaDtoRequest);

        // Then
        assertEquals(maquinaDtoResponse, maquinaDtoResponseDevuelto);
    }
}
