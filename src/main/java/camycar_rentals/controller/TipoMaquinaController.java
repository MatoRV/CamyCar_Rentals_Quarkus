package camycar_rentals.controller;

import base.controller.BaseController;
import camycar_rentals.service.TipoMaquinaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;

@Path("/tipomaquina")
public class TipoMaquinaController extends BaseController {

    @Inject
    TipoMaquinaService tipoMaquinaService;
}
