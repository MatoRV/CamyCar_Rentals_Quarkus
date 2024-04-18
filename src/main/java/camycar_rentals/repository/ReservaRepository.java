package camycar_rentals.repository;

import base.repository.AbstractRepository;
import camycar_rentals.domain.Reserva;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class ReservaRepository extends AbstractRepository<Reserva, Integer> {

    @Inject
    private EntityManager em;

    public ReservaRepository() {
        super(Reserva.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
