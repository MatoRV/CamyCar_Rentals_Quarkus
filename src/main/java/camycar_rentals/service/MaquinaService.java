package camycar_rentals.service;

import java.time.LocalDate;
import java.util.ArrayList;
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

    @Inject
    DiaReservadoService diaReservadoService;

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

        List<LocalDate> dias = diaReservadoService.obtenerDiasPorIdMaquina(idMaquina);
        maquina.setEstado(obtenerEstadoPorDias(dias));

        return converterJpaToDto.convertMaquinaDtoResponse(maquina, dias);
    }

    @Transactional
    public MaquinaDtoResponse editarMaquinaPorId(Integer idMaquina, MaquinaDtoRequest maquinaDtoRequest) {
        TipoMaquina tipoMaquina = tipoMaquinaService.find(maquinaDtoRequest.getIdTipoMaquina());
        Maquina maquinaData = find(idMaquina);
        Maquina maquinaEdit = converterDtoToJpa.convertMaquina(maquinaData, maquinaDtoRequest);
        maquinaEdit.setTipoMaquina(tipoMaquina);

        List<LocalDate> dias = diaReservadoRepository.obtenerDiasReservadosPorIdMaquina(idMaquina).stream().map(DiaReservado::getDia).toList();
        if (maquinaEdit.getEstado().equals(EstadoEnum.MANTENIMIENTO) || maquinaEdit.getEstado().equals(EstadoEnum.NO_DISPONIBLE)) {
            maquinaEdit.setEstado(obtenerEstadoPorDias(dias));
        }

        return converterJpaToDto.convertMaquinaDtoResponse(edit(maquinaEdit), dias);
    }

    @Transactional
    public MaquinaDtoResponse eliminarMaquinaPorId(Integer idMaquina) {
        Maquina maquina = find(idMaquina);

        List<DiaReservado> diasReservados = diaReservadoRepository.obtenerDiasReservadosPorIdMaquina(idMaquina);

        List<LocalDate> dias = diaReservadoService.eliminarDiasReservados(diasReservados);

        return converterJpaToDto.convertMaquinaDtoResponse(remove(maquina), dias);
    }

    @Transactional
    public List<MaquinaDtoResponse> obtenerMaquinaPorTipoMaquinaYCapacidadCargaYFabricanteYEstado(Integer tipoMaquina, Integer capacidadCarga, String fabricante, EstadoEnum estadoEnum) {
        if (tipoMaquina != null) {
            tipoMaquinaService.find(tipoMaquina);
        }

        List<Maquina> maquinas = repository.obtenerMaquinaPorTipoMaquinaYCapacidadCargaYFabricanteYEstado(tipoMaquina, capacidadCarga, fabricante, estadoEnum);
        List<MaquinaDtoResponse> responseList = new ArrayList<>();
        for (Maquina m : maquinas) {
            List<LocalDate> dias = diaReservadoService.obtenerDiasPorIdMaquina(m.getIdMaquina());
            m.setEstado(obtenerEstadoPorDias(dias));
            edit(m);
            responseList.add(converterJpaToDto.convertMaquinaDtoResponse(m, dias));
        }

        return responseList;
    }

    public EstadoEnum obtenerEstadoPorDias(List<LocalDate> dias) {
        EstadoEnum estado = EstadoEnum.DISPONIBLE;
        for (LocalDate d : dias) {
            if (d.equals(LocalDate.now())) {
                estado = EstadoEnum.ALQUILADO;
            }
        }
        return estado;
    }
}
