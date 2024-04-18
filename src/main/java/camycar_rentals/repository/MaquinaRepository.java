package camycar_rentals.repository;

import java.util.List;
import base.repository.AbstractRepository;
import base.util.Casts;
import camycar_rentals.domain.Maquina;
import camycar_rentals.domain.TipoMaquina;
import camycar_rentals.domain.enumerados.EstadoEnum;
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
        return Casts.castList(query.getResultList(), Maquina.class);
    }

    public List<Maquina> obtenerMaquinasPorCapacidadCarga(Integer capacidadCarga) {
        Query query = em.createNamedQuery("Maquina.obtenerMaquinaPorCapacidadCarga", Maquina.class);
        query.setParameter("capacidadCarga", capacidadCarga);
        return Casts.castList(query.getResultList(), Maquina.class);
    }

    public List<Maquina> obtenerMaquinasPorFabricante(String fabricante) {
        Query query = em.createNamedQuery("Maquina.obtenerMaquinaPorFabricante", Maquina.class);
        query.setParameter("fabricante", fabricante);
        return Casts.castList(query.getResultList(), Maquina.class);
    }

    public List<Maquina> obtenerMaquinasPorEstado(EstadoEnum estadoEnum) {
        Query query = em.createNamedQuery("Maquina.obtenerMaquinaPorEstado", Maquina.class);
        query.setParameter("estado", estadoEnum);
        return Casts.castList(query.getResultList(), Maquina.class);
    }
}
