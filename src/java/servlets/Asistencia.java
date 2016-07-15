package servlets;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Asistencia", urlPatterns = {"/Asistencia"})
public class Asistencia extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void Asistencia(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (OutputStream os = response.getOutputStream()) {

            Document document = new Document(PageSize.A4.rotate());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            document.open();

            // PARRAFO
            String titulo = "INSTITUTO TECNOLÓGICO DE TOLUCA\n"
                    + "DEPARTAMENTO DE ACTIVIDADES EXTRAESCOLARES\n"
                    + "LISTA DE ASISTENCIA DE LAS ACTIVIDADES COMPLEMENTARIAS CULTURALES Y/O DEPORTIVAS\n"
                    + "ACTIVIDAD: \n"
                    + "HORARIO: \n"
                    + "SEMESTRE: \n";
            Font fuenteParr = FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD);
            Chunk chunk = new Chunk(titulo + "\n\n", fuenteParr);
            Paragraph parrafo = new Paragraph(chunk);
            parrafo.setAlignment(Element.ALIGN_CENTER);
            parrafo.setLeading(12f);

            PdfPTable tablitaParr = new PdfPTable(11);
            tablitaParr.setTotalWidth(document.getPageSize().getWidth() - 80);
            tablitaParr.setLockedWidth(true);
            PdfPCell celdaParr;

            Image imagen = Image.getInstance("/home/pablog23/Imágenes/img.png");

            celdaParr = new PdfPCell(new Phrase());
            celdaParr.addElement(parrafo);
            celdaParr.setColspan(8);

            tablitaParr.addCell(celdaParr);

            celdaParr = new PdfPCell(new Phrase(" "));
            imagen.scaleAbsoluteWidth(60f);
            celdaParr.addElement(imagen);
            celdaParr.setColspan(3);
            tablitaParr.addCell(celdaParr);

/////////////////////////// TABLA PRINCIPAL
            Font fuente = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10);
            PdfPTable tablita = new PdfPTable(39);
            tablita.setTotalWidth(document.getPageSize().getWidth() - 80);
            tablita.setLockedWidth(true);

            PdfPTable tablita2 = new PdfPTable(39);
            tablita2.setTotalWidth(document.getPageSize().getWidth() - 80);
            tablita2.setLockedWidth(true);

            PdfPCell celda2;
            PdfPCell celda;

            celda = new PdfPCell(new Phrase());
            celda.setColspan(1);
            tablita2.addCell(celda);

            celda = new PdfPCell(new Phrase("NOMBRE ALUMNO",fuente));
            celda.setColspan(10);
            tablita2.addCell(celda);
            
            celda = new PdfPCell(new Phrase("AGOSTO"));
            celda.setColspan(6);
            tablita2.addCell(celda);

            celda = new PdfPCell(new Phrase("SEPTIEMBRE"));
            celda.setColspan(6);
            tablita2.addCell(celda);
            
             celda = new PdfPCell(new Phrase("OCTUBRE"));
            celda.setColspan(6);
            tablita2.addCell(celda);
            
             celda = new PdfPCell(new Phrase("NOVIEMBRE"));
            celda.setColspan(6);
            tablita2.addCell(celda);
            
            celda = new PdfPCell(new Phrase("DICIEMBRE"));
            celda.setColspan(4);
            tablita2.addCell(celda);

/////////**********
            celda = new PdfPCell(new Phrase("1"));
            celda.setColspan(1);
            tablita.addCell(celda);

            celda = new PdfPCell(new Phrase());
            celda.setColspan(10);
            tablita.addCell(celda);

            
            
            
            
            
            celda = new PdfPCell(new Phrase());
            celda.setColspan(1);
            tablita.addCell(celda);
            celda = new PdfPCell(new Phrase());
            celda.setColspan(1);
            tablita.addCell(celda);
            celda = new PdfPCell(new Phrase());
            celda.setColspan(1);
            tablita.addCell(celda);
            celda = new PdfPCell(new Phrase());
            celda.setColspan(1);
            tablita.addCell(celda);
            celda = new PdfPCell(new Phrase());
            celda.setColspan(1);
            tablita.addCell(celda);
            celda = new PdfPCell(new Phrase());
            celda.setColspan(1);
            tablita.addCell(celda);
            celda = new PdfPCell(new Phrase());
            celda.setColspan(1);
            tablita.addCell(celda);
            celda = new PdfPCell(new Phrase());
            celda.setColspan(1);
            tablita.addCell(celda);
            celda = new PdfPCell(new Phrase());
            celda.setColspan(1);
            tablita.addCell(celda);
            celda = new PdfPCell(new Phrase());
            celda.setColspan(1);
            tablita.addCell(celda);
            celda = new PdfPCell(new Phrase());
            celda.setColspan(1);
            tablita.addCell(celda);
            celda = new PdfPCell(new Phrase());
            celda.setColspan(1);
            tablita.addCell(celda);
            celda = new PdfPCell(new Phrase());
            celda.setColspan(1);
            tablita.addCell(celda);
            celda = new PdfPCell(new Phrase());
            celda.setColspan(1);
            tablita.addCell(celda);
            celda = new PdfPCell(new Phrase());
            celda.setColspan(1);
            tablita.addCell(celda);
            celda = new PdfPCell(new Phrase());
            celda.setColspan(1);
            tablita.addCell(celda);
            celda = new PdfPCell(new Phrase());
            celda.setColspan(1);
            tablita.addCell(celda);
            celda = new PdfPCell(new Phrase());
            celda.setColspan(1);
            tablita.addCell(celda);
            celda = new PdfPCell(new Phrase());
            celda.setColspan(1);
            tablita.addCell(celda);
            celda = new PdfPCell(new Phrase());
            celda.setColspan(1);
            tablita.addCell(celda);
            celda = new PdfPCell(new Phrase());
            celda.setColspan(1);
            tablita.addCell(celda);
            celda = new PdfPCell(new Phrase());
            celda.setColspan(1);
            tablita.addCell(celda);
            celda = new PdfPCell(new Phrase());
            celda.setColspan(1);
            tablita.addCell(celda);
            celda = new PdfPCell(new Phrase());
            celda.setColspan(1);
            tablita.addCell(celda);
            celda = new PdfPCell(new Phrase());
            celda.setColspan(1);
            tablita.addCell(celda);
            celda = new PdfPCell(new Phrase());
            celda.setColspan(1);
            tablita.addCell(celda);
            celda = new PdfPCell(new Phrase());
            celda.setColspan(1);
            tablita.addCell(celda);
            celda = new PdfPCell(new Phrase());
            celda.setColspan(1);
            tablita.addCell(celda);

            ////////////////////////TABLA PRINCIPAL
            /////////////TABLA FECHA
            ///////////////////// TABLA ULTIMA
            
            PdfPTable tablita5 = new PdfPTable(17);
            tablita5.setTotalWidth(document.getPageSize().getWidth() - 80);
            tablita5.setLockedWidth(true);

            PdfPCell chida3;
            Font fuentePrimeraTabla = FontFactory.getFont(FontFactory.HELVETICA, 10);

            chida3 = new PdfPCell(new Phrase("Elaboró \n" + "Promotor/a", fuentePrimeraTabla));
            chida3.setColspan(5);
            tablita5.addCell(chida3);

            chida3 = new PdfPCell(new Phrase(" ", fuentePrimeraTabla));
            chida3.setColspan(1);
            tablita5.addCell(chida3);

            chida3 = new PdfPCell(new Phrase("Vo.Bo. \n" + "Jefatura de Oficina de Promoción Cultural y/o Deportiva", fuentePrimeraTabla));
            chida3.setColspan(5);
            tablita5.addCell(chida3);

            chida3 = new PdfPCell(new Phrase(" ", fuentePrimeraTabla));
            chida3.setColspan(1);
            tablita5.addCell(chida3);

            chida3 = new PdfPCell(new Phrase("Vo.Bo. \n"
                    + "Jefatura  de Departamento Actividades Extraescolares ", fuentePrimeraTabla));
            chida3.setColspan(5);
            tablita5.addCell(chida3);

            PdfPTable tablita6 = new PdfPTable(17);
            tablita6.setTotalWidth(document.getPageSize().getWidth() - 80);
            tablita6.setLockedWidth(true);

            PdfPCell chida4;

            chida4 = new PdfPCell(new Phrase("Firma \n\n" + "Nombre:", fuentePrimeraTabla));
            chida4.setColspan(5);
            tablita5.addCell(chida4);

            chida4 = new PdfPCell(new Phrase(" ", fuentePrimeraTabla));
            chida4.setColspan(1);
            tablita5.addCell(chida4);

            chida4 = new PdfPCell(new Phrase("Firma \n\n" + "Nombre:", fuentePrimeraTabla));
            chida4.setColspan(5);
            tablita5.addCell(chida4);

            chida4 = new PdfPCell(new Phrase(" ", fuentePrimeraTabla));
            chida4.setColspan(1);
            tablita5.addCell(chida4);

            chida4 = new PdfPCell(new Phrase("Firma \n\n" + "Nombre:", fuentePrimeraTabla));
            chida4.setColspan(5);
            tablita5.addCell(chida4);
            document.add(tablitaParr);
            document.add(new Paragraph(" "));
            document.add(tablita2);
            document.add(tablita);
            document.add(new Paragraph(" "));
            document.add(tablita5);
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
        Asistencia(request, response);
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
        Asistencia(request, response);
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
