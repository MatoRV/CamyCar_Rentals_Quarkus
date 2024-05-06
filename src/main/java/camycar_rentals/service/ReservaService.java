package camycar_rentals.service;

import java.io.IOException;
import java.util.List;
import base.dto.reserva.ReservaDtoRequest;
import base.dto.reserva.ReservaDtoResponse;
import base.service.BaseService;
import camycar_rentals.domain.Maquina;
import camycar_rentals.domain.Reserva;
import camycar_rentals.domain.Usuario;
import camycar_rentals.domain.enumerados.EstadoEnum;
import camycar_rentals.dto.converters.ConverterDtoToJpa;
import camycar_rentals.dto.converters.ConverterJpaToDto;
import camycar_rentals.repository.MaquinaRepository;
import camycar_rentals.repository.ReservaRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@RequestScoped
public class ReservaService extends BaseService<ReservaRepository, Reserva, Integer> {

    @Inject
    ConverterJpaToDto converterJpaToDto;

    @Inject
    ConverterDtoToJpa converterDtoToJpa;

    @Inject
    MaquinaService maquinaService;

    @Inject
    MaquinaRepository maquinaRepository;

    @Inject
    UsuarioService usuarioService;

    @Inject
    PdfGeneratorService pdfGeneratorService;

    @Transactional
    public ReservaDtoResponse crearReserva(ReservaDtoRequest reservaDtoRequest) throws IOException {
        Maquina maquina = maquinaService.find(reservaDtoRequest.getIdMaquina());
        Usuario usuario = usuarioService.find(reservaDtoRequest.getIdCliente());
        maquina.setEstado(EstadoEnum.ALQUILADO);
        maquinaRepository.edit(maquina);
        Reserva reserva = converterDtoToJpa.convertReserva(reservaDtoRequest);
        reserva.setMaquina(maquina);
        reserva.setUsuario(usuario);
        reserva = create(reserva);
        pdfGeneratorService.generarPdf(reserva);
        return converterJpaToDto.convertReservaDtoResponse(reserva);
    }

    public List<ReservaDtoResponse> obtenerReservas() {
        return converterJpaToDto.convertReservaDtoResponseList(findAll());
    }

    public ReservaDtoResponse obtenerReservaPorId(Integer idReserva) {
        Reserva reserva = find(idReserva);
        return converterJpaToDto.convertReservaDtoResponse(reserva);
    }

    @Transactional
    public ReservaDtoResponse eliminarReserva(Integer idReserva) {
        Reserva reserva = find(idReserva);
        return converterJpaToDto.convertReservaDtoResponse(remove(reserva));
    }

    @Transactional
    public ReservaDtoResponse editarReserva(Integer idReserva, ReservaDtoRequest reservaDtoRequest) {
        Reserva reserva = find(idReserva);
        Reserva reservaEdit = converterDtoToJpa.convertReserva(reserva, reservaDtoRequest);
        return converterJpaToDto.convertReservaDtoResponse(edit(reservaEdit));
    }
}
