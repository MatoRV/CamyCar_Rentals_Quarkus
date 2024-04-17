package camycar_rentals.repository;

import java.util.List;
import base.repository.AbstractRepository;
import camycar_rentals.domain.Maquina;
import camycar_rentals.domain.TipoMaquina;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

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

    public List<Maquina> obtenerMaquinasPorTipoMaquina(TipoMaquina tipoMaquina) {
        Query query = em.createNamedQuery("Maquina.obtenerMaquinaPorTipoMaquina", Maquina.class);
        query.setParameter("tipoMaquina", tipoMaquina);
        return query.getResultList();
    }
}
