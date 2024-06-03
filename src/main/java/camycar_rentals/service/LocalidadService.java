package camycar_rentals.service;

import java.util.ArrayList;
import java.util.List;
import base.dto.localidad.LocalidadDtoResponse;
import base.service.BaseService;
import camycar_rentals.domain.Localidad;
import camycar_rentals.dto.converters.ConverterJpaToDto;
import camycar_rentals.repository.LocalidadRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class LocalidadService extends BaseService<LocalidadRepository, Localidad, Integer> {

    @Inject
    ConverterJpaToDto converterJpaToDto;

    public List<LocalidadDtoResponse> obtenerLocalidades() {
        List<Localidad> localidades = findAll();
        List<String> localidadesList = new ArrayList<>();
        for (Localidad localidad : localidades) {
            localidadesList.add(localidad.getNombre());
        }
        return converterJpaToDto.convertLocalidadDtoResponseList(localidadesList);
    }

}
