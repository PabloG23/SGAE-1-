package servlets;

import Modelo.BeanAlumno;
import Modelo.BeanUsuario;
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
import entities.Alumno;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Reconocimiento", urlPatterns = {"/Reconocimiento"})
public class Reconocimiento extends HttpServlet {

//    @ManagedProperty(value = "#{beanUsuario}")
    private BeanUsuario obj;

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

        obj = (BeanUsuario) request.getSession().getAttribute("beanUsuario");

        try (OutputStream os = response.getOutputStream()) {

            Document document = new Document();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            document.open();

            int rango = 25, alumnos = obj.alumno_grupo().size();
            int f = alumnos;
            double y = alumnos / rango;
            double r = Math.ceil(y) + 1;
            int h = (int) r;

            Font fuenteParrafo1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
            PdfPCell celda;

            PdfPTable tabla = new PdfPTable(20);
            tabla.setTotalWidth(document.getPageSize().getWidth() - 80);
            tabla.setLockedWidth(true);

            List<Alumno> lista = new ArrayList<Alumno>();
            lista = obj.alumno_grupo();

            if (alumnos < rango) {
                document.add(this.addParagraph());
                document.add(new Paragraph(" "));
                document.add(this.tabloide(document));
                document.add(this.tabla_alumnos(document));

                while (f < rango) {
                    document.add(new Paragraph(" "));
                    f++;
                }
                document.add(new Paragraph(" "));
                document.add(this.tablaFecha());
                document.add(new Paragraph(" "));
                document.add(this.tablaFirmas());
                document.add(this.tablaUltima());

            } else {
                int p = 0;
                while (p < h) {
                    document.add(this.addParagraph());
                    document.add(new Paragraph(" "));
                    document.add(this.tabloide(document));

                    for (int i = 0; i < rango; i++) {

                        celda = new PdfPCell(new Phrase(i + 1 + ""));
                        celda.setColspan(1);
                        tabla.addCell(celda);

                        celda = new PdfPCell(new Phrase(lista.get(i).getGrupo().getActividad().getCatActividad().getNombre(), fuenteParrafo1));
                        celda.setColspan(3);
                        tabla.addCell(celda);

                        celda = new PdfPCell(new Phrase(lista.get(i).getApPat() + " " + lista.get(i).getApMat() + " " + lista.get(i).getNombre(), fuenteParrafo1));
                        celda.setColspan(8);
                        tabla.addCell(celda);

                        celda = new PdfPCell(new Phrase(lista.get(i).getCatCarreras().getNomCarrera(), fuenteParrafo1));
                        celda.setColspan(5);
                        tabla.addCell(celda);

                        celda = new PdfPCell(new Phrase(lista.get(i).getNoCtrl() + ""));
                        celda.setColspan(3);
                        tabla.addCell(celda);
                        lista.remove(i);
                    }

                    document.add(new Paragraph(" "));
                    document.add(this.tablaFecha());
                    document.add(new Paragraph(" "));
                    document.add(this.tablaFirmas());
                    document.add(this.tablaUltima());
                    p++;

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

    public PdfPTable tabla_alumnos(Document document) {
        Font fuenteParrafo1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
        PdfPCell celda;

        PdfPTable tabla = new PdfPTable(20);
        tabla.setTotalWidth(document.getPageSize().getWidth() - 80);
        tabla.setLockedWidth(true);

        List<Alumno> lista = obj.alumno_grupo();

        for (int i = 0; i < lista.size(); i++) {

            celda = new PdfPCell(new Phrase(i + 1 + ""));
            celda.setColspan(1);
            tabla.addCell(celda);

            celda = new PdfPCell(new Phrase(lista.get(i).getGrupo().getActividad().getCatActividad().getNombre(), fuenteParrafo1));
            celda.setColspan(3);
            tabla.addCell(celda);

            celda = new PdfPCell(new Phrase(lista.get(i).getApPat() + " " + lista.get(i).getApMat() + " " + lista.get(i).getNombre(), fuenteParrafo1));
            celda.setColspan(8);
            tabla.addCell(celda);

            celda = new PdfPCell(new Phrase(lista.get(i).getCatCarreras().getNomCarrera(), fuenteParrafo1));
            celda.setColspan(5);
            tabla.addCell(celda);

            celda = new PdfPCell(new Phrase(lista.get(i).getNoCtrl() + ""));
            celda.setColspan(3);
            tabla.addCell(celda);
        }
        return tabla;
    }

    private Paragraph addParagraph() {
        // PARRAFO
        int i = 1;
        String title = "SUBDIRECCIÓN DE PLANEACIÓN Y VINCULACIÓN\n"
                + "DEPARTAMENTO DE ACTIVIDADES EXTRAESCOLARES\n"
                + "REGISTRO DE ALUMNOS DE LAS ACTIVIDADES COMPLEMENTARIAS Y REPRESENTATIVOS\n"
                + "CULTURALES Y/O DEPORTIVA PARA RECIBIR  RECONOCIMIENTO POR SU DESTACADA\n"
                + "PARTICIPACIÓN\n"
                + "PERIODO" + " " + obj.alumno_grupo().get(i).getGrupo().getActividad().getSemestre() + " " + obj.alumno_grupo().get(i).getGrupo().getActividad().getAño();
        Font fuenteParrafo1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
        Chunk chunk = new Chunk(title + "\n\n", fuenteParrafo1);
        Paragraph titleParagraph = new Paragraph(chunk);
        titleParagraph.setAlignment(Element.ALIGN_CENTER);
        titleParagraph.setExtraParagraphSpace(50);
        return titleParagraph;

    }

    private PdfPTable tablaFecha() {
        Date fecha = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        System.out.println(formatter.format(fecha));
        PdfPTable tablita4 = new PdfPTable(3);

        PdfPCell chida2;
        Font fuenteParrafo1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);

        chida2 = new PdfPCell(new Phrase("Metepec, México a"));
        chida2.setColspan(2);
        tablita4.addCell(chida2);

        chida2 = new PdfPCell(new Phrase(formatter.format(fecha), fuenteParrafo1));
        chida2.setColspan(1);
        tablita4.addCell(chida2);

        tablita4.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tablita4.setWidthPercentage(40f);
        return tablita4;
    }

    public PdfPTable tablaFirmas() {

        PdfPTable tablita6 = new PdfPTable(13);
        Font fuenteParrafo1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
        PdfPCell chida5;

        chida5 = new PdfPCell(new Phrase("Firma\n" + "Nombre:", fuenteParrafo1));
        chida5.setColspan(6);
        tablita6.addCell(chida5);

        chida5 = new PdfPCell(new Phrase(" ", fuenteParrafo1));
        chida5.setColspan(1);
        tablita6.addCell(chida5);

        chida5 = new PdfPCell(new Phrase("Firma\n\n\n" + "Nombre:M. en C.I.Fernando Sanchez Solis", fuenteParrafo1));
        chida5.setColspan(6);
        tablita6.addCell(chida5);
        tablita6.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablita6.setWidthPercentage(100f);
        return tablita6;
    }

    private PdfPTable tablaUltima() {
        PdfPTable tablita5 = new PdfPTable(13);
        Font fuenteParrafo1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
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
        return tablita5;
    }

    private PdfPTable tabloide(Document document) {
        Font fuenteParrafo1 = FontFactory.getFont(FontFactory.HELVETICA, 10);

        ////////////////////////TABLA PRINCIPAL
        PdfPTable tablita3 = new PdfPTable(20);
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
        chida1.setColspan(8);
        tablita3.addCell(chida1);

        chida1 = new PdfPCell(new Phrase("CARRERA", fuenteParrafo1));
        chida1.setColspan(5);
        tablita3.addCell(chida1);

        chida1 = new PdfPCell(new Phrase("CONTROL", fuenteParrafo1));
        chida1.setColspan(3);
        tablita3.addCell(chida1);
        return tablita3;
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
