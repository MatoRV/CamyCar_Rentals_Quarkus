package camycar_rentals.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/ficheros")
@Produces(MediaType.TEXT_PLAIN)
public class PdfGeneratorController {

    @GET
    @Path("/{archivo}")
    public Uni<Response> pdf(@PathParam("archivo") String archivo) throws IOException {
        File nf = new File("src\\main\\resources\\ficheros\\"+archivo+".pdf");
        if (!Files.exists(nf.toPath())) {
            return Uni.createFrom().item(Response.status(Response.Status.NOT_FOUND).build());
        }
        Response.ResponseBuilder response = Response.ok((Object) nf);
        response.header("Content-Disposition","attachment;filename=" + nf.getName());
        return Uni.createFrom().item(response.build());
    }

//    @POST
//    public String pdfb64(PdfGeneratorRequest payload) throws IOException {
//        System.out.println(payload.template);
//        System.out.println(payload.data);
//
//        Template pdfTemplate = engine.parse(payload.template);
//
//        String result = pdfTemplate.data(payload.data).render();
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//
//        PdfRendererBuilder builder = new PdfRendererBuilder();
//        builder.useFastMode();
//        builder.withHtmlContent(result, PdfGeneratorController.class.getResource("/images/").toExternalForm());
//        builder.toStream(baos);
//        builder.run();
//
//        byte[] pdfdata = baos.toByteArray();
//
//        byte[] encode = Base64.getEncoder().encode(pdfdata);
//
//        return new String(encode, StandardCharsets.UTF_8);
//    }
}
