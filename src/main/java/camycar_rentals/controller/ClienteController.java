package camycar_rentals.controller;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import base.constant.errores.ErroresGeneral;
import base.controller.BaseController;
import base.dto.usuario.UsuarioDtoRequest;
import base.dto.usuario.UsuarioDtoResponse;
import camycar_rentals.service.UsuarioService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class ClienteController extends BaseController {

    @Inject
    UsuarioService usuarioService;

    @GET
    @Operation(summary = "Devuelve todos los clientes")
    @APIResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UsuarioDtoResponse.class)))
    @APIResponse(responseCode = "404", description = "NOT FOUND")
    public Response obtenerClientes() {
        return Response.status(Response.Status.OK).entity(usuarioService.obtenerClientes()).build();
    }

    @POST
    @Operation(summary = "Crea un cliente")
    @APIResponse(responseCode = "201", description = "CREATED", content = @Content(schema = @Schema(implementation = UsuarioDtoResponse.class)))
    @APIResponse(responseCode = "400", description = "BAD REQUEST")
    @APIResponse(responseCode = "403", description = "FORBIDDEN\n - " + ErroresGeneral.GEN_0002)
    @APIResponse(responseCode = "404", description = "NOT FOUND")
    public Response crearCliente(UsuarioDtoRequest usuarioDtoRequest) {
        return Response.status(Response.Status.CREATED).entity(usuarioService.crear(usuarioDtoRequest)).build();
    }

    @DELETE
    @Path("/{idCliente}")
    @Operation(summary = "Elimina un cliente")
    @APIResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UsuarioDtoResponse.class)))
    public Response eliminarCliente(@PathParam("idCliente") Integer idCliente) {
        return Response.status(Response.Status.OK).entity(usuarioService.eliminar(idCliente)).build();
    }

    @PUT
    @Path("/{idCliente}")
    @Operation(summary = "Edita un cliente")
    @APIResponse(responseCode = "201", description = "CREATED", content = @Content(schema = @Schema(implementation = UsuarioDtoResponse.class)))
    @APIResponse(responseCode = "400", description = "BAD REQUEST")
    @APIResponse(responseCode = "404", description = "NOT FOUND")
    public Response editarCliente(@PathParam("idCliente") Integer idCliente, UsuarioDtoRequest usuarioDtoRequest) {
        return Response.status(Response.Status.CREATED).entity(usuarioService.editar(idCliente, usuarioDtoRequest)).build();
    }
}
