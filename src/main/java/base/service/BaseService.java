package base.service;

import base.repository.AbstractRepository;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

public class BaseService<T extends AbstractRepository<E, P>, E, P> {

    @Inject
    protected T repository;

    public List<E> findAll() {
        return repository.findAll(new ArrayList<>());
    }

    public E find(P id) {
        return repository.find(id);
    }

    public Boolean exists(P id) {
        return repository.exists(id);
    }

    @Transactional
    public E create(E entity) {
        return repository.create(entity);
    }

    @Transactional(dontRollbackOn = EntityNotFoundException.class)
    public E edit(E entity) {
        return repository.edit(entity);
    }

    @Transactional(dontRollbackOn = EntityNotFoundException.class)
    public E remove(E entity) {
        return repository.remove(entity);
    }

    @Transactional(dontRollbackOn = EntityNotFoundException.class)
    public E removeById(P id) {
        return repository.removeById(id);
    }
}
