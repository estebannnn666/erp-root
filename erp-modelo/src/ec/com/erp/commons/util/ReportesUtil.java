package ec.com.erp.commons.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

import org.jsoup.Jsoup;
import org.xhtmlrenderer.pdf.ITextRenderer;

public class ReportesUtil {
	
	public static String htmlToXhtml(String html) {
        org.jsoup.nodes.Document document = Jsoup.parse(html);
        document.outputSettings().syntax(org.jsoup.nodes.Document.OutputSettings.Syntax.xml);
        return document.html();
    }

	public static byte[] xhtmlToPdf(String xhtml, String outFileName) throws IOException {
        File output = new File(outFileName);
        ITextRenderer iTextRenderer = new ITextRenderer();
        iTextRenderer.setDocumentFromString(xhtml);
        iTextRenderer.layout();
        OutputStream os = new FileOutputStream(output);
        iTextRenderer.createPDF(os);
        os.close();
        byte[] bytes = Files.readAllBytes(output.toPath());
        return bytes;
    }
}
