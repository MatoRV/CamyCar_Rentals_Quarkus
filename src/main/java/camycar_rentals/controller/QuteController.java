package camycar_rentals.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import base.util.Quark;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateExtension;
import io.quarkus.qute.TemplateInstance;

@Path("/qute/quarks")
public class QuteController {

    private final List<Quark> quarks = Collections.synchronizedList(new ArrayList<>());

    @Inject
    Template page;

    public QuteController() {
        for (int i = 0; i < 3; i++) {
            this.addQuark();
        }
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get() {
        return page.data("quarks", new ArrayList<>(quarks));
    }

    @POST
    @Path("add")
    public void addQuark() {
        final Random random = new Random();
        final Quark.Flavor flavor = Quark.Flavor.values()[random.nextInt(Quark.Flavor.values().length)];
        final Quark.Color color = Quark.Color.values()[random.nextInt(Quark.Color.values().length)];
        quarks.add(new Quark(flavor, color));
    }

    @TemplateExtension
    static int position(Quark quark) {
        return new Random().nextInt(100);
    }
}
