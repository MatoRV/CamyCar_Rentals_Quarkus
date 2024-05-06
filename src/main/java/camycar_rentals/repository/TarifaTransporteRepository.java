package camycar_rentals.repository;

import base.repository.AbstractRepository;
import camycar_rentals.domain.TarifaTransporte;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@ApplicationScoped
public class TarifaTransporteRepository extends AbstractRepository<TarifaTransporte, Integer> {

    @Inject
    private EntityManager em;

    public TarifaTransporteRepository() {
        super(TarifaTransporte.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TarifaTransporte obtenerTarifaTransportePorLocalidad(Integer idLocalidad) {
        TypedQuery<TarifaTransporte> query = em.createNamedQuery("TarifaTransporte.obtenerTarifaTransportePorLocalidad", TarifaTransporte.class);
        query.setParameter("idLocalidad",idLocalidad);
        return query.getSingleResult();
    }
}
