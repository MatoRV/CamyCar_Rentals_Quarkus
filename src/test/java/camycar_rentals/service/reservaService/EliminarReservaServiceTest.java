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
public class EliminarReservaServiceTest {

    @Inject
    ReservaService reservaService;

    @InjectMock
    ReservaRepository reservaRepository;

    @Test
    @DisplayName("Prueba para probar que se elimina la reserva")
    void eliminarReservaOk() {
        // Given
        Reserva reservaEsperada = new Reserva(1, null, null, "direccion 1", LocalDate.parse("2024-04-22"), LocalDate.parse("2024-04-24"));
        ReservaDtoResponse reservaDtoResponse = new ReservaDtoResponse(1, null, null, "direccion 1", "2024-04-22", "2024-04-24");
        Mockito.when(reservaRepository.find(1)).thenReturn(reservaEsperada);
        Mockito.when(reservaRepository.remove(reservaEsperada)).thenReturn(reservaEsperada);

        // When
        ReservaDtoResponse reservaDtoResponseDevuelta = reservaService.eliminarReserva(1);

        // Then
        assertEquals(reservaDtoResponse, reservaDtoResponseDevuelta);
    }
}
