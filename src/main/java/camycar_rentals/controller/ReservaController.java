package camycar_rentals.controller;

import java.io.IOException;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import base.dto.reserva.ReservaDtoRequest;
import base.dto.reserva.ReservaDtoResponse;
import camycar_rentals.service.ReservaService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/reservas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class ReservaController {

    @Inject
    ReservaService reservaService;

    @GET
    @Operation(summary = "Obtiene una lista de todas las reservas")
    @APIResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ReservaDtoResponse.class)))
    @APIResponse(responseCode = "404", description = "NOT FOUND")
    public Response obtenerReservas() {
        return Response.status(Response.Status.OK).entity(reservaService.obtenerReservas()).build();
    }

    @GET
    @Path("/{idReserva}")
    @Operation(summary = "Obtiene una reserva por su id")
    @APIResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ReservaDtoResponse.class)))
    @APIResponse(responseCode = "404", description = "NOT FOUND")
    public Response obtenerReservaPorId(@PathParam("idReserva") Integer idReserva) {
        return Response.status(Response.Status.OK).entity(reservaService.obtenerReservaPorId(idReserva)).build();
    }

    @POST
    @Operation(summary = "Crea una reserva")
    @APIResponse(responseCode = "201", description = "CREATED", content = @Content(schema = @Schema(implementation = ReservaDtoResponse.class)))
    @APIResponse(responseCode = "400", description = "BAD REQUEST")
    @APIResponse(responseCode = "404", description = "NOT FOUND")
    public Response crearReserva(ReservaDtoRequest reservaDtoRequest) throws IOException {
        return Response.status(Response.Status.CREATED).entity(reservaService.crearReserva(reservaDtoRequest)).build();
    }

    @DELETE
    @Path("/{idReserva}")
    @Operation(summary = "Borra una reserva")
    @APIResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ReservaDtoResponse.class)))
    @APIResponse(responseCode = "400", description = "BAD REQUEST")
    @APIResponse(responseCode = "404", description = "NOT FOUND")
    public Response eliminarReserva(@PathParam("idReserva") Integer idReserva) {
        return Response.status(Response.Status.OK).entity(reservaService.eliminarReserva(idReserva)).build();
    }
}
