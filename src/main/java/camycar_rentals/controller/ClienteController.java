package camycar_rentals.controller;

import base.constant.errores.ErroresGeneral;
import base.controller.BaseController;
import base.dto.cliente.ClienteDtoRequest;
import base.dto.cliente.ClienteDtoResponse;
import camycar_rentals.service.ClienteService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class ClienteController extends BaseController {

    @Inject
    ClienteService clienteService;

    @GET
    @Operation(summary = "Devuelve todos los clientes")
    @APIResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ClienteDtoResponse.class)))
    @APIResponse(responseCode = "404", description = "NOT FOUND")
    public Response obtenerClientes() {
        return Response.status(Response.Status.OK).entity(clienteService.obtenerClientes()).build();
    }

    @POST
    @Operation(summary = "Crea un cliente")
    @APIResponse(responseCode = "201", description = "CREATED", content = @Content(schema = @Schema(implementation = ClienteDtoResponse.class)))
    @APIResponse(responseCode = "400", description = "BAD REQUEST")
    @APIResponse(responseCode = "403", description = "FORBIDDEN\n - " + ErroresGeneral.GEN_0002)
    @APIResponse(responseCode = "404", description = "NOT FOUND")
    public Response crearCliente(ClienteDtoRequest clienteDtoRequest) {
        return Response.status(Response.Status.CREATED).entity(clienteService.crear(clienteDtoRequest)).build();
    }

    @DELETE
    @Path("/{idCliente}")
    @Operation(summary = "Elimina un cliente")
    @APIResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ClienteDtoResponse.class)))
    public Response eliminarCliente(@PathParam("idCliente") Integer idCliente) {
        return Response.status(Response.Status.OK).entity(clienteService.eliminar(idCliente)).build();
    }
}
