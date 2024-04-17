package base.controller.catches;

import base.controller.BaseController;
import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CatchEntityNotFoundException extends BaseController implements ExceptionMapper<EntityNotFoundException> {

    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public Response toResponse(final EntityNotFoundException e) {
        return createEntityNotFoundException(e);
    }
}
