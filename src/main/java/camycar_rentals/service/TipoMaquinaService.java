package camycar_rentals.service;


import java.util.List;
import base.dto.tipomaquina.TipoMaquinaDtoRequest;
import base.dto.tipomaquina.TipoMaquinaDtoResponse;
import base.service.BaseService;
import camycar_rentals.domain.TipoMaquina;
import camycar_rentals.dto.converters.ConverterDtoToJpa;
import camycar_rentals.dto.converters.ConverterJpaToDto;
import camycar_rentals.repository.TipoMaquinaRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@RequestScoped
public class TipoMaquinaService extends BaseService<TipoMaquinaRepository, TipoMaquina, Integer> {

    @Inject
    ConverterDtoToJpa converterDtoToJpa;

    @Inject
    ConverterJpaToDto converterJpaToDto;

    @Transactional
    public TipoMaquinaDtoResponse crearTipoMaquina(TipoMaquinaDtoRequest tipoMaquinaDtoRequest) {

        TipoMaquina tipoMaquina = converterDtoToJpa.convertTipoMaquina(tipoMaquinaDtoRequest);

        tipoMaquina = create(tipoMaquina);
        return converterJpaToDto.convertTipoMaquinaDtoResponse(tipoMaquina);
    }

    public List<TipoMaquinaDtoResponse> obtenerTiposMaquina() {
        return converterJpaToDto.convertTipoMaquinaDtoResponseList(findAll());
    }

    public TipoMaquinaDtoResponse obtenerTipoMaquinaPorId(Integer idTipoMaquina) {
        TipoMaquina tipoMaquina = find(idTipoMaquina);
        return converterJpaToDto.convertTipoMaquinaDtoResponse(tipoMaquina);
    }

    @Transactional
    public TipoMaquinaDtoResponse eliminarTipoMaquinaPorId(Integer idTipoMaquina) {
        TipoMaquina tipoMaquina = find(idTipoMaquina);
        return converterJpaToDto.convertTipoMaquinaDtoResponse(remove(tipoMaquina));
    }

    @Transactional
    public TipoMaquinaDtoResponse editarTipoMaquinaPorId(Integer idTipoMaquina, TipoMaquinaDtoRequest tipoMaquinaDtoRequest) {
        TipoMaquina tipoMaquinaData = find(idTipoMaquina);
        TipoMaquina tipoMaquinaEdit = converterDtoToJpa.convertTipoMaquina(tipoMaquinaData, tipoMaquinaDtoRequest);
        return converterJpaToDto.convertTipoMaquinaDtoResponse(edit(tipoMaquinaEdit));
    }
}
