package camycar_rentals.repository;

import base.repository.AbstractRepository;
import camycar_rentals.domain.TipoMaquina;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class TipoMaquinaRepository extends AbstractRepository<TipoMaquina, Integer> {

    @Inject
    private EntityManager em;

    public TipoMaquinaRepository() {
        super(TipoMaquina.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
