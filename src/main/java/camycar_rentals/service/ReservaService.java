package camycar_rentals.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import base.dto.reserva.ReservaDtoRequest;
import base.dto.reserva.ReservaDtoResponse;
import base.service.BaseService;
import camycar_rentals.domain.DiaReservado;
import camycar_rentals.domain.Maquina;
import camycar_rentals.domain.Reserva;
import camycar_rentals.domain.Usuario;
import camycar_rentals.domain.enumerados.EstadoEnum;
import camycar_rentals.dto.converters.ConverterDtoToJpa;
import camycar_rentals.dto.converters.ConverterJpaToDto;
import camycar_rentals.repository.DiaReservadoRepository;
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
    DiaReservadoRepository diaReservadoRepository;

    @Inject
    PdfGeneratorService pdfGeneratorService;

    @Transactional
    public ReservaDtoResponse crearReserva(ReservaDtoRequest reservaDtoRequest) throws IOException {
        Maquina maquina = maquinaService.find(reservaDtoRequest.getIdMaquina());
        Usuario usuario = usuarioService.find(reservaDtoRequest.getIdUsuario());
        maquina.setEstado(EstadoEnum.ALQUILADO);
        maquinaRepository.edit(maquina);
        Reserva reserva = converterDtoToJpa.convertReserva(reservaDtoRequest);
        reserva.setMaquina(maquina);
        reserva.setUsuario(usuario);


        long dif = ChronoUnit.DAYS.between(reserva.getFechaInicio(), reserva.getFechaFin());
        List<LocalDate> dias = new ArrayList<>();

        for (int i = 0; i <= dif; i++) {
            diaReservadoRepository.create(new DiaReservado(null, maquina, reserva.getFechaInicio().plusDays(i)));
            dias.add(reserva.getFechaInicio().plusDays(i));
        }
        
        reserva = create(reserva);
        pdfGeneratorService.generarPdf(reserva);
        return converterJpaToDto.convertReservaDtoResponse(reserva, dias);
    }

    public List<ReservaDtoResponse> obtenerReservas() {
        return converterJpaToDto.convertReservaDtoResponseList(findAll());
    }

    public ReservaDtoResponse obtenerReservaPorId(Integer idReserva) {
        Reserva reserva = find(idReserva);

        List<LocalDate> dias = diaReservadoRepository.obtenerDiasReservadosPorIdMaquina(reserva.getIdMaquina()).stream().map(DiaReservado::getDia).toList();

        return converterJpaToDto.convertReservaDtoResponse(reserva, dias);
    }

    @Transactional
    public ReservaDtoResponse eliminarReserva(Integer idReserva) {
        Reserva reserva = find(idReserva);
        return converterJpaToDto.convertReservaDtoResponse(remove(reserva), List.of());
    }

    //    @Transactional
    //    public ReservaDtoResponse editarReserva(Integer idReserva, ReservaDtoRequest reservaDtoRequest) {
    //        Reserva reserva = find(idReserva);
    //        Reserva reservaEdit = converterDtoToJpa.convertReserva(reserva, reservaDtoRequest);
    //        return converterJpaToDto.convertReservaDtoResponse(edit(reservaEdit));
    //    }
}
