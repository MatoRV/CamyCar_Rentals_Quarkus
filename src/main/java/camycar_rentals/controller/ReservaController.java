package camycar_rentals.controller;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import base.dto.reserva.ReservaDtoResponse;
import camycar_rentals.service.ReservaService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
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
}
