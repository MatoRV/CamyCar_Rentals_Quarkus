package camycar_rentals.service;

import java.time.LocalDate;
import java.util.List;
import base.dto.maquina.MaquinaDtoRequest;
import base.dto.maquina.MaquinaDtoResponse;
import base.service.BaseService;
import camycar_rentals.domain.DiaReservado;
import camycar_rentals.domain.Maquina;
import camycar_rentals.domain.TipoMaquina;
import camycar_rentals.domain.enumerados.EstadoEnum;
import camycar_rentals.dto.converters.ConverterDtoToJpa;
import camycar_rentals.dto.converters.ConverterJpaToDto;
import camycar_rentals.repository.DiaReservadoRepository;
import camycar_rentals.repository.MaquinaRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@RequestScoped
public class MaquinaService extends BaseService<MaquinaRepository, Maquina, Integer> {

    @Inject
    ConverterJpaToDto converterJpaToDto;

    @Inject
    ConverterDtoToJpa converterDtoToJpa;

    @Inject
    TipoMaquinaService tipoMaquinaService;

    @Inject
    DiaReservadoRepository diaReservadoRepository;

    @Transactional
    public MaquinaDtoResponse crearMaquina(MaquinaDtoRequest maquinaDtoRequest) {

        TipoMaquina tipoMaquina = tipoMaquinaService.find(maquinaDtoRequest.getIdTipoMaquina());

        Maquina maquina = converterDtoToJpa.convertMaquina(maquinaDtoRequest);
        maquina.setTipoMaquina(tipoMaquina);

        maquina = create(maquina);
        return converterJpaToDto.convertMaquinaDtoResponse(maquina, List.of());
    }

    public List<MaquinaDtoResponse> obtenerMaquinas() {
        return converterJpaToDto.convertMaquinaDtoResponseList(findAll());
    }

    public MaquinaDtoResponse obtenerMaquinaPorId(Integer idMaquina) {
        Maquina maquina = find(idMaquina);

        List<LocalDate> dias = diaReservadoRepository.obtenerDiasReservadosPorIdMaquina(idMaquina).stream().map(DiaReservado::getDia).toList();

        return converterJpaToDto.convertMaquinaDtoResponse(maquina, dias);
    }

    @Transactional
    public MaquinaDtoResponse editarMaquinaPorId(Integer idMaquina, MaquinaDtoRequest maquinaDtoRequest) {
        TipoMaquina tipoMaquina = tipoMaquinaService.find(maquinaDtoRequest.getIdTipoMaquina());
        Maquina maquinaData = find(idMaquina);
        Maquina maquinaEdit = converterDtoToJpa.convertMaquina(maquinaData, maquinaDtoRequest);
        maquinaEdit.setTipoMaquina(tipoMaquina);

        List<LocalDate> dias = diaReservadoRepository.obtenerDiasReservadosPorIdMaquina(idMaquina).stream().map(DiaReservado::getDia).toList();

        return converterJpaToDto.convertMaquinaDtoResponse(edit(maquinaEdit), dias);
    }

    @Transactional
    public MaquinaDtoResponse eliminarMaquinaPorId(Integer idMaquina) {
        Maquina maquina = find(idMaquina);

        List<DiaReservado> diasReservados = diaReservadoRepository.obtenerDiasReservadosPorIdMaquina(idMaquina);

        List<LocalDate> dias = diasReservados.stream().map(DiaReservado::getDia).toList();

        for (int i = 0; i < diasReservados.size(); i++) {
            diaReservadoRepository.remove(diasReservados.get(i));
        }

        return converterJpaToDto.convertMaquinaDtoResponse(remove(maquina), dias);
    }

    public List<MaquinaDtoResponse> obtenerMaquinaPorTipoMaquinaYCapacidadCargaYFabricanteYEstado(Integer tipoMaquina, Integer capacidadCarga,
            String fabricante, EstadoEnum estadoEnum) {
        if (tipoMaquina != null) {
            tipoMaquinaService.find(tipoMaquina);
        }
        return converterJpaToDto.convertMaquinaDtoResponseList(
                repository.obtenerMaquinaPorTipoMaquinaYCapacidadCargaYFabricanteYEstado(tipoMaquina, capacidadCarga, fabricante, estadoEnum));
    }
}
