package camycar_rentals.service.reservaService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.util.List;
import base.dto.reserva.ReservaDtoResponse;
import camycar_rentals.domain.Reserva;
import camycar_rentals.repository.DiaReservadoRepository;
import camycar_rentals.repository.ReservaRepository;
import camycar_rentals.service.ReservaService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ObtenerReservasServiceTest {

    @Inject
    ReservaService reservaService;

    @InjectMock
    ReservaRepository reservaRepository;

    @InjectMock
    DiaReservadoRepository diaReservadoRepository;

    @Test
    @DisplayName("prueba para ver si se obtienen las reservas")
    void obtenerReservasOk() {
        // Given
        Reserva reserva = new Reserva(1, 1, null, 1, null, "Arrakis", LocalDate.parse("2024-04-22"), LocalDate.parse("2024-04-24"));
        List<Reserva> reservas = List.of(reserva);
        ReservaDtoResponse reservaDtoResponse = new ReservaDtoResponse(1, null, null, "Arrakis");
        List<ReservaDtoResponse> reservaDtoResponses = List.of(reservaDtoResponse);
        when(reservaRepository.findAll(List.of())).thenReturn(reservas);
        when(diaReservadoRepository.obtenerDiasReservadosPorIdMaquina(1)).thenReturn(List.of());

        // When
        List<ReservaDtoResponse> reservaDtoResponseDevuelto = reservaService.obtenerReservas();

        // Then
        assertEquals(reservaDtoResponses, reservaDtoResponseDevuelto);
    }
}
