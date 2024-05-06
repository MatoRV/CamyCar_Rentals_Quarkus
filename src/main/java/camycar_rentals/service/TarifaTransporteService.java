package camycar_rentals.service;

import java.util.List;
import base.dto.tarifatransporte.TarifaTransporteDtoResponse;
import base.service.BaseService;
import camycar_rentals.domain.Localidad;
import camycar_rentals.domain.TarifaTransporte;
import camycar_rentals.dto.converters.ConverterJpaToDto;
import camycar_rentals.repository.LocalidadRepository;
import camycar_rentals.repository.TarifaTransporteRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class TarifaTransporteService extends BaseService<TarifaTransporteRepository, TarifaTransporte, Integer> {


    @Inject
    ConverterJpaToDto converterJpaToDto;

    @Inject
    LocalidadRepository localidadRepository;

    public List<TarifaTransporteDtoResponse> obtenerTarifasTransporte() {
        return converterJpaToDto.convertTarifaTransporteDtoResponseList(findAll());
    }

    public TarifaTransporteDtoResponse obtenerTarifaTransportePorLocalidad(String l) {
        Localidad localidad = localidadRepository.obtenerLocalidadPorNombre(l.toUpperCase());
        TarifaTransporte tarifaTransporte = repository.obtenerTarifaTransportePorLocalidad(localidad.getIdLocalidad());
        return converterJpaToDto.convertTarifaTransporteDtoResponse(tarifaTransporte);
    }
}
