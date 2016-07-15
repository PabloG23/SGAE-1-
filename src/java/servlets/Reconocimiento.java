package servlets;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Reconocimiento", urlPatterns = {"/Reconocimiento"})
public class Reconocimiento extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void Reconocimiento(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (OutputStream os = response.getOutputStream()) {

            Document document = new Document();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            document.open();

            // PARRAFO
            Font fuenteParrafo1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);

            ////////////////////////TABLA PRINCIPAL
            PdfPTable tablita3 = new PdfPTable(18);
            tablita3.setTotalWidth(document.getPageSize().getWidth() - 80);
            tablita3.setLockedWidth(true);

            PdfPCell chida1;

            chida1 = new PdfPCell(new Phrase("N°", fuenteParrafo1));
            chida1.setColspan(1);
            tablita3.addCell(chida1);

            chida1 = new PdfPCell(new Phrase("ACTIVIDAD", fuenteParrafo1));
            chida1.setColspan(3);
            tablita3.addCell(chida1);

            chida1 = new PdfPCell(new Phrase("NOMBRE DEL ALUMNO", fuenteParrafo1));
            chida1.setColspan(7);
            tablita3.addCell(chida1);

            chida1 = new PdfPCell(new Phrase("CARRERA", fuenteParrafo1));
            chida1.setColspan(5);
            tablita3.addCell(chida1);

            chida1 = new PdfPCell(new Phrase("CONTROL", fuenteParrafo1));
            chida1.setColspan(2);
            tablita3.addCell(chida1);
            
                   

            /////////////TABLA FECHA
            PdfPTable tablita4 = new PdfPTable(2);
//            tablita4.setTotalWidth(document.getPageSize().getWidth() - 80);
//            tablita4.setLockedWidth(true);

            PdfPCell chida2;

            chida2 = new PdfPCell(new Phrase("Metepec, México a", fuenteParrafo1));
            chida2.setColspan(1);
            tablita4.addCell(chida2);

            chida2 = new PdfPCell(new Phrase(" ", fuenteParrafo1));
            chida2.setColspan(1);
            tablita4.addCell(chida2);

            tablita4.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tablita4.setWidthPercentage(40f);

            ///////////////////// TABLA ULTIMA
            PdfPTable tablita5 = new PdfPTable(13);

            PdfPCell chida4;

            chida4 = new PdfPCell(new Phrase("Vo.Bo. \n" + "Jefatura de Oficina de Promoción Cultural y/o Deportiva \n\n\n", fuenteParrafo1));
            chida4.setColspan(6);
            tablita5.addCell(chida4);

            chida4 = new PdfPCell(new Phrase(" ", fuenteParrafo1));
            chida4.setColspan(1);
            tablita5.addCell(chida4);

            chida4 = new PdfPCell(new Phrase("Vo.Bo. \n"
                    + "Jefatura  de Departamento Actividades Extraescolares \n\n\n", fuenteParrafo1));
            chida4.setColspan(6);
            tablita5.addCell(chida4);
            tablita5.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablita5.setWidthPercentage(100f);

            PdfPTable tablita6 = new PdfPTable(13);
            PdfPCell chida5;

            chida5 = new PdfPCell(new Phrase("Firma\n" + "Nombre:", fuenteParrafo1));
            chida5.setColspan(6);
            tablita6.addCell(chida5);

            chida5 = new PdfPCell(new Phrase(" ", fuenteParrafo1));
            chida5.setColspan(1);
            tablita6.addCell(chida5);

            chida5 = new PdfPCell(new Phrase("Firma\n" + "Nombre:", fuenteParrafo1));
            chida5.setColspan(6);
            tablita6.addCell(chida5);
            tablita6.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablita6.setWidthPercentage(100f);

            document.add(this.addParagraph());
            document.add(tablita3);
            document.add(new Paragraph(" "));
            document.add(tablita4);
            document.add(new Paragraph(" "));
            document.add(tablita5);
            document.add(tablita6);

            // Close document
            document.close();

            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            response.setContentType("application/pdf");
            response.setContentLength(baos.size());

            // write ByteArrayOutputStream to the ServletOutputStream
            baos.writeTo(os);
            os.flush();
        } catch (DocumentException e) {
            System.err.println(e.getMessage());
        }
    }

    private Paragraph addParagraph() {
        // PARRAFO

        String title = "SUBDIRECCIÓN DE PLANEACIÓN Y VINCULACIÓN\n"
                + "DEPARTAMENTO DE ACTIVIDADES EXTRAESCOLARES\n"
                + "REGISTRO DE ALUMNOS DE LAS ACTIVIDADES COMPLEMENTARIAS Y REPRESENTATIVOS\n"
                + "CULTURALES Y/O DEPORTIVA PARA RECIBIR  RECONOCIMIENTO POR SU DESTACADA\n"
                + "PARTICIPACIÓN";
        Font fuenteParrafo1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
        Chunk chunk = new Chunk(title + "\n\n", fuenteParrafo1);
        Paragraph titleParagraph = new Paragraph(chunk);
        titleParagraph.setAlignment(Element.ALIGN_CENTER);
        titleParagraph.setExtraParagraphSpace(50);
        return titleParagraph;

    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Reconocimiento(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Reconocimiento(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
