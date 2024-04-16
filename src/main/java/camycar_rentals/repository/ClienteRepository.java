package camycar_rentals.repository;

import base.repository.AbstractRepository;
import camycar_rentals.domain.Cliente;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

@ApplicationScoped
public class ClienteRepository extends AbstractRepository<Cliente, Integer> {

    @Inject
    private EntityManager em;

    public ClienteRepository() {
        super(Cliente.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Boolean obtenerPorDni(Cliente cliente) {
        Query query = em.createNamedQuery("Cliente.obtenerPorDni");
        query.setParameter("dniCliente", cliente.getDniCliente());
        try {
            query.getSingleResult();
        } catch (NoResultException e) {
            return false;
        }
        return true;
    }
}
