/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Modelo.BeanEvento;
import Modelo.BeanUsuario;
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
import entities.Alumno;
import entities.Evento;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    private BeanEvento obj;

    protected void Eventos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        obj = (BeanEvento) request.getSession().getAttribute("beanEvento");
        try (OutputStream os = response.getOutputStream()) {

            Document document = new Document(PageSize.A4.rotate());

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            document.open();
            int rango = 10, alumnos = obj.eventoCultural().size();
            int f = alumnos;
            double y = alumnos / rango;
            double r = Math.ceil(y) + 1;
            int h = (int) r;
            PdfPTable tablita3 = new PdfPTable(22);
            tablita3.setTotalWidth(document.getPageSize().getWidth() - 80);
            tablita3.setLockedWidth(true);
            PdfPCell celda2;
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            List<Evento> lista = new ArrayList<Evento>();
            lista = obj.eventoCultural();
            Font fuente = FontFactory.getFont(FontFactory.HELVETICA, 8);

            if (alumnos <= rango) {
                document.add(this.t1());
                document.add(new Paragraph(" "));
                document.add(this.t2());
                document.add(this.t3());
                document.add(new Paragraph(" "));
                document.add(this.t4(document));
                document.add(this.t5(document));//++++

                while (f <= rango) {
                    document.add(new Paragraph(" "));
                    f++;
                }
                document.add(new Paragraph(" "));
                document.add(this.t6());
                document.add(new Paragraph(" "));
                document.add(this.t7());
                document.add(this.t8());

            } else {
                int tamañolista = lista.size(), j, k = 0;
                for (int i = 0; i < h; i++) {

                    document.add(this.t1());
                    document.add(new Paragraph(" "));
                    document.add(this.t2());
                    document.add(this.t3());
                    document.add(new Paragraph(" "));
                    document.add(this.t4(document));

                    tamañolista = lista.size();
                    System.out.println("tamaño actual:" + tamañolista);
                    if (tamañolista > k && tamañolista <= rango) {
                        for (int l = k; l < lista.size(); l++) {
                            celda2 = new PdfPCell(new Phrase(i + 1 + "", fuente));
                            celda2.setColspan(1);
                            tablita3.addCell(celda2);

                            celda2 = new PdfPCell(new Phrase(lista.get(l).getNombreEvento(), fuente));
                            celda2.setColspan(5);
                            tablita3.addCell(celda2);

                            celda2 = new PdfPCell(new Phrase(lista.get(l).getInstOrganizadora(), fuente));
                            celda2.setColspan(5);
                            tablita3.addCell(celda2);

                            celda2 = new PdfPCell(new Phrase(interno(l), fuente));
                            celda2.setColspan(1);
                            tablita3.addCell(celda2);

                            celda2 = new PdfPCell(new Phrase(externo(l), fuente));
                            celda2.setColspan(1);
                            tablita3.addCell(celda2);

                            celda2 = new PdfPCell(new Phrase(formatter.format(lista.get(l).getFechaEvento()) + "", fuente));
                            celda2.setColspan(2);
                            tablita3.addCell(celda2);

                            celda2 = new PdfPCell(new Phrase(lista.get(l).getHombres() + "", fuente));
                            celda2.setColspan(1);
                            tablita3.addCell(celda2);

                            celda2 = new PdfPCell(new Phrase(lista.get(l).getMujeres() + "", fuente));
                            celda2.setColspan(1);
                            tablita3.addCell(celda2);

                            celda2 = new PdfPCell(new Phrase(lista.get(l).getTotal() + "", fuente));
                            celda2.setColspan(1);
                            tablita3.addCell(celda2);

                            celda2 = new PdfPCell(new Phrase(lista.get(l).getResultado(), fuente));
                            celda2.setColspan(5);
                            tablita3.addCell(celda2);

                        }
                    } else {
                        for (j = k; j < rango; j++) {
                            celda2 = new PdfPCell(new Phrase(j + 1 + "", fuente));
                            celda2.setColspan(1);
                            tablita3.addCell(celda2);

                            celda2 = new PdfPCell(new Phrase(lista.get(j).getNombreEvento(), fuente));
                            celda2.setColspan(5);
                            tablita3.addCell(celda2);

                            celda2 = new PdfPCell(new Phrase(lista.get(j).getInstOrganizadora(), fuente));
                            celda2.setColspan(5);
                            tablita3.addCell(celda2);

                            celda2 = new PdfPCell(new Phrase(interno(j), fuente));
                            celda2.setColspan(1);
                            tablita3.addCell(celda2);

                            celda2 = new PdfPCell(new Phrase(externo(j), fuente));
                            celda2.setColspan(1);
                            tablita3.addCell(celda2);

                            celda2 = new PdfPCell(new Phrase(formatter.format(lista.get(j).getFechaEvento()) + "", fuente));
                            celda2.setColspan(2);
                            tablita3.addCell(celda2);

                            celda2 = new PdfPCell(new Phrase(lista.get(j).getHombres() + "", fuente));
                            celda2.setColspan(1);
                            tablita3.addCell(celda2);

                            celda2 = new PdfPCell(new Phrase(lista.get(j).getMujeres() + "", fuente));
                            celda2.setColspan(1);
                            tablita3.addCell(celda2);

                            celda2 = new PdfPCell(new Phrase(lista.get(j).getTotal() + "", fuente));
                            celda2.setColspan(1);
                            tablita3.addCell(celda2);

                            celda2 = new PdfPCell(new Phrase(lista.get(j).getResultado(), fuente));
                            celda2.setColspan(5);
                            tablita3.addCell(celda2);
                        }
                        k = rango;
                        rango = rango + 10;
                    }
                    document.add(tablita3);
                    document.add(new Paragraph(" "));
                    document.add(this.t6());
                    document.add(new Paragraph(" "));
                    document.add(this.t7());
                    document.add(this.t8());
                    document.newPage();
                    tablita3.deleteBodyRows();
                }
            }
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

    public PdfPTable t1() {
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

//            Image imagen = Image.getInstance("/home/pablog23/Imágenes/img.png");
        celdaParr = new PdfPCell(new Phrase());
        celdaParr.addElement(parrafo);
        celdaParr.setColspan(8);

        tablitaParr.addCell(celdaParr);

        celdaParr = new PdfPCell(new Phrase(" "));
//            imagen.scaleAbsoluteWidth(60f);
//            celdaParr.addElement(imagen);
        celdaParr.setColspan(3);

        tablitaParr.addCell(celdaParr);

        tablitaParr.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablitaParr.setWidthPercentage(50f);
        return tablitaParr;
        //
    }

    public PdfPTable t2() {
        PdfPTable tablita = new PdfPTable(16);

        Font fuente = FontFactory.getFont(FontFactory.HELVETICA, 8);
        Font fuenteSegundaTabla = FontFactory.getFont(FontFactory.HELVETICA, 8);
        Font fuentePrimeraTabla = FontFactory.getFont(FontFactory.HELVETICA, 10);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        Date date = new Date();
        PdfPCell celda;

        celda = new PdfPCell(new Phrase("Informe Semestral del Periodo(1)", fuente));
        celda.setColspan(4);
        tablita.addCell(celda);

        celda = new PdfPCell(new Phrase("Ago-Dic", fuentePrimeraTabla));
        celda.setColspan(2);
        tablita.addCell(celda);

        celda = new PdfPCell(new Phrase(ad(), fuentePrimeraTabla));
        celda.setColspan(1);
        tablita.addCell(celda);

        celda = new PdfPCell(new Phrase("Ene-Jun", fuentePrimeraTabla));
        celda.setColspan(2);
        tablita.addCell(celda);

        celda = new PdfPCell(new Phrase(ej(), fuentePrimeraTabla));
        celda.setColspan(1);
        tablita.addCell(celda);

        celda = new PdfPCell(new Phrase("Año", fuentePrimeraTabla));
        celda.setColspan(2);
        tablita.addCell(celda);

        celda = new PdfPCell(new Phrase(formatter.format(date), fuentePrimeraTabla));
        celda.setColspan(4);
        tablita.addCell(celda);

        tablita.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablita.setWidthPercentage(70f);
        return tablita;
    }

    public PdfPTable t3() {
        PdfPTable tablita2 = new PdfPTable(16);
        Font fuenteParr = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD);
        Font fuentePrimeraTabla = FontFactory.getFont(FontFactory.HELVETICA, 10);
        PdfPCell chida;

        chida = new PdfPCell(new Phrase("Actividad Cultural(2)", fuentePrimeraTabla));
        chida.setColspan(4);
        tablita2.addCell(chida);

        chida = new PdfPCell(new Phrase(cultural(), fuenteParr));
        chida.setColspan(4);
        tablita2.addCell(chida);

        chida = new PdfPCell(new Phrase("Deportiva", fuentePrimeraTabla));
        chida.setColspan(3);
        tablita2.addCell(chida);

        chida = new PdfPCell(new Phrase(deportivo(), fuenteParr));
        chida.setColspan(5);
        tablita2.addCell(chida);

        tablita2.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablita2.setWidthPercentage(70f);
        return tablita2;
    }

    public PdfPTable t4(Document document) {
        PdfPTable tablita3 = new PdfPTable(22);
        tablita3.setTotalWidth(document.getPageSize().getWidth() - 80);
        tablita3.setLockedWidth(true);
        Font fuente = FontFactory.getFont(FontFactory.HELVETICA, 8);
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
        return tablita3;
    }

    public PdfPTable t5(Document document) {
        PdfPTable tablita3 = new PdfPTable(22);
        tablita3.setTotalWidth(document.getPageSize().getWidth() - 80);
        tablita3.setLockedWidth(true);
        PdfPCell celda2;
        Font fuente = FontFactory.getFont(FontFactory.HELVETICA, 8);
        List<Evento> lista = obj.eventoCultural();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        for (int i = 0; i < lista.size(); i++) {

            celda2 = new PdfPCell(new Phrase(i + 1 + "", fuente));
            celda2.setColspan(1);
            tablita3.addCell(celda2);

            celda2 = new PdfPCell(new Phrase(lista.get(i).getNombreEvento(), fuente));
            celda2.setColspan(5);
            tablita3.addCell(celda2);

            celda2 = new PdfPCell(new Phrase(lista.get(i).getInstOrganizadora(), fuente));
            celda2.setColspan(5);
            tablita3.addCell(celda2);

            celda2 = new PdfPCell(new Phrase(interno(i), fuente));
            celda2.setColspan(1);
            tablita3.addCell(celda2);

            celda2 = new PdfPCell(new Phrase(externo(i), fuente));
            celda2.setColspan(1);
            tablita3.addCell(celda2);

            celda2 = new PdfPCell(new Phrase(formatter.format(lista.get(i).getFechaEvento()) + "", fuente));
            celda2.setColspan(2);
            tablita3.addCell(celda2);

            celda2 = new PdfPCell(new Phrase(lista.get(i).getHombres() + "", fuente));
            celda2.setColspan(1);
            tablita3.addCell(celda2);

            celda2 = new PdfPCell(new Phrase(lista.get(i).getMujeres() + "", fuente));
            celda2.setColspan(1);
            tablita3.addCell(celda2);

            celda2 = new PdfPCell(new Phrase(lista.get(i).getTotal() + "", fuente));
            celda2.setColspan(1);
            tablita3.addCell(celda2);

            celda2 = new PdfPCell(new Phrase(lista.get(i).getResultado(), fuente));
            celda2.setColspan(5);
            tablita3.addCell(celda2);

        }
        return tablita3;

    }//****

    public PdfPTable t6() {
        /////////////TABLA FECHA
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        PdfPTable tablita4 = new PdfPTable(2);
        Font fuentePrimeraTabla = FontFactory.getFont(FontFactory.HELVETICA, 10);
        PdfPCell chida3;

        chida3 = new PdfPCell(new Phrase("Metepec, México a", fuentePrimeraTabla));
        chida3.setColspan(1);
        tablita4.addCell(chida3);

        chida3 = new PdfPCell(new Phrase(formatter.format(date), fuentePrimeraTabla));
        chida3.setColspan(1);
        tablita4.addCell(chida3);

        tablita4.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tablita4.setWidthPercentage(40f);
        return tablita4;
    }

    public PdfPTable t7() {
        PdfPTable tablita5 = new PdfPTable(13);
        Font fuentePrimeraTabla = FontFactory.getFont(FontFactory.HELVETICA, 10);
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
        return tablita5;
    }

    public PdfPTable t8() {
        PdfPTable tablita6 = new PdfPTable(13);
        PdfPCell chida5;
        Font fuentePrimeraTabla = FontFactory.getFont(FontFactory.HELVETICA, 10);
        chida5 = new PdfPCell(new Phrase("Firma\n" + "Nombre:", fuentePrimeraTabla));
        chida5.setColspan(6);
        tablita6.addCell(chida5);

        chida5 = new PdfPCell(new Phrase(" ", fuentePrimeraTabla));
        chida5.setColspan(1);
        tablita6.addCell(chida5);

        chida5 = new PdfPCell(new Phrase("Firma\n" + "Nombre:", fuentePrimeraTabla));
        chida5.setColspan(6);
        tablita6.addCell(chida5);
        tablita6.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablita6.setWidthPercentage(50f);
        return tablita6;
    }

    public String interno(int i) {
        System.out.println("evento: "+ obj.eventoCultural());
        if (obj.eventoCultural().get(i).getEvento().equals("Interno")) {
            return "1";
        }
        return " ";
    }

    public String externo(int i) {
        if (obj.eventoCultural().get(i).getEvento().equals("Externo")) {
            return "1";
        }
        return " ";
    }
    
    public String cultural(){
        int i=1;
        if (obj.eventoCultural().get(i).getTipoEvento().equals("Cultural")) {
            return "X";
        }
        return " ";
    }
    
    public String deportivo(){
        int i=1;
        if (obj.eventoCultural().get(i).getTipoEvento().equals("Deportivo")) {
            return "X";
        }
        return " ";
    }

    public String ej() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM");
        Date date = new Date();
        if (formatter.format(date).equals("01")
                || formatter.format(date).equals("02")
                || formatter.format(date).equals("03")
                || formatter.format(date).equals("04")
                || formatter.format(date).equals("05")
                || formatter.format(date).equals("06")) {
            return "X";
        }
        return " ";
    }

    public String ad() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM");
        Date date = new Date();
        if (formatter.format(date).equals("07")
                || formatter.format(date).equals("08")
                || formatter.format(date).equals("09")
                || formatter.format(date).equals("10")
                || formatter.format(date).equals("11")
                || formatter.format(date).equals("12")) {
            return "X";
        }
        return " ";
    }

//    public String meses(){
//        int i =1;
//        SimpleDateFormat formatter = new SimpleDateFormat("MM");
//        
//        if (formatter.format(obj.jalarEvento().get(i).getFechaEvento()).equals(obj);) {
//            
//        }
//    }
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
