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
        Reserva reservaEsperada = new Reserva(1, 1, null, 1, null, "direccion 1", LocalDate.parse("2024-04-22"), LocalDate.parse("2024-04-24"));
        ReservaDtoResponse reservaDtoResponse = new ReservaDtoResponse(1, null, null, "direccion 1");
        when(reservaRepository.find(1)).thenReturn(reservaEsperada);
        when(reservaRepository.remove(reservaEsperada)).thenReturn(reservaEsperada);
        when(diaReservadoRepository.obtenerDiasReservadosPorIdMaquina(1)).thenReturn(List.of());

        // When
        ReservaDtoResponse reservaDtoResponseDevuelta = reservaService.eliminarReserva(1);

        // Then
        assertEquals(reservaDtoResponse, reservaDtoResponseDevuelta);
    }
}
