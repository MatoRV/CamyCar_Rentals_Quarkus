package camycar_rentals.repository;

import java.util.List;
import base.repository.AbstractRepository;
import camycar_rentals.domain.DiaReservado;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@ApplicationScoped
public class DiaReservadoRepository extends AbstractRepository<DiaReservado, Integer> {

    @Inject
    private EntityManager em;

    public DiaReservadoRepository() {
        super(DiaReservado.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<DiaReservado> obtenerDiasReservadosPorIdMaquina(Integer idMaquina) {
        TypedQuery<DiaReservado> query = em.createNamedQuery("DiaReservado.obtenerDiasReservadosPorIdMaquina", DiaReservado.class);
        query.setParameter("maquina", idMaquina);
        return query.getResultList();
    }
}
