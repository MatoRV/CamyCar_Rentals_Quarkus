package camycar_rentals.repository;

import base.repository.AbstractRepository;
import camycar_rentals.domain.Maquina;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class MaquinaRepository extends AbstractRepository<Maquina, Integer> {

    @Inject
    private EntityManager em;

    public MaquinaRepository() {
        super(Maquina.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
