package camycar_rentals.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import camycar_rentals.domain.Localidad;
import camycar_rentals.domain.Reserva;
import camycar_rentals.domain.TarifaTransporte;
import camycar_rentals.repository.LocalidadRepository;
import camycar_rentals.repository.TarifaTransporteRepository;
import com.openhtmltopdf.pdfboxout.PdfBoxRenderer;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.gradle.internal.impldep.org.apache.commons.lang.text.StrBuilder;
import io.quarkus.qute.Engine;
import io.quarkus.qute.Template;

@RequestScoped
public class PdfGeneratorService {

    @Inject
    TarifaTransporteRepository tarifaTransporteRepository;

    @Inject
    LocalidadRepository localidadRepository;

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

        Localidad localidad = localidadRepository.obtenerLocalidadPorNombre(reserva.getDireccion());
        TarifaTransporte tarifaTransporte = tarifaTransporteRepository.obtenerTarifaTransportePorLocalidad(localidad.getIdLocalidad());
        Integer precioTarifa;
        if (reserva.getMaquina().getCapacidadCarga() <= 4000) {
            precioTarifa = tarifaTransporte.getP4000();
        } else if (reserva.getMaquina().getCapacidadCarga() <= 9000) {
            precioTarifa = tarifaTransporte.getP9000();
        } else {
            precioTarifa = tarifaTransporte.getP14000();
        }

        Integer diferenciaDias = Math.toIntExact(ChronoUnit.DAYS.between(reserva.getFechaInicio(), reserva.getFechaFin()));
        Integer precioPorDia = 60;
        if (diferenciaDias > 7 && diferenciaDias <= 21) {
            precioPorDia = 50;
        } else if (diferenciaDias >= 30) {
            precioPorDia = 35;
        }

        Integer precioTotalPorDias = precioPorDia * diferenciaDias;
        Integer precioTotal = precioTotalPorDias + precioTarifa;

        String result = pdfTemplate.data("idReserva", reserva.getIdReserva()).data("nombreCliente", reserva.getCliente().getNombre())
                .data("direccion", reserva.getDireccion()).data("fechaInicio", reserva.getFechaInicio()).data("fechaFin", reserva.getFechaFin())
                .data("fabricante", reserva.getMaquina().getFabricante()).data("modelo", reserva.getMaquina().getModelo())
                .data("fechaActual", LocalDate.now().toString()).data("precioPorDia", precioPorDia + "€").data("cantidadDiasAlquiler", diferenciaDias)
                .data("precioTotalPorDia", precioTotalPorDias + "€").data("tarifaTransporte", precioTarifa + "€").data("precioSubTotal", precioTotal + "€")
                .data("precioTotal", precioTotal + "€").render();

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
