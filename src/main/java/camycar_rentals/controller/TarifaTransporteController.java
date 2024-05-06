package camycar_rentals.controller;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import base.controller.BaseController;
import base.dto.tarifatransporte.TarifaTransporteDtoResponse;
import camycar_rentals.service.TarifaTransporteService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/tarifa-transporte")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TarifaTransporteController extends BaseController {

    @Inject
    TarifaTransporteService tarifaTransporteService;

//    @GET
//    @Operation(summary = "Obtiene un listado de las tarifas transporte")
//    @APIResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = TarifaTransporteDtoResponse.class)))
//    @APIResponse(responseCode = "404", description = "NOT FOUND")
//    public Response obtenerTarifasTransporte() {
//        return Response.status(Response.Status.OK).entity(tarifaTransporteService.obtenerTarifasTransporte()).build();
//    }

    @GET
    @Operation(summary = "Obtiene una tarifa según la localidad")
    @APIResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = TarifaTransporteDtoResponse.class)))
    @APIResponse(responseCode = "404", description = "NOT FOUND")
    public Response obtenerTarifaTransportePorLocalidad(@QueryParam("localidad")String localidad) {
        return Response.status(Response.Status.OK).entity(tarifaTransporteService.obtenerTarifaTransportePorLocalidad(localidad)).build();
    }
}
