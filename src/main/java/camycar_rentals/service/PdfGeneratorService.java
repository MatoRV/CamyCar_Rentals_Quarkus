package camycar_rentals.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import com.openhtmltopdf.pdfboxout.PdfBoxRenderer;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import jakarta.enterprise.context.RequestScoped;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.gradle.internal.impldep.org.apache.commons.lang.text.StrBuilder;
import io.quarkus.qute.Engine;
import io.quarkus.qute.Template;
@RequestScoped
public class PdfGeneratorService {

    public void generarPdf() throws IOException {
        Engine engine = Engine.builder().addDefaults().build();
        File template = new File("C:\\Users\\victor.mato\\Documents\\PI\\CamyCar_Rentals_Quarkus\\src\\main\\resources\\templates\\template.html");
        StrBuilder templ = new StrBuilder();
        Object[] lineas;

        FileReader fr = new FileReader(template);
        BufferedReader br = new BufferedReader(fr);
        lineas = br.lines().toArray();

        for (int i = 0; i < lineas.length; i++) {
            templ.append(lineas[i].toString());
        }

        Template pdfTemplate = engine.parse(templ.toString());

        String result = pdfTemplate.data("name","Victor").render();

        PDDocument pdDocument = new PDDocument();

        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.useFastMode();
        builder.withHtmlContent(result,PdfGeneratorService.class.getResource("/templates/").toExternalForm());
        builder.usePDDocument(pdDocument);
        PdfBoxRenderer renderer = builder.buildPdfRenderer();
        renderer.createPDFWithoutClosing();
        renderer.close();

        OutputStream os = new FileOutputStream("C:\\Users\\victor.mato\\Documents\\PI\\CamyCar_Rentals_Quarkus\\src\\main\\resources\\ficheros"
                + "\\output.pdf");
        pdDocument.save(os);
        os.close();

    }
}
