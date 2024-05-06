package camycar_rentals.repository;

import java.util.List;
import base.repository.AbstractRepository;
import camycar_rentals.domain.Localidad;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@ApplicationScoped
public class LocalidadRepository extends AbstractRepository<Localidad, Integer> {

    @Inject
    private EntityManager em;

    public LocalidadRepository() {
        super(Localidad.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Localidad> obtenerLocalidadPorNombre(String nombre) {
        TypedQuery<Localidad> query = em.createNamedQuery("Localidad.obtenerLocalidadPorNombre", Localidad.class);
        query.setParameter("nombre", nombre);
        return query.getResultList();
    }
}
