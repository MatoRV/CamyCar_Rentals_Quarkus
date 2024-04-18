package camycar_rentals.repository;

import java.util.List;
import base.repository.AbstractRepository;
import camycar_rentals.domain.Maquina;
import camycar_rentals.domain.enumerados.EstadoEnum;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

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

    public List<Maquina> obtenerMaquinaPorTipoMaquinaYCapacidadCargaYFabricanteYEstado(Integer tipoMaquina, Integer capacidadCarga, String fabricante,
            EstadoEnum estadoEnum) {
        TypedQuery<Maquina> query = em.createNamedQuery("Maquina.obtenerMaquinaPorTipoMaquinaYCapacidadCargaYFabricanteYEstado", Maquina.class);
        query.setParameter("tipoMaquina", tipoMaquina);
        query.setParameter("capacidadCarga", capacidadCarga);
        query.setParameter("fabricante", fabricante);
        query.setParameter("estado", estadoEnum);
        return query.getResultList();
    }
}
