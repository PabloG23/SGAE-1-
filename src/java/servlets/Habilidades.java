/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author giovanni
 */
@WebServlet(name = "Habilidades", urlPatterns = {"/Habilidades"})
public class Habilidades extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void Habilidades(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (OutputStream os = response.getOutputStream()) {

            Document document = new Document();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            document.open();

            // PARRAFO
            String title = "INSTITUTO TECNOLÓGICO DE TOLUCA\n"
                    + "SUBDIRECCIÓN DE PLANEACIÓN Y VINCULACIÓN\n"
                    + "DEPARTAMENTO DE ACTIVIDADES EXTRAESCOLARES\n"
                    + "REGISTRO DE ALUMNOS CON HABILIDADES PARA INTEGRARSE\n"
                    + "A LOS GRUPOS REPRESENTATIVOS";
            Font fuenteParrafo = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD);
            Chunk chunk = new Chunk(title + "\n\n", fuenteParrafo);
            Paragraph titleParagraph = new Paragraph(chunk);
            titleParagraph.setAlignment(Element.ALIGN_CENTER);
            titleParagraph.setExtraParagraphSpace(50);
            document.add(titleParagraph);
/////////////////////////// TABLA PRINCIPAL
            PdfPTable tablita = new PdfPTable(11);
            tablita.setTotalWidth(document.getPageSize().getWidth() - 80);
            tablita.setLockedWidth(true);
            Font fuente = FontFactory.getFont(FontFactory.HELVETICA, 7);
            Font fuenteSegundaTabla = FontFactory.getFont(FontFactory.TIMES_ROMAN, 9);
            Font fuentePrimeraTabla = FontFactory.getFont(FontFactory.HELVETICA, 10);

            PdfPCell celda;

            celda = new PdfPCell(new Phrase("Oficina de Promocion(1)", fuente));
            celda.setColspan(1);
            tablita.addCell(celda);

            celda = new PdfPCell(new Phrase("Cultural", fuentePrimeraTabla));
            celda.setColspan(1);
            tablita.addCell(celda);

            celda = new PdfPCell(new Phrase());
            celda.setColspan(1);
            tablita.addCell(celda);

            celda = new PdfPCell(new Phrase("Deportiva", fuentePrimeraTabla));
            celda.setColspan(1);
            tablita.addCell(celda);

            celda = new PdfPCell(new Phrase());
            celda.setColspan(1);
            tablita.addCell(celda);

            celda = new PdfPCell(new Phrase("Ago-Dic", fuentePrimeraTabla));
            celda.setColspan(1);
            tablita.addCell(celda);

            celda = new PdfPCell(new Phrase());
            celda.setColspan(1);
            tablita.addCell(celda);

            celda = new PdfPCell(new Phrase("Ene-Jun", fuentePrimeraTabla));
            celda.setColspan(1);
            tablita.addCell(celda);

            celda = new PdfPCell(new Phrase());
            celda.setColspan(1);
            tablita.addCell(celda);

            celda = new PdfPCell(new Phrase("Año", fuentePrimeraTabla));
            celda.setColspan(1);
            tablita.addCell(celda);

            celda = new PdfPCell(new Phrase());
            celda.setColspan(1);
            tablita.addCell(celda);

            PdfPTable tablita2 = new PdfPTable(11);
            tablita2.setTotalWidth(document.getPageSize().getWidth() - 80);
            tablita2.setLockedWidth(true);
            PdfPCell chida;

            chida = new PdfPCell(new Phrase("Actividad(2)", fuentePrimeraTabla));
            chida.setColspan(2);
            tablita2.addCell(chida);

            chida = new PdfPCell(new Phrase(" ", fuenteParrafo));
            chida.setColspan(9);
            tablita2.addCell(chida);

            tablita2.setWidthPercentage(100f);
            ////////////////////////TABLA PRINCIPAL
            PdfPTable tablita3 = new PdfPTable(13);
            tablita3.setTotalWidth(document.getPageSize().getWidth() - 80);
            tablita3.setLockedWidth(true);

            PdfPCell chida1;

            chida1 = new PdfPCell(new Phrase("No.", fuenteSegundaTabla));
            chida1.setColspan(1);
            tablita3.addCell(chida1);

            chida1 = new PdfPCell(new Phrase("NOMBRE ALUMNO(3)", fuenteSegundaTabla));
            chida1.setColspan(6);
            tablita3.addCell(chida1);

            chida1 = new PdfPCell(new Phrase("CARRERA(4)", fuenteSegundaTabla));
            chida1.setColspan(2);
            tablita3.addCell(chida1);

            chida1 = new PdfPCell(new Phrase("CONTROL(5)", fuenteSegundaTabla));
            chida1.setColspan(2);
            tablita3.addCell(chida1);

            chida1 = new PdfPCell(new Phrase("SEMESTRE(6)", fuenteSegundaTabla));
            chida1.setColspan(2);
            tablita3.addCell(chida1);

            /////////////TABLA FECHA
            PdfPTable tablita4 = new PdfPTable(2);
//            tablita4.setTotalWidth(document.getPageSize().getWidth() - 80);
//            tablita4.setLockedWidth(true);

            PdfPCell chida2;

            chida2 = new PdfPCell(new Phrase("Metepec, México a", fuentePrimeraTabla));
            chida2.setColspan(1);
            tablita4.addCell(chida2);

            chida2 = new PdfPCell(new Phrase(" ", fuentePrimeraTabla));
            chida2.setColspan(1);
            tablita4.addCell(chida2);
            
            tablita4.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tablita4.setWidthPercentage(40f);

            ///////////////////// TABLA ULTIMA
            PdfPTable tablita5 = new PdfPTable(17);
            tablita5.setTotalWidth(document.getPageSize().getWidth() - 80);
            tablita5.setLockedWidth(true);

            PdfPCell chida3;

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
            
            chida4 = new PdfPCell(new Phrase("Firma \n\n" +"Nombre:", fuentePrimeraTabla));
            chida4.setColspan(5);
            tablita5.addCell(chida4);
            
            chida4 = new PdfPCell(new Phrase(" ", fuentePrimeraTabla));
            chida4.setColspan(1);
            tablita5.addCell(chida4);
            
            chida4 = new PdfPCell(new Phrase("Firma \n\n" +"Nombre:", fuentePrimeraTabla));
            chida4.setColspan(5);
            tablita5.addCell(chida4);
            
            chida4 = new PdfPCell(new Phrase(" ", fuentePrimeraTabla));
            chida4.setColspan(1);
            tablita5.addCell(chida4);
            
            chida4 = new PdfPCell(new Phrase("Firma \n\n" +"Nombre:", fuentePrimeraTabla));
            chida4.setColspan(5);
            tablita5.addCell(chida4);

            document.add(tablita);
            document.add(tablita2);
            document.add(new Paragraph(" "));
            document.add(tablita3);
            document.add(new Paragraph(" "));
            document.add(tablita4);
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
        Habilidades(request, response);
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
        Habilidades(request, response);
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
