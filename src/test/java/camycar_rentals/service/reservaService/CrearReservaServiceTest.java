package camycar_rentals.service.reservaService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import base.dto.cliente.ClienteDtoResponse;
import base.dto.maquina.MaquinaDtoResponse;
import base.dto.reserva.ReservaDtoRequest;
import base.dto.reserva.ReservaDtoResponse;
import camycar_rentals.domain.Cliente;
import camycar_rentals.domain.Maquina;
import camycar_rentals.domain.Reserva;
import camycar_rentals.domain.TipoMaquina;
import camycar_rentals.domain.enumerados.EstadoEnum;
import camycar_rentals.repository.ClienteRepository;
import camycar_rentals.repository.MaquinaRepository;
import camycar_rentals.repository.ReservaRepository;
import camycar_rentals.service.ReservaService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class CrearReservaServiceTest {

    @Inject
    ReservaService reservaService;

    @InjectMock
    ReservaRepository reservaRepository;

    @InjectMock
    ClienteRepository clienteRepository;

    @InjectMock
    MaquinaRepository maquinaRepository;

    @Test
    @DisplayName("prueba para ver si se crea una reserva")
    void crearReservaOk() {
        // Given
        ReservaDtoRequest reservaDtoRequest = new ReservaDtoRequest(1,1,"direccion 1","2024-04-22","2024-04-24");
        TipoMaquina tipoMaquina = new TipoMaquina(1,"Torito");
        Maquina maquina = new Maquina(1,"F1","M1",1500, EstadoEnum.DISPONIBLE,tipoMaquina);
        Maquina maquinaEdit = new Maquina(1,"F1","M1",1500, EstadoEnum.ALQUILADO,tipoMaquina);
        Cliente cliente = new Cliente(1,"Cliente 1","cliente_1","","11111111C");
        Reserva reservaEsperada = new Reserva(1,1,maquina,1,cliente,"direccion 1", LocalDate.parse("2024-04-22"),LocalDate.parse("2024-04-24"));
        Reserva reservaDada = new Reserva(null,1,maquina,1,cliente,"direccion 1", LocalDate.parse("2024-04-22"),LocalDate.parse("2024-04-24"));
        MaquinaDtoResponse maquinaDtoResponse = new MaquinaDtoResponse(1,"F1","M1",1500,EstadoEnum.ALQUILADO,"Torito");
        ClienteDtoResponse clienteDtoResponse = new ClienteDtoResponse(1,"Cliente 1","cliente_1","11111111C");
        ReservaDtoResponse reservaDtoResponse = new ReservaDtoResponse(1,maquinaDtoResponse,clienteDtoResponse,"direccion 1","2024-04-22","2024-04-24");
        Mockito.when(maquinaRepository.find(1)).thenReturn(maquina);
        Mockito.when(clienteRepository.find(1)).thenReturn(cliente);
        Mockito.when(maquinaRepository.edit(maquina)).thenReturn(maquinaEdit);
        Mockito.when(reservaRepository.create(reservaDada)).thenReturn(reservaEsperada);

        // When
        ReservaDtoResponse reservaDtoResponseDevuelto = reservaService.crearReserva(reservaDtoRequest);

        // Then
        assertEquals(reservaDtoResponse, reservaDtoResponseDevuelto);
    }
}
