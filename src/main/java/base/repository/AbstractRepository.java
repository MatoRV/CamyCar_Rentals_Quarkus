package base.repository;

import base.util.Casts;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Root;
import org.jboss.logging.Logger;

import java.util.List;

public abstract class AbstractRepository<E, P> {
    
    public static final String NO_ENCONTRADO = "No encontrado";
    private final Class<E> entityClass;

    @Inject
    Logger log;

    protected AbstractRepository(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void clear() {
        getEntityManager().clear();
    }

    public E merge(E entity) {
        getEntityManager().merge(entity);
        return entity;
    }

    public void flush() {
        getEntityManager().flush();
    }

    public E create(E entity) {
        getEntityManager().persist(entity);
        return entity;
    }

    public Object getIdentifier(E entity) {
        return getEntityManager().getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity);
    }

    public E edit(E entity) {
        if (getIdentifier(entity) == null || getEntityManager().find(entityClass, getIdentifier(entity)) == null) {
           throw new EntityNotFoundException(NO_ENCONTRADO + entity.getClass().getSimpleName());
        } else {
            return getEntityManager().merge(entity);
        }
    }

    public E remove(E entity) {
        if (getIdentifier(entity) == null || getEntityManager().find(entityClass, getIdentifier(entity)) == null) {
            throw new EntityNotFoundException(NO_ENCONTRADO + entity.getClass().getSimpleName());
        } else {
            getEntityManager().remove(getEntityManager().merge(entity));
            return entity;
        }
    }

    public E find(P id) {
        E e = getEntityManager().find(entityClass, id);
        if (e == null) {
            throw new EntityNotFoundException(NO_ENCONTRADO + entityClass.getSimpleName() + "con id " + id);
        } else {
            return e;
        }
    }

    public E removeById(P id) {
        E entity = find(id);
        remove(entity);
        return entity;
    }

    public Boolean exists(P id) {
        return getEntityManager().find(entityClass, id) != null;
    }

    public List<E> findAll(List<Order> orderList) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();
        Root<?> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(root);
        criteriaQuery.orderBy(orderList);
        return Casts.castList(getEntityManager().createQuery(criteriaQuery).getResultList(), entityClass);
    }


}
