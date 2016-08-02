package servlets;

import Modelo.BeanUsuario;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
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

@WebServlet(name = "Acreditados", urlPatterns = {"/Acreditados"})
public class Acreditados extends HttpServlet {

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
    protected void Acreditados(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        obj = (BeanUsuario) request.getSession().getAttribute("beanUsuario");
        try (OutputStream os = response.getOutputStream()) {

            Document document = new Document();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            document.open();

/////////////////////////// TABLA PRINCIPAL        
            int rango = 3, alumnos = obj.alumno_grupo().size();
            int f = alumnos;
            double y = alumnos / rango;
            double r = Math.ceil(y) + 1;
            int h = (int) r;
            PdfPTable tablabuena = new PdfPTable(15);//numero de columnas
            PdfPCell celdabuena;

            List<Alumno> lista = new ArrayList<Alumno>();
            lista = obj.alumno_grupo();
            System.out.println("lista: " + lista.size());
            if (alumnos <= rango) {

                document.add(this.t1(document));
                document.add(new Paragraph(" "));
                document.add(this.t2(document));
                document.add(this.t3(document));
                document.add(new Paragraph(" "));
                document.add(this.t4(document));
                document.add(this.t5(document));//**
                while (f <= rango) {
                    document.add(new Paragraph(" "));
                    f++;
                }
                document.add(new Paragraph(" "));
                document.add(this.t6(document));
                document.add(new Paragraph(" "));
                document.add(this.t7(document));

            } else {
                //aqui empeze
                int tamañolista = lista.size(), j, k = 0;

                for (int i = 0; i < h; i++) {
                    document.add(this.t1(document));
                    document.add(new Paragraph(" "));
                    document.add(this.t2(document));
                    document.add(this.t3(document));
                    document.add(new Paragraph(" "));
                    document.add(this.t4(document));

                    tamañolista = lista.size();
                    System.out.println("tamaño actual:" + tamañolista);
                    ///cuando ya quedan menos entidades que rango
                    if (tamañolista > k && tamañolista <= rango) {
                        for (int l = k; l < lista.size(); l++) {
                            celdabuena = new PdfPCell(new Phrase(l + 1 + ""));
                            celdabuena.setColspan(1);
                            tablabuena.addCell(celdabuena);

                            celdabuena = new PdfPCell(new Phrase(lista.get(l).getApPat() + " " + lista.get(l).getApMat() + " " + lista.get(l).getNombre()));
                            celdabuena.setColspan(6);
                            tablabuena.addCell(celdabuena);

                            celdabuena = new PdfPCell(new Phrase(lista.get(l).getNoCtrl()));
                            celdabuena.setColspan(2);
                            tablabuena.addCell(celdabuena);

                            celdabuena = new PdfPCell(new Phrase(lista.get(l).getCatCarreras().getNomCarrera()));
                            celdabuena.setColspan(3);
                            tablabuena.addCell(celdabuena);

                            celdabuena = new PdfPCell(new Phrase(lista.get(l).getSemestre()));
                            celdabuena.setColspan(1);
                            tablabuena.addCell(celdabuena);

                            celdabuena = new PdfPCell(new Phrase(a()));
                            celdabuena.setColspan(1);
                            tablabuena.addCell(celdabuena);

                            celdabuena = new PdfPCell(new Phrase(na()));
                            celdabuena.setColspan(1);
                            tablabuena.addCell(celdabuena);
                            System.out.println("alumno: " + lista.get(l).getNombre());
                        }
                    } ///cuando hay mas entidades que rango
                    else {
                        for (j = k; j < rango; j++) {
                            document.add(new Paragraph(lista.get(j).getNombre()));
                            System.out.println("alumno: " + lista.get(j).getNombre());
                        }
                        k = rango;
                        rango = rango + 3;
                    }
                    document.add(tablabuena);
                    document.add(new Paragraph(" "));
                    document.add(this.t6(document));
                    document.add(new Paragraph(" "));
                    document.add(this.t7(document));
                    document.newPage();
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

    public PdfPTable t1(Document document) {
        // PARRAFO

        String titulo = "InstitutoTecnológico de Toluca\n"
                + "Subdirección de Planeación y Vinculación\n"
                + "Departamento de Actividades Extraescolares\n"
                + "Cédula de Resultados de Alumnos Inscritos en Actividades Complementarias y Representativos Culturales y/o Deportivas";
        Font fuenteParr = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD);
        Chunk chunk = new Chunk(titulo + "\n\n", fuenteParr);
        Paragraph parrafo = new Paragraph(chunk);
        parrafo.setAlignment(Element.ALIGN_CENTER);
        parrafo.setLeading(12f);

        PdfPTable tablitaParr = new PdfPTable(11);
        tablitaParr.setTotalWidth(document.getPageSize().getWidth() - 80);
        tablitaParr.setLockedWidth(true);
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
        return tablitaParr;
    }

    public PdfPTable t2(Document document) {
        Date fecha = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        PdfPTable tablita = new PdfPTable(11);
        tablita.setTotalWidth(document.getPageSize().getWidth() - 80);
        tablita.setLockedWidth(true);
        Font fuente = FontFactory.getFont(FontFactory.HELVETICA, 7);

        Font fuentePrimeraTabla = FontFactory.getFont(FontFactory.HELVETICA, 10);
        Font fuenteParr = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD);
        PdfPCell celda;

        celda = new PdfPCell(new Phrase("Oficina de Promocion(1)", fuente));
        celda.setColspan(1);
        tablita.addCell(celda);

        celda = new PdfPCell(new Phrase("Cultural", fuentePrimeraTabla));
        celda.setColspan(1);
        tablita.addCell(celda);

        celda = new PdfPCell(new Phrase(cultural()));
        celda.setColspan(1);
        tablita.addCell(celda);

        celda = new PdfPCell(new Phrase("Deportiva", fuentePrimeraTabla));
        celda.setColspan(1);
        tablita.addCell(celda);

        celda = new PdfPCell(new Phrase(deportivo()));
        celda.setColspan(1);
        tablita.addCell(celda);

        celda = new PdfPCell(new Phrase("Ago-Dic", fuentePrimeraTabla));
        celda.setColspan(1);
        tablita.addCell(celda);

        celda = new PdfPCell(new Phrase(ad()));
        celda.setColspan(1);
        tablita.addCell(celda);

        celda = new PdfPCell(new Phrase("Ene-Jun", fuentePrimeraTabla));
        celda.setColspan(1);
        tablita.addCell(celda);

        celda = new PdfPCell(new Phrase(ej()));
        celda.setColspan(1);
        tablita.addCell(celda);

        celda = new PdfPCell(new Phrase("Año", fuentePrimeraTabla));
        celda.setColspan(1);
        tablita.addCell(celda);

        celda = new PdfPCell(new Phrase(formatter.format(fecha)));
        celda.setColspan(1);
        tablita.addCell(celda);
        return tablita;
    }

    public PdfPTable t3(Document document) {
        PdfPTable tablita2 = new PdfPTable(11);
        tablita2.setTotalWidth(document.getPageSize().getWidth() - 80);
        tablita2.setLockedWidth(true);
        Font fuentePrimeraTabla = FontFactory.getFont(FontFactory.HELVETICA, 10);
        Font fuenteParr = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD);
        PdfPCell chida;

        chida = new PdfPCell(new Phrase("Actividad(2)", fuentePrimeraTabla));
        chida.setColspan(2);
        tablita2.addCell(chida);
        int i = 1;
        chida = new PdfPCell(new Phrase(obj.alumno_grupo().get(i).getGrupo().getActividad().getCatActividad().getNombre() + "," + obj.alumno_grupo().get(i).getGrupo().getDias() + " " + obj.alumno_grupo().get(i).getGrupo().getHorario(), fuenteParr));
        chida.setColspan(9);
        tablita2.addCell(chida);

        tablita2.setWidthPercentage(100f);
        return tablita2;
    }

    public PdfPTable t4(Document document) {
        ////////////////////////TABLA PRINCIPAL
        PdfPTable tablita3 = new PdfPTable(15);
        tablita3.setTotalWidth(document.getPageSize().getWidth() - 80);
        tablita3.setLockedWidth(true);
        Font fuenteSegundaTabla = FontFactory.getFont(FontFactory.HELVETICA, 8);
        PdfPCell chida1;

        chida1 = new PdfPCell(new Phrase("No.", fuenteSegundaTabla));
        chida1.setColspan(1);
        tablita3.addCell(chida1);

        chida1 = new PdfPCell(new Phrase("Nombre\n" + "(3)", fuenteSegundaTabla));
        chida1.setColspan(6);
        tablita3.addCell(chida1);

        chida1 = new PdfPCell(new Phrase("No. Control\n" + " (4)", fuenteSegundaTabla));
        chida1.setColspan(2);
        tablita3.addCell(chida1);

        chida1 = new PdfPCell(new Phrase("Carrera \n" + "(5)", fuenteSegundaTabla));
        chida1.setColspan(3);
        tablita3.addCell(chida1);

        chida1 = new PdfPCell(new Phrase("Semestre \n" + "(6)", fuenteSegundaTabla));
        chida1.setColspan(1);
        tablita3.addCell(chida1);

        chida1 = new PdfPCell(new Phrase("A \n" + "(7)", fuenteSegundaTabla));
        chida1.setColspan(1);
        tablita3.addCell(chida1);

        chida1 = new PdfPCell(new Phrase("NA \n" + "(8)", fuenteSegundaTabla));
        chida1.setColspan(1);
        tablita3.addCell(chida1);

        tablita3.addCell(chida1);

        return tablita3;
    }

    public PdfPTable t5(Document document) {
        PdfPTable tablabuena = new PdfPTable(15);//numero de columnas
        tablabuena.setTotalWidth(document.getPageSize().getWidth() - 80);
        tablabuena.setLockedWidth(true);
        PdfPCell celdabuena;
        List<Alumno> lista = obj.alumno_grupo();

        for (int i = 0; i < lista.size(); i++) {

            celdabuena = new PdfPCell(new Phrase(i + 1 + ""));
            celdabuena.setColspan(1);
            tablabuena.addCell(celdabuena);

            celdabuena = new PdfPCell(new Phrase(obj.alumno_grupo().get(i).getApPat() + " " + obj.alumno_grupo().get(i).getApMat() + " " + obj.alumno_grupo().get(i).getNombre()));
            celdabuena.setColspan(6);
            tablabuena.addCell(celdabuena);

            celdabuena = new PdfPCell(new Phrase(obj.alumno_grupo().get(i).getNoCtrl()));
            celdabuena.setColspan(2);
            tablabuena.addCell(celdabuena);

            celdabuena = new PdfPCell(new Phrase(obj.alumno_grupo().get(i).getCatCarreras().getNomCarrera()));
            celdabuena.setColspan(3);
            tablabuena.addCell(celdabuena);

            celdabuena = new PdfPCell(new Phrase(obj.alumno_grupo().get(i).getSemestre()));
            celdabuena.setColspan(1);
            tablabuena.addCell(celdabuena);

            celdabuena = new PdfPCell(new Phrase(a()));
            celdabuena.setColspan(1);
            tablabuena.addCell(celdabuena);

            celdabuena = new PdfPCell(new Phrase(na()));
            celdabuena.setColspan(1);
            tablabuena.addCell(celdabuena);
        }
        return tablabuena;
    }//buena

    public PdfPTable t6(Document document) {
        /////////////TABLA FECHA
        PdfPTable tablita4 = new PdfPTable(2);
        Date fecha = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Font fuentePrimeraTabla = FontFactory.getFont(FontFactory.HELVETICA, 10);

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

    public PdfPTable t7(Document document) {
        ///////////////////// TABLA ULTIMA
        PdfPTable tablita5 = new PdfPTable(17);
        tablita5.setTotalWidth(document.getPageSize().getWidth() - 80);
        tablita5.setLockedWidth(true);
        Font fuentePrimeraTabla = FontFactory.getFont(FontFactory.HELVETICA, 10);
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
        int i = 1;
        chida4 = new PdfPCell(new Phrase("Firma \n\n" + "Nombre:" + " " + obj.alumno_grupo().get(i).getGrupo().getPromotor().getApePat() + " " + obj.alumno_grupo().get(i).getGrupo().getPromotor().getApeMat() + " " + obj.alumno_grupo().get(i).getGrupo().getPromotor().getNombre(), fuentePrimeraTabla));
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

    public String a() {
        int i = 1;
        if (obj.alumno_grupo().get(i).isAcreditado() == true) {
            return "1";
        }
        return " ";
    }

    public String na() {
        int i = 1;
        if (obj.alumno_grupo().get(i).isNoAcreditado() == true) {
            return "1";
        }
        return " ";
    }

    public String cultural() {
        int i = 1;
        if (obj.alumno_grupo().get(i).getGrupo().getActividad().getCatActividad().getTipo().equals("Cultural")) {
            System.out.println("********" + obj.alumno_grupo().get(i).getGrupo().getActividad().getCatActividad().getTipo());
            return "X";
        }
        return " ";
    }

    public String deportivo() {
        int i = 1;
        if (obj.alumno_grupo().get(i).getGrupo().getActividad().getCatActividad().getTipo().equals("Deportivo")) {
            System.out.println("********" + obj.alumno_grupo().get(i).getGrupo().getActividad().getCatActividad().getTipo());
            return "X";
        }
        return " ";
    }

    public String ej() {
        int i = 1;
        if (obj.alumno_grupo().get(i).getGrupo().getActividad().getSemestre().equals("Enero-Junio")) {
            return "X";
        }
        return " ";
    }

    public String ad() {
        int i = 1;
        if (obj.alumno_grupo().get(i).getGrupo().getActividad().getSemestre().equals("Agosto-Diciembre")) {
            return "X";
        }
        return " ";
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
        Acreditados(request, response);
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
        Acreditados(request, response);
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
