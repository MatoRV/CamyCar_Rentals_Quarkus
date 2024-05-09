package camycar_rentals.repository;

import base.constant.errores.ErroresGeneral;
import base.repository.AbstractRepository;
import base.util.Casts;
import camycar_rentals.domain.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import io.quarkus.security.ForbiddenException;

@ApplicationScoped
public class UsuarioRepository extends AbstractRepository<Usuario, Integer> {

    @Inject
    private EntityManager em;

    public UsuarioRepository() {
        super(Usuario.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Boolean obtenerPorDni(Usuario usuario) {
        Query query = em.createNamedQuery("Usuario.obtenerPorDni");
        query.setParameter("dniUsuario", usuario.getDniUsuario());
        try {
            query.getSingleResult();
        } catch (NoResultException e) {
            return false;
        }
        return true;
    }

    public Usuario existeUsuarioCorreoYContrasena(String correo, String contrasena) {
        Query query = em.createNamedQuery("Usuario.existeUsuarioCorreoYContrasena");
        query.setParameter("correo", correo);
        query.setParameter("contrasena", contrasena);
        try {
            return Casts.castObject(query.getSingleResult(), Usuario.class);
        } catch (NoResultException n) {
            throw new ForbiddenException(ErroresGeneral.GEN_0003);
        }
    }
}
