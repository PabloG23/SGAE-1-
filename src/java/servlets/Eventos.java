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

/**
 *
 * @author pablog23
 */
@WebServlet(name = "Eventos", urlPatterns = {"/Eventos"})
public class Eventos extends HttpServlet {

    protected void Eventos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (OutputStream os = response.getOutputStream()) {

            Document document = new Document(PageSize.A4.rotate());

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            document.open();

            // PARRAFO
            String titulo = "Instituto Tecnológico de Toluca\n"
                    + "Subdirección de Planeación y Vinculación\n"
                    + "Departamento de Actividades Extraescolares\n"
                    + "Oficina de Promoción Cultural o Deportiva\n"
                    + "Informe de Actividad Cultural y/o Deportiva";
            Font fuenteParr = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD);
            Chunk chunk = new Chunk(titulo + "\n\n", fuenteParr);
            Paragraph parrafo = new Paragraph(chunk);
            parrafo.setAlignment(Element.ALIGN_CENTER);
            parrafo.setLeading(12f);

            PdfPTable tablitaParr = new PdfPTable(11);

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

            tablitaParr.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablitaParr.setWidthPercentage(50f);

            //
            PdfPTable tablita = new PdfPTable(16);

            Font fuente = FontFactory.getFont(FontFactory.HELVETICA, 8);
            Font fuenteSegundaTabla = FontFactory.getFont(FontFactory.HELVETICA, 8);
            Font fuentePrimeraTabla = FontFactory.getFont(FontFactory.HELVETICA, 10);

            PdfPCell celda;

            celda = new PdfPCell(new Phrase("Informe Semestral del Periodo(1)", fuente));
            celda.setColspan(4);
            tablita.addCell(celda);

            celda = new PdfPCell(new Phrase("Ago-Dic", fuentePrimeraTabla));
            celda.setColspan(2);
            tablita.addCell(celda);

            celda = new PdfPCell(new Phrase());
            celda.setColspan(1);
            tablita.addCell(celda);

            celda = new PdfPCell(new Phrase("Ene-Jun", fuentePrimeraTabla));
            celda.setColspan(2);
            tablita.addCell(celda);

            celda = new PdfPCell(new Phrase());
            celda.setColspan(1);
            tablita.addCell(celda);

            celda = new PdfPCell(new Phrase("Año", fuentePrimeraTabla));
            celda.setColspan(2);
            tablita.addCell(celda);

            celda = new PdfPCell(new Phrase());
            celda.setColspan(4);
            tablita.addCell(celda);

            tablita.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablita.setWidthPercentage(70f);

            PdfPTable tablita2 = new PdfPTable(16);

            PdfPCell chida;

            chida = new PdfPCell(new Phrase("Actividad Cultural(2)", fuentePrimeraTabla));
            chida.setColspan(4);
            tablita2.addCell(chida);

            chida = new PdfPCell(new Phrase(" ", fuenteParr));
            chida.setColspan(4);
            tablita2.addCell(chida);

            chida = new PdfPCell(new Phrase("Deportiva", fuentePrimeraTabla));
            chida.setColspan(3);
            tablita2.addCell(chida);

            chida = new PdfPCell(new Phrase(" ", fuenteParr));
            chida.setColspan(5);
            tablita2.addCell(chida);

            tablita2.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablita2.setWidthPercentage(70f);

            ////
            PdfPTable tablita3 = new PdfPTable(22);
            tablita3.setTotalWidth(document.getPageSize().getWidth() - 80);
            tablita3.setLockedWidth(true);

            PdfPCell celda2;

            celda2 = new PdfPCell(new Phrase("No.", fuente));
            celda2.setColspan(1);
            tablita3.addCell(celda2);

            celda2 = new PdfPCell(new Phrase("Nombre del Evento (3)", fuente));
            celda2.setColspan(5);
            tablita3.addCell(celda2);

            celda2 = new PdfPCell(new Phrase("Institución Organizadora (4)", fuente));
            celda2.setColspan(5);
            tablita3.addCell(celda2);

            celda2 = new PdfPCell(new Phrase("Interno \n" + "(5)", fuente));
            celda2.setColspan(1);
            tablita3.addCell(celda2);

            celda2 = new PdfPCell(new Phrase("Externo \n" + "(6)", fuente));
            celda2.setColspan(1);
            tablita3.addCell(celda2);

            celda2 = new PdfPCell(new Phrase("Fecha de \n" + "Realización \n" + "(7)", fuente));
            celda2.setColspan(2);
            tablita3.addCell(celda2);

            celda2 = new PdfPCell(new Phrase("H \n" + "(8)", fuente));
            celda2.setColspan(1);
            tablita3.addCell(celda2);

            celda2 = new PdfPCell(new Phrase("M \n" + "(9)", fuente));
            celda2.setColspan(1);
            tablita3.addCell(celda2);

            celda2 = new PdfPCell(new Phrase("Total \n" + "(10)", fuente));
            celda2.setColspan(1);
            tablita3.addCell(celda2);

            celda2 = new PdfPCell(new Phrase("Resultado \n" + "(11)", fuente));
            celda2.setColspan(5);
            tablita3.addCell(celda2);
            
            
            
                /////////////TABLA FECHA
            PdfPTable tablita4 = new PdfPTable(2);



            PdfPCell chida3;

            chida3 = new PdfPCell(new Phrase("Metepec, México a", fuentePrimeraTabla));
            chida3.setColspan(1);
            tablita4.addCell(chida3);

            chida3 = new PdfPCell(new Phrase(" ", fuentePrimeraTabla));
            chida3.setColspan(1);
            tablita4.addCell(chida3);

            tablita4.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tablita4.setWidthPercentage(40f);
            
            //
            PdfPTable tablita5 = new PdfPTable(13);
            
            PdfPCell chida4;

            chida4 = new PdfPCell(new Phrase("Vo.Bo. \n" + "Jefatura de Oficina de Promoción Cultural y/o Deportiva \n\n\n", fuentePrimeraTabla));
            chida4.setColspan(6);
            tablita5.addCell(chida4);

            chida4 = new PdfPCell(new Phrase(" ", fuentePrimeraTabla));
            chida4.setColspan(1);
            tablita5.addCell(chida4);

            chida4 = new PdfPCell(new Phrase("Vo.Bo. \n"
                    + "Jefatura  de Departamento Actividades Extraescolares \n\n\n", fuentePrimeraTabla));
            chida4.setColspan(6);
            tablita5.addCell(chida4);
            tablita5.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablita5.setWidthPercentage(50f);
            
            PdfPTable tablita6 = new PdfPTable(13);
            PdfPCell chida5;
            
            chida5 = new PdfPCell(new Phrase("Firma\n" +"Nombre:", fuentePrimeraTabla));
            chida5.setColspan(6);
            tablita6.addCell(chida5);

            chida5 = new PdfPCell(new Phrase(" ", fuentePrimeraTabla));
            chida5.setColspan(1);
            tablita6.addCell(chida5);

            chida5 = new PdfPCell(new Phrase("Firma\n" +"Nombre:", fuentePrimeraTabla));
            chida5.setColspan(6);
            tablita6.addCell(chida5);
            tablita6.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablita6.setWidthPercentage(50f);

           

            document.add(tablitaParr);
            document.add(new Paragraph(" "));
            document.add(tablita);
            document.add(tablita2);

            document.add(new Paragraph(" "));
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
        Eventos(request, response);
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
        Eventos(request, response);
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
