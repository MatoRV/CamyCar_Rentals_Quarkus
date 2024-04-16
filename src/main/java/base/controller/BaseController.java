package base.controller;


import base.constant.errores.ErroresGeneral;
import io.quarkus.security.ForbiddenException;
import io.quarkus.security.UnauthorizedException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.NotSupportedException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.UriInfo;
import org.eclipse.microprofile.config.inject.ConfigProperty;

public abstract class BaseController {

    public static final String MESSAGE = "{\"message\":\"";
    private static final String CREATED = ".created";
    private static final String UPDATED = ".updated";
    private static final String DELETED = ".deleted";
    protected String entityName;

    @Context
    UriInfo uriInfo;

    @ConfigProperty(name = "aplicacion.nombre")
    String project;

    public ResponseBuilder createAlert(final ResponseBuilder builder, final String message, final String param) {
        builder.header("X-app-alert", message);
        builder.header("X-app-params", param);
        return builder;
    }

    public Response createEntityNotFoundException(EntityNotFoundException ex) {
        return createEntityNotFoundException(ex.getMessage());
    }

    public Response createEntityNotFoundException(String mensaje) {
        return Response.status(Response.Status.NOT_FOUND).entity(MESSAGE + mensaje + "\"}").type(MediaType.APPLICATION_JSON).build();
    }

    public Response createEntityUnauthorizedException(UnauthorizedException ex) {
        return createEntityUnauthorizedException(ex.getMessage());
    }

    public Response createEntityUnauthorizedException(String mensaje) {
        return Response.status(Response.Status.UNAUTHORIZED).entity(MESSAGE + mensaje + "\"}").type(MediaType.APPLICATION_JSON).build();
    }

    public Response createEntityForbiddenException(ForbiddenException ex) {
        return createEntityForbiddenException(ex.getMessage());
    }

    public Response createEntityForbiddenException(String mensaje) {
        return Response.status(Response.Status.FORBIDDEN).entity(MESSAGE + (mensaje == null ? ErroresGeneral.GEN_0001 : mensaje) + "\"}")
                .type(MediaType.APPLICATION_JSON).build();
    }

    public Response createEntityNotSupportedException(NotSupportedException e) {
        return createEntityNotSupportedException(e.getMessage());
    }

    private Response createEntityNotSupportedException(String mensaje) {
        return Response.status(Response.Status.NOT_IMPLEMENTED).entity(MESSAGE + (mensaje == null ? ErroresGeneral.GEN_0001 : mensaje) + "\"}")
                .type(MediaType.APPLICATION_JSON).build();
    }

    public Response createInternalServerErrorException(InternalServerErrorException ex) {
        return createInternalServerErrorException(ex.getMessage());
    }

    public Response createInternalServerErrorException(String mensaje) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(MESSAGE + mensaje + "\"}").type(MediaType.APPLICATION_JSON).build();
    }
}
