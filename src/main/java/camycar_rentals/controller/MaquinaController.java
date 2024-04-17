package camycar_rentals.controller;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import base.controller.BaseController;
import base.dto.maquina.MaquinaDtoRequest;
import base.dto.maquina.MaquinaDtoResponse;
import camycar_rentals.service.MaquinaService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/maquinas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class MaquinaController extends BaseController {

    @Inject
    MaquinaService maquinaService;

    @GET
    @Operation(summary = "Muestra una lista de maquinas")
    @APIResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = MaquinaDtoResponse.class)))
    @APIResponse(responseCode = "404", description = "NOT FOUND")
    public Response obtenerMaquinas() {
        return Response.status(Response.Status.OK).entity(maquinaService.obtenerMaquinas()).build();
    }

    @GET
    @Path("/{idMaquina}")
    @Operation(summary = "Muestra una maquina por su Id")
    @APIResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = MaquinaDtoResponse.class)))
    @APIResponse(responseCode = "404", description = "NOT FOUND")
    public Response obtenerMaquinaPorId(@PathParam("idMaquina") Integer idMaquina) {
        return Response.status(Response.Status.OK).entity(maquinaService.obtenerMaquinaPorId(idMaquina)).build();
    }


    @POST
    @Operation(summary = "Crea una maquina")
    @APIResponse(responseCode = "201", description = "CREATED", content = @Content(schema = @Schema(implementation = MaquinaDtoResponse.class)))
    @APIResponse(responseCode = "400", description = "BAD REQUEST")
    @APIResponse(responseCode = "404", description = "NOT FOUND")
    public Response crearMaquina(MaquinaDtoRequest maquinaDtoRequest) {
        return Response.status(Response.Status.CREATED).entity(maquinaService.crearMaquina(maquinaDtoRequest)).build();
    }
}
