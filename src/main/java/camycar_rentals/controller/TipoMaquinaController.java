package camycar_rentals.controller;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import base.controller.BaseController;
import base.dto.tipomaquina.TipoMaquinaDtoRequest;
import base.dto.tipomaquina.TipoMaquinaDtoResponse;
import camycar_rentals.service.TipoMaquinaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/tipomaquina")
public class TipoMaquinaController extends BaseController {

    @Inject
    TipoMaquinaService tipoMaquinaService;

    @POST
    @Operation(summary = "Crea un tipo maquina")
    @APIResponse(responseCode = "201", description = "CREATED", content = @Content(schema = @Schema(implementation = TipoMaquinaDtoResponse.class)))
    @APIResponse(responseCode = "400", description = "BAD REQUEST")
    @APIResponse(responseCode = "404", description = "NOT FOUND")
    public Response crearTipoMaquina(TipoMaquinaDtoRequest tipoMaquinaDtoRequest) {
        return Response.status(Response.Status.CREATED).entity(tipoMaquinaService.crearTipoMaquina(tipoMaquinaDtoRequest)).build();
    }

    @GET
    @Operation(summary = "Obtiene todos los tipos de maquina")
    @APIResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = TipoMaquinaDtoResponse.class)))
    @APIResponse(responseCode = "404", description = "NOT FOUND")
    public Response obtenerTiposMaquina() {
        return Response.status(Response.Status.OK).entity(tipoMaquinaService.obtenerTiposMaquina()).build();
    }

    @GET
    @Path("/{idTipoMaquina}")
    @Operation(summary = "Obtiene un tipo de maquina por su id")
    @APIResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = TipoMaquinaDtoResponse.class)))
    @APIResponse(responseCode = "404", description = "NOT FOUND")
    public Response obtenerTipoMaquinaPorId(@PathParam("idTipoMaquina") Integer idTipoMaquina) {
        return Response.status(Response.Status.OK).entity(tipoMaquinaService.obtenerTipoMaquinaPorId(idTipoMaquina)).build();
    }
}
