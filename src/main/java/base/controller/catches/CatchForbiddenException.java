package base.controller.catches;

import base.controller.BaseController;
import io.quarkus.security.ForbiddenException;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CatchForbiddenException extends BaseController implements ExceptionMapper<ForbiddenException> {

    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public Response toResponse(final ForbiddenException e) {
        return createEntityForbiddenException(e);
    }
}
