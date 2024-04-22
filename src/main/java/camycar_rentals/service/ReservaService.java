package camycar_rentals.service;

import java.util.List;
import base.dto.reserva.ReservaDtoRequest;
import base.dto.reserva.ReservaDtoResponse;
import base.service.BaseService;
import camycar_rentals.domain.Cliente;
import camycar_rentals.domain.Maquina;
import camycar_rentals.domain.Reserva;
import camycar_rentals.domain.enumerados.EstadoEnum;
import camycar_rentals.dto.converters.ConverterDtoToJpa;
import camycar_rentals.dto.converters.ConverterJpaToDto;
import camycar_rentals.repository.ClienteRepository;
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
    MaquinaRepository maquinaRepository;

    @Inject
    ClienteRepository clienteRepository;

    @Transactional
    public ReservaDtoResponse crearReserva(ReservaDtoRequest reservaDtoRequest) {
        Maquina maquina = maquinaRepository.find(reservaDtoRequest.getIdMaquina());
        Cliente cliente = clienteRepository.find(reservaDtoRequest.getIdCliente());
        Reserva reserva = converterDtoToJpa.convertReserva(reservaDtoRequest);
        maquina.setEstado(EstadoEnum.ALQUILADO);
        maquinaRepository.edit(maquina);
        reserva.setMaquina(maquina);
        reserva.setCliente(cliente);
        return converterJpaToDto.convertReservaDtoResponse(create(reserva));
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
