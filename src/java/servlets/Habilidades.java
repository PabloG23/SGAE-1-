/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

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
    protected void Habilidades(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        obj = (BeanUsuario) request.getSession().getAttribute("beanUsuario");

        try (OutputStream os = response.getOutputStream()) {

            Document document = new Document();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            document.open();

            int rango = 20, alumnos = obj.alumnos_preseleccionados().size();
            int f = alumnos;
            double y = alumnos / rango;
            double r = Math.ceil(y) + 1;
            int h = (int) r;

            Font fuenteParrafo1 = FontFactory.getFont(FontFactory.HELVETICA, 10);
            PdfPCell celda;

            PdfPTable tabla = new PdfPTable(20);
            tabla.setTotalWidth(document.getPageSize().getWidth() - 80);
            tabla.setLockedWidth(true);

            List<Alumno> lista = new ArrayList<Alumno>();
            lista = obj.alumnos_preseleccionados();

            if (alumnos <= rango) {
                document.add(this.addParagraph());
                document.add(this.t1(document));
                document.add(this.t2(document));
                document.add(new Paragraph(" "));
                document.add(this.t3(document));
                document.add(tabla_alumnos(document));

                while (f <= rango) {
                    document.add(new Paragraph(" "));
                    f++;
                }
                document.add(new Paragraph(" "));
                document.add(this.t4(document));
                document.add(new Paragraph(" "));
                document.add(this.t5(document));
            } else {
                int tamañolista = lista.size(), j, k = 0;
                for (int i = 0; i < h; i++) {

                    document.add(this.addParagraph());
                    document.add(this.t1(document));
                    document.add(this.t2(document));
                    document.add(new Paragraph(" "));
                    document.add(this.t3(document));

                    tamañolista = lista.size();
                    System.out.println("tamaño actual:" + tamañolista);
                    if (tamañolista > k && tamañolista <= rango) {
                        for (int l = k; l < lista.size(); l++) {
                            celda = new PdfPCell(new Phrase(l + 1 + ""));
                            celda.setColspan(1);
                            tabla.addCell(celda);

                            celda = new PdfPCell(new Phrase(lista.get(l).getApPat() + " " + lista.get(l).getApMat() + " " + lista.get(l).getNombre(), fuenteParrafo1));
                            celda.setColspan(8);
                            tabla.addCell(celda);

                            celda = new PdfPCell(new Phrase(lista.get(l).getCatCarreras().getNomCarrera(), fuenteParrafo1));
                            celda.setColspan(5);
                            tabla.addCell(celda);

                            celda = new PdfPCell(new Phrase(lista.get(l).getNoCtrl() + ""));
                            celda.setColspan(3);
                            tabla.addCell(celda);

                            celda = new PdfPCell(new Phrase(lista.get(l).getSemestre(), fuenteParrafo1));
                            celda.setColspan(3);
                            tabla.addCell(celda);

                        }
                    } else {
                        for (j = k; j < rango; j++) {
                            celda = new PdfPCell(new Phrase(j + 1 + ""));
                            celda.setColspan(1);
                            tabla.addCell(celda);

                            celda = new PdfPCell(new Phrase(lista.get(j).getApPat() + " " + lista.get(j).getApMat() + " " + lista.get(j).getNombre(), fuenteParrafo1));
                            celda.setColspan(8);
                            tabla.addCell(celda);

                            celda = new PdfPCell(new Phrase(lista.get(j).getCatCarreras().getNomCarrera(), fuenteParrafo1));
                            celda.setColspan(5);
                            tabla.addCell(celda);

                            celda = new PdfPCell(new Phrase(lista.get(j).getNoCtrl() + ""));
                            celda.setColspan(3);
                            tabla.addCell(celda);

                            celda = new PdfPCell(new Phrase(lista.get(j).getSemestre(), fuenteParrafo1));
                            celda.setColspan(3);
                            tabla.addCell(celda);
                        }
                        k = rango;
                        rango = rango + 20;
                    }
                    document.add(tabla);
                    document.add(new Paragraph(" "));
                    document.add(this.t4(document));
                    document.add(new Paragraph(" "));
                    document.add(this.t5(document));
                    document.newPage();
                    tabla.deleteBodyRows();
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

    public Paragraph addParagraph() {
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
        return titleParagraph;
    }

    public PdfPTable t1(Document document) {
        /////////////////////////// TABLA PRINCIPAL
        Date fecha = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        PdfPTable tablita = new PdfPTable(11);
        tablita.setTotalWidth(document.getPageSize().getWidth() - 80);
        tablita.setLockedWidth(true);
        Font fuente = FontFactory.getFont(FontFactory.HELVETICA, 7);

        Font fuentePrimeraTabla = FontFactory.getFont(FontFactory.HELVETICA, 10);

        PdfPCell celda;

        celda = new PdfPCell(new Phrase("Oficina de Promocion(1)", fuente));
        celda.setColspan(1);
        tablita.addCell(celda);

        celda = new PdfPCell(new Phrase("Cultural", fuentePrimeraTabla));
        celda.setColspan(1);
        tablita.addCell(celda);

        celda = new PdfPCell(new Phrase("d"));
        celda.setColspan(1);
        tablita.addCell(celda);

        celda = new PdfPCell(new Phrase("Deportiva", fuentePrimeraTabla));
        celda.setColspan(1);
        tablita.addCell(celda);

        celda = new PdfPCell(new Phrase("d", fuentePrimeraTabla));
        celda.setColspan(1);
        tablita.addCell(celda);

        celda = new PdfPCell(new Phrase("Ago-Dic", fuentePrimeraTabla));
        celda.setColspan(1);
        tablita.addCell(celda);

        celda = new PdfPCell(new Phrase("s", fuentePrimeraTabla));
        celda.setColspan(1);
        tablita.addCell(celda);

        celda = new PdfPCell(new Phrase("Ene-Jun", fuentePrimeraTabla));
        celda.setColspan(1);
        tablita.addCell(celda);

        celda = new PdfPCell(new Phrase("w", fuentePrimeraTabla));
        celda.setColspan(1);
        tablita.addCell(celda);

        celda = new PdfPCell(new Phrase("Año", fuentePrimeraTabla));
        celda.setColspan(1);
        tablita.addCell(celda);

        celda = new PdfPCell(new Phrase(formatter.format(fecha), fuentePrimeraTabla));
        celda.setColspan(1);
        tablita.addCell(celda);
        return tablita;
    }

    public PdfPTable t2(Document document) {
        Font fuentePrimeraTabla = FontFactory.getFont(FontFactory.HELVETICA, 10);
        Font fuenteParrafo = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD);
        PdfPTable tablita2 = new PdfPTable(11);
        tablita2.setTotalWidth(document.getPageSize().getWidth() - 80);
        tablita2.setLockedWidth(true);
        PdfPCell chida;

        chida = new PdfPCell(new Phrase("Actividad(2)", fuentePrimeraTabla));
        chida.setColspan(2);
        tablita2.addCell(chida);
        int i = 1;
        chida = new PdfPCell(new Phrase(obj.alumnos_preseleccionados().get(i).getGrupo().getActividad().getCatActividad().getTipo(), fuentePrimeraTabla));
        chida.setColspan(9);
        tablita2.addCell(chida);

        tablita2.setWidthPercentage(100f);
        return tablita2;
    }

    public PdfPTable t3(Document document) {
        Font fuenteSegundaTabla = FontFactory.getFont(FontFactory.TIMES_ROMAN, 9);
        PdfPTable tablita3 = new PdfPTable(20);
        tablita3.setTotalWidth(document.getPageSize().getWidth() - 80);
        tablita3.setLockedWidth(true);

        PdfPCell chida1;

        chida1 = new PdfPCell(new Phrase("No.", fuenteSegundaTabla));
        chida1.setColspan(1);
        tablita3.addCell(chida1);

        chida1 = new PdfPCell(new Phrase("NOMBRE ALUMNO(3)", fuenteSegundaTabla));
        chida1.setColspan(8);
        tablita3.addCell(chida1);

        chida1 = new PdfPCell(new Phrase("CARRERA(4)", fuenteSegundaTabla));
        chida1.setColspan(5);
        tablita3.addCell(chida1);

        chida1 = new PdfPCell(new Phrase("CONTROL(5)", fuenteSegundaTabla));
        chida1.setColspan(3);
        tablita3.addCell(chida1);

        chida1 = new PdfPCell(new Phrase("SEMESTRE(6)", fuenteSegundaTabla));
        chida1.setColspan(3);
        tablita3.addCell(chida1);
        return tablita3;
    }

    public PdfPTable tabla_alumnos(Document document) {
        Font fuenteParrafo1 = FontFactory.getFont(FontFactory.HELVETICA, 10);
        PdfPCell celda;

        PdfPTable tabla = new PdfPTable(20);
        tabla.setTotalWidth(document.getPageSize().getWidth() - 80);
        tabla.setLockedWidth(true);

        List<Alumno> lista = obj.alumnos_preseleccionados();

        for (int i = 0; i < lista.size(); i++) {

            celda = new PdfPCell(new Phrase(i + 1 + ""));
            celda.setColspan(1);
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

            celda = new PdfPCell(new Phrase(lista.get(i).getSemestre(), fuenteParrafo1));
            celda.setColspan(3);
            tabla.addCell(celda);
        }
        return tabla;
    }

    public PdfPTable t4(Document document) {
        /////////////TABLA FECHA
        Date fecha = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Font fuentePrimeraTabla = FontFactory.getFont(FontFactory.HELVETICA, 10);

        PdfPTable tablita4 = new PdfPTable(2);
//            tablita4.setTotalWidth(document.getPageSize().getWidth() - 80);
//            tablita4.setLockedWidth(true);

        PdfPCell chida2;

        chida2 = new PdfPCell(new Phrase("Metepec, México a", fuentePrimeraTabla));
        chida2.setColspan(1);
        tablita4.addCell(chida2);

        chida2 = new PdfPCell(new Phrase(formatter.format(fecha), fuentePrimeraTabla));
        chida2.setColspan(1);
        tablita4.addCell(chida2);

        tablita4.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tablita4.setWidthPercentage(40f);
        return tablita4;
    }

    public PdfPTable t5(Document document) {
        ///////////////////// TABLA ULTIMA
        Font fuentePrimeraTabla = FontFactory.getFont(FontFactory.HELVETICA, 10);

        PdfPTable tablita5 = new PdfPTable(17);
        tablita5.setTotalWidth(document.getPageSize().getWidth() - 80);
        tablita5.setLockedWidth(true);

        PdfPCell chida3;

        chida3 = new PdfPCell(new Phrase("Elaboró \n" + "Promotor/a", fuentePrimeraTabla));
        chida3.setColspan(5);
        tablita5.addCell(chida3);

        int i = 1;
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

        chida4 = new PdfPCell(new Phrase("Firma \n\n" + "Nombre:" + "Profr." + obj.alumnos_preseleccionados().get(i).getGrupo().getPromotor().getApePat() + " " + obj.alumnos_reconocidos().get(i).getGrupo().getPromotor().getApeMat() + " " + obj.alumnos_reconocidos().get(i).getGrupo().getPromotor().getNombre(), fuentePrimeraTabla));
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
        return tablita5;
    }

    public String cultural() {
        int i = 1;
        if (obj.alumnos_preseleccionados().get(i).getGrupo().getActividad().getCatActividad().getTipo().equals("Cultural")) {
            System.out.println("********" + obj.alumnos_preseleccionados().get(i).getGrupo().getActividad().getCatActividad().getTipo());
            return "X";
        }
        return " ";
    }

    public String deportivo() {
        int i = 1;
        if (obj.alumnos_preseleccionados().get(i).getGrupo().getActividad().getCatActividad().getTipo().equals("Deportivo")) {
            System.out.println("********" + obj.alumnos_preseleccionados().get(i).getGrupo().getActividad().getCatActividad().getTipo());
            return "X";
        }
        return " ";
    }

    public String ej() {
        int i = 1;
        if (obj.alumnos_preseleccionados().get(i).getGrupo().getActividad().getSemestre().equals("Enero-Junio")) {
            return "X";
        }
        return " ";
    }

    public String ad() {
        int i = 1;
        if (obj.alumnos_preseleccionados().get(i).getGrupo().getActividad().getSemestre().equals("Agosto-Diciembre")) {
            return "X";
        }
        return " ";
    }

    public BeanUsuario getObj() {
        return obj;
    }

    public void setObj(BeanUsuario obj) {
        this.obj = obj;
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
