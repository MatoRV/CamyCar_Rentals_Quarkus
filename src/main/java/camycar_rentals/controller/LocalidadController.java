package camycar_rentals.controller;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import base.controller.BaseController;
import base.dto.localidad.LocalidadDtoResponse;
import camycar_rentals.service.LocalidadService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/localidades")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class LocalidadController extends BaseController {

    @Inject
    LocalidadService localidadService;

    @GET
    @Operation(summary = "Muestra una lista de las localidades")
    @APIResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = LocalidadDtoResponse.class)))
    @APIResponse(responseCode = "404", description = "NOT FOUND")
    public Response obtenerLocalidades() {
        return Response.status(Response.Status.OK).entity(localidadService.obtenerLocalidades()).build();
    }
}
