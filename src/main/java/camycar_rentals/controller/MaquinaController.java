package camycar_rentals.controller;

import base.controller.BaseController;
import camycar_rentals.service.MaquinaService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/maquinas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class MaquinaController extends BaseController {

    @Inject
    MaquinaService maquinaService;
}
