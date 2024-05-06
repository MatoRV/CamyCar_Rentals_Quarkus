package camycar_rentals.service;

import base.service.BaseService;
import camycar_rentals.domain.Localidad;
import camycar_rentals.repository.LocalidadRepository;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class LocalidadService extends BaseService<LocalidadRepository, Localidad,Integer> {


}
