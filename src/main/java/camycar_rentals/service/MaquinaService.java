package camycar_rentals.service;


import java.util.List;
import base.dto.maquina.MaquinaDtoRequest;
import base.dto.maquina.MaquinaDtoResponse;
import base.service.BaseService;
import camycar_rentals.domain.Maquina;
import camycar_rentals.domain.TipoMaquina;
import camycar_rentals.dto.converters.ConverterDtoToJpa;
import camycar_rentals.dto.converters.ConverterJpaToDto;
import camycar_rentals.repository.MaquinaRepository;
import camycar_rentals.repository.TipoMaquinaRepository;
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
    TipoMaquinaRepository tipoMaquinaRepository;

    @Transactional
    public MaquinaDtoResponse crearMaquina(MaquinaDtoRequest maquinaDtoRequest) {

        TipoMaquina tipoMaquina = tipoMaquinaRepository.find(maquinaDtoRequest.getIdTipoMaquina());

        Maquina maquina = converterDtoToJpa.convertMaquina(maquinaDtoRequest);
        maquina.setTipoMaquina(tipoMaquina);

        return converterJpaToDto.convertMaquinaDtoResponse(create(maquina));
    }

    public List<MaquinaDtoResponse> obtenerMaquinas() {
        return converterJpaToDto.convertMaquinaDtoResponseList(findAll());
    }

    public MaquinaDtoResponse obtenerMaquinaPorId(Integer idMaquina) {
        Maquina maquina = find(idMaquina);
        return converterJpaToDto.convertMaquinaDtoResponse(maquina);
    }

    @Transactional
    public MaquinaDtoResponse editarMaquinaPorId(Integer idMaquina, MaquinaDtoRequest maquinaDtoRequest) {
        TipoMaquina tipoMaquina = tipoMaquinaRepository.find(maquinaDtoRequest.getIdTipoMaquina());
        Maquina maquinaData = find(idMaquina);
        Maquina maquinaEdit = converterDtoToJpa.convertMaquina(maquinaData, maquinaDtoRequest);
        maquinaEdit.setTipoMaquina(tipoMaquina);
        return converterJpaToDto.convertMaquinaDtoResponse(edit(maquinaEdit));
    }

    @Transactional
    public MaquinaDtoResponse eliminarMaquinaPorId(Integer idMaquina) {
        Maquina maquina = find(idMaquina);
        return converterJpaToDto.convertMaquinaDtoResponse(remove(maquina));
    }
}
