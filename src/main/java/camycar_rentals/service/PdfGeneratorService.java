package camycar_rentals.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import camycar_rentals.domain.Reserva;
import com.openhtmltopdf.pdfboxout.PdfBoxRenderer;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import jakarta.enterprise.context.RequestScoped;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.gradle.internal.impldep.org.apache.commons.lang.text.StrBuilder;
import io.quarkus.qute.Engine;
import io.quarkus.qute.Template;
@RequestScoped
public class PdfGeneratorService {

    public void generarPdf(Reserva reserva) throws IOException {
        Engine engine = Engine.builder().addDefaults().build();
        File template = new File("src\\main\\resources\\templates\\reserva-template.html");
        StrBuilder templ = new StrBuilder();
        Object[] lineas;

        FileReader fr = new FileReader(template);
        BufferedReader br = new BufferedReader(fr);
        lineas = br.lines().toArray();

        for (int i = 0; i < lineas.length; i++) {
            templ.append(lineas[i].toString());
        }

        Template pdfTemplate = engine.parse(templ.toString());

        String result = pdfTemplate.data("idReserva", reserva.getIdReserva()).data("nombreCliente", reserva.getCliente().getNombre())
                .data("direccion", reserva.getDireccion()).data("fechaInicio", reserva.getFechaInicio()).data("fechaFin", reserva.getFechaFin())
                .data("fabricante", reserva.getMaquina().getFabricante()).data("modelo", reserva.getMaquina().getModelo())
                .data("capacidadCarga", reserva.getMaquina().getCapacidadCarga()).data("fechaActual", LocalDate.now().toString()).render();

        PDDocument pdDocument = new PDDocument();

        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.useFastMode();
        builder.withHtmlContent(result, PdfGeneratorService.class.getResource("/templates/").toExternalForm());
        builder.usePDDocument(pdDocument);
        PdfBoxRenderer renderer = builder.buildPdfRenderer();
        renderer.createPDFWithoutClosing();
        renderer.close();

        OutputStream os = new FileOutputStream("src\\main\\resources\\ficheros\\Reserva" + reserva.getIdReserva() + ".pdf");
        pdDocument.save(os);
        os.close();
    }
}
