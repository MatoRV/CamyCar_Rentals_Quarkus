package camycar_rentals.service.reservaService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import base.dto.reserva.ReservaDtoResponse;
import camycar_rentals.domain.Reserva;
import camycar_rentals.repository.ReservaRepository;
import camycar_rentals.service.ReservaService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ObtenerReservaPorIdServiceTest {

    @Inject
    ReservaService reservaService;

    @InjectMock
    ReservaRepository reservaRepository;

    @Test
    @DisplayName("prueba para ver si se obtiene una reserva por su id")
    void ObtenerReservaPorId() {
        // Given
        Reserva reserva = new Reserva(1, 1, null, 1, null, "direccion 1", LocalDate.parse("2024-04-22"), LocalDate.parse("2024-04-24"));
        ReservaDtoResponse reservaDtoResponse = new ReservaDtoResponse(1, null, null, "direccion 1", "2024-04-22", "2024-04-24");
        Mockito.when(reservaRepository.find(1)).thenReturn(reserva);

        // When
        ReservaDtoResponse reservaDtoResponseDevuelto = reservaService.obtenerReservaPorId(1);

        // Then
        assertEquals(reservaDtoResponse, reservaDtoResponseDevuelto);
    }
}