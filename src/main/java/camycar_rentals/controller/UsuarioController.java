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
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class UsuarioController extends BaseController {

    @Inject
    UsuarioService usuarioService;

    @GET
    @Operation(summary = "Devuelve todos los usuarios")
    @APIResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UsuarioDtoResponse.class)))
    @APIResponse(responseCode = "404", description = "NOT FOUND")
    public Response obtenerUsuarios() {
        return Response.status(Response.Status.OK).entity(usuarioService.obtenerUsuarios()).build();
    }

    @POST
    @Operation(summary = "Crea un usuario")
    @APIResponse(responseCode = "201", description = "CREATED", content = @Content(schema = @Schema(implementation = UsuarioDtoResponse.class)))
    @APIResponse(responseCode = "400", description = "BAD REQUEST")
    @APIResponse(responseCode = "403", description = "FORBIDDEN\n - " + ErroresGeneral.GEN_0002)
    @APIResponse(responseCode = "404", description = "NOT FOUND")
    public Response crearUsuario(UsuarioDtoRequest usuarioDtoRequest) {
        return Response.status(Response.Status.CREATED).entity(usuarioService.crear(usuarioDtoRequest)).build();
    }

    @DELETE
    @Path("/{idUsuario}")
    @Operation(summary = "Elimina un usuario")
    @APIResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UsuarioDtoResponse.class)))
    public Response eliminarUsuario(@PathParam("idUsuario") Integer idUsuario) {
        return Response.status(Response.Status.OK).entity(usuarioService.eliminar(idUsuario)).build();
    }

    @PUT
    @Path("/{idUsuario}")
    @Operation(summary = "Edita un usuario")
    @APIResponse(responseCode = "201", description = "CREATED", content = @Content(schema = @Schema(implementation = UsuarioDtoResponse.class)))
    @APIResponse(responseCode = "400", description = "BAD REQUEST")
    @APIResponse(responseCode = "404", description = "NOT FOUND")
    public Response editarUsuario(@PathParam("idUsuario") Integer idUsuario, UsuarioDtoRequest usuarioDtoRequest) {
        return Response.status(Response.Status.CREATED).entity(usuarioService.editar(idUsuario, usuarioDtoRequest)).build();
    }

    @GET
    @Path("/comprobar")
    @Operation(summary = "Comprueba las credenciales de un usuario")
    @APIResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UsuarioDtoResponse.class)))
    @APIResponse(responseCode = "400", description = "BAD REQUEST")
    @APIResponse(responseCode = "403", description = "FORBIDDEN " + ErroresGeneral.GEN_0003)
    @APIResponse(responseCode = "404", description = "NOT FOUND")
    public Response comprobarUsuario(@QueryParam("correo") String correo, @QueryParam("contrasena") String contrasena) {
        return Response.status(Response.Status.OK).entity(usuarioService.comprobarUsuario(correo, contrasena)).build();
    }
}
