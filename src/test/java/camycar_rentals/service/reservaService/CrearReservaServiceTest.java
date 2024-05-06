package camycar_rentals.service.reservaService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import base.dto.maquina.MaquinaDtoResponse;
import base.dto.reserva.ReservaDtoRequest;
import base.dto.reserva.ReservaDtoResponse;
import camycar_rentals.domain.Localidad;
import camycar_rentals.domain.Maquina;
import camycar_rentals.domain.Reserva;
import camycar_rentals.domain.TipoMaquina;
import camycar_rentals.domain.Usuario;
import camycar_rentals.domain.enumerados.EstadoEnum;
import camycar_rentals.repository.LocalidadRepository;
import camycar_rentals.repository.MaquinaRepository;
import camycar_rentals.repository.ReservaRepository;
import camycar_rentals.service.MaquinaService;
import camycar_rentals.service.ReservaService;
import camycar_rentals.service.UsuarioService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class CrearReservaServiceTest {

    @Inject
    ReservaService reservaService;

    @InjectMock
    ReservaRepository reservaRepository;

    @InjectMock
    UsuarioService usuarioService;

    @InjectMock
    MaquinaService maquinaService;

    @InjectMock
    MaquinaRepository maquinaRepository;

    @InjectMock
    LocalidadRepository localidadRepository;

    @Test
    @DisplayName("prueba para ver si se crea una reserva")
    void crearReservaOk() throws IOException {
        // Given
        ReservaDtoRequest reservaDtoRequest = new ReservaDtoRequest(1, 1, "direccion 1", "2024-04-22", "2024-04-24");
        TipoMaquina tipoMaquina = new TipoMaquina(1, "Torito");
        Localidad localidad = new Localidad(1, "Albacete");
        Maquina maquina = new Maquina(1, "F1", "M1", 1500, EstadoEnum.DISPONIBLE, tipoMaquina, 4500);
        Maquina maquinaEdit = new Maquina(1, "F1", "M1", 1500, EstadoEnum.ALQUILADO, tipoMaquina, 4500);
        Usuario usuario = new Usuario(1, "Cliente 1", "", "11111111C", "apellido1", "apellido2", "prueba@example.com");
        Reserva reservaEsperada = new Reserva(1, 1, maquina, 1, usuario, "direccion 1", LocalDate.parse("2024-04-22"), LocalDate.parse("2024-04-24"));
        MaquinaDtoResponse maquinaDtoResponse = new MaquinaDtoResponse(1, "F1", "M1", 1500, EstadoEnum.ALQUILADO, "Torito", 4500);
        ReservaDtoResponse reservaDtoResponse = new ReservaDtoResponse(1, maquinaDtoResponse, "Cliente 1", "direccion 1", "2024-04-22", "2024-04-24");
        when(maquinaService.find(1)).thenReturn(maquina);
        when(localidadRepository.obtenerLocalidadPorNombre(any())).thenReturn(List.of(localidad));
        when(usuarioService.find(1)).thenReturn(usuario);
        when(maquinaRepository.edit(maquina)).thenReturn(maquinaEdit);
        when(reservaRepository.create(any())).thenReturn(reservaEsperada);

        // When
        ReservaDtoResponse reservaDtoResponseDevuelto = reservaService.crearReserva(reservaDtoRequest);

        // Then
        verify(maquinaService, times(1)).find(1);
        verify(usuarioService, times(1)).find(1);
        verify(maquinaRepository, times(1)).edit(maquina);
        assertEquals(reservaDtoResponse, reservaDtoResponseDevuelto);
    }
}
