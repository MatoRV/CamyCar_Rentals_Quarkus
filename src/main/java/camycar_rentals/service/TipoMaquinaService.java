package camycar_rentals.service;


import base.service.BaseService;
import camycar_rentals.domain.TipoMaquina;
import camycar_rentals.dto.converters.ConverterDtoToJpa;
import camycar_rentals.dto.converters.ConverterJpaToDto;
import camycar_rentals.repository.TipoMaquinaRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class TipoMaquinaService extends BaseService<TipoMaquinaRepository, TipoMaquina, Integer> {

    @Inject
    ConverterDtoToJpa converterDtoToJpa;

    @Inject
    ConverterJpaToDto converterJpaToDto;
}
