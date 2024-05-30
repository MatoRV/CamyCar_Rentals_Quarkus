package camycar_rentals.service.reservaService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.util.List;
import base.dto.diareservado.DiasReservadosDtoResponse;
import base.dto.maquina.MaquinaDtoResponse;
import base.dto.reserva.ReservaDtoResponse;
import base.dto.tipomaquina.TipoMaquinaNombreDtoResponse;
import camycar_rentals.domain.DiaReservado;
import camycar_rentals.domain.Maquina;
import camycar_rentals.domain.Reserva;
import camycar_rentals.domain.TipoMaquina;
import camycar_rentals.domain.enumerados.EstadoEnum;
import camycar_rentals.repository.DiaReservadoRepository;
import camycar_rentals.repository.ReservaRepository;
import camycar_rentals.service.ReservaService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class EliminarReservaServiceTest {

    @Inject
    ReservaService reservaService;

    @InjectMock
    ReservaRepository reservaRepository;

    @InjectMock
    DiaReservadoRepository diaReservadoRepository;

    @Test
    @DisplayName("Prueba para probar que se elimina la reserva")
    void eliminarReservaOk() {
        // Given
        TipoMaquina tipoMaquina = new TipoMaquina(1, "Torito");
        Maquina maquina = new Maquina(1, "F1", "M1", 1500, EstadoEnum.DISPONIBLE, tipoMaquina, 4500);
        Reserva reservaEsperada = new Reserva(1, 1, maquina, 1, null, "direccion 1", LocalDate.parse("2024-04-22"), LocalDate.parse("2024-04-24"));
        List<DiaReservado> diaReservados =
                List.of(new DiaReservado(1, maquina, LocalDate.parse("2024-04-22")), new DiaReservado(2, maquina, LocalDate.parse("2024-04-23")),
                        new DiaReservado(3, maquina, LocalDate.parse("2024-04-24")));
        DiasReservadosDtoResponse diasReservadosDtoResponse =
                new DiasReservadosDtoResponse(List.of(LocalDate.parse("2024-04-22"), LocalDate.parse("2024-04-23"), LocalDate.parse("2024-04-24")));
        TipoMaquinaNombreDtoResponse tipoMaquinaNombreDtoResponse = new TipoMaquinaNombreDtoResponse("Torito");
        MaquinaDtoResponse maquinaDtoResponse =
                new MaquinaDtoResponse(1, "F1", "M1", 1500, EstadoEnum.DISPONIBLE, tipoMaquinaNombreDtoResponse, 4500, diasReservadosDtoResponse);
        ReservaDtoResponse reservaDtoResponse = new ReservaDtoResponse(1, maquinaDtoResponse, null, "direccion 1");
        when(reservaRepository.find(1)).thenReturn(reservaEsperada);
        when(reservaRepository.remove(reservaEsperada)).thenReturn(reservaEsperada);
        when(diaReservadoRepository.obtenerDiasReservadosPorIdMaquina(1)).thenReturn(diaReservados);

        // When
        ReservaDtoResponse reservaDtoResponseDevuelta = reservaService.eliminarReserva(1);

        // Then
        assertEquals(reservaDtoResponse, reservaDtoResponseDevuelta);
    }
}
