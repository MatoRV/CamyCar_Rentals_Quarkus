package camycar_rentals.controller;

import base.controller.BaseController;
import base.dto.cliente.ClienteDtoRequest;
import camycar_rentals.service.ClienteService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class ClienteController extends BaseController {

    @Inject
    ClienteService clienteService;

    @GET
    public Response obtenerClientes() {
        return Response.status(Response.Status.OK).entity(clienteService.obtenerClientes()).build();
    }

    @POST
    public Response crearCliente(ClienteDtoRequest clienteDtoRequest) {
        return Response.status(Response.Status.OK).entity(clienteService.crear(clienteDtoRequest)).build();
    }
}
