/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import entities.Actividad;
import entities.Alumno;
import entities.CatCarreras;
import facade.ActividadFacade;
import facade.AlumnoFacade;
import facade.CatCarrerasFacade;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author omar
 */
@ManagedBean
public class ChartView implements Serializable {

    private Alumno entidadalumno = new Alumno();
    private Actividad entidadactividad = new Actividad();
    private CatCarreras entidadcarrera = new CatCarreras();
    private int Totalhombres = 0, totalactividad, totaltablaactida,totalcarrera;
    private int Totalmujeres = 0;

    @Inject
    private AlumnoFacade facadealumno;
    @Inject
    private ActividadFacade facadeactividad;
    @Inject
    private CatCarrerasFacade facadecarrera;

    private BarChartModel barModel, barModel2;

    @PostConstruct
    public void init() {
        createBarModels();
    }

    private void createBarModels() {
        createBarModel();
        createBarModel2();

    }

//////PRIMERA GRAFICA//////
    public BarChartModel getBarModel() {
        return barModel;
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();

        List<Alumno> objalumno = facadealumno.findAll();
        List<Alumno> aludeportivo = new ArrayList<Alumno>();
        List<Alumno> alucultural = new ArrayList<Alumno>();

        List<Actividad> objactivdad = facadeactividad.findAll();
        List<Actividad> actdeportiva = new ArrayList<Actividad>();
        List<Actividad> actcultural = new ArrayList<Actividad>();
        int idalumno, idactividad;
        ///for para separar alumnos deportivo de los cultural
        for (int i = 0; i < objalumno.size(); i++) {
            if (objalumno.get(i).getGrupo().getActividad().getCatActividad().getTipo().equals("Deportivo")) {
                idalumno = objalumno.get(i).getNoCtrl();
                entidadalumno = facadealumno.find(idalumno);
                aludeportivo.add(entidadalumno);
            } else {
                if (objalumno.get(i).getGrupo().getActividad().getCatActividad().getTipo().equals("Cultural")) {
                    idalumno = objalumno.get(i).getNoCtrl();
                    entidadalumno = facadealumno.find(idalumno);
                    alucultural.add(entidadalumno);
                }
            }
        }
//        System.out.println("Lista ALUMNO DEPORTIVO: " + aludeportivo);
//        System.out.println("Lista ALUMNO CULTURAL: " + alucultural);
        ///FIN del for para separar alumnos deportivo de los cultural

        ///for para separar actividades deportivas de las cultural
        for (int i = 0; i < objactivdad.size(); i++) {

            if (objactivdad.get(i).getCatActividad().getTipo().equals("Deportivo")) {
                idactividad = objactivdad.get(i).getIdactividad();
                entidadactividad = facadeactividad.find(idactividad);
                actdeportiva.add(entidadactividad);
            } else {
                if (objactivdad.get(i).getCatActividad().getTipo().equals("Cultural")) {
                    idactividad = objactivdad.get(i).getIdactividad();
                    entidadactividad = facadeactividad.find(idactividad);
                    actcultural.add(entidadactividad);
                }
            }
        }
//        System.out.println("Actividad deportiva: " + actdeportiva);
//        System.out.println("Actividad Cultural: " + actcultural);

        ///FIN for para separar actividades deportivas de las cultural
        ///for para contar alumnos de actividades deportivas 
        int idactividadtabla, idactividadtablaalumno, aux = 0, Masculino = 0, Femenino = 0;

        ChartSeries hombres = new ChartSeries();
        hombres.setLabel("Hombres");
        ChartSeries mujeres = new ChartSeries();
        mujeres.setLabel("Mujeres");
        for (int i = 0; i < actdeportiva.size(); i++) {
            idactividadtabla = actdeportiva.get(i).getIdactividad();
            aux = 0;
            Masculino = 0;
            Femenino = 0;
            totalactividad = 0;
            //System.out.println("id:.." + idactividadtabla);
            for (int j = 0; j < aludeportivo.size(); j++) {
                idactividadtablaalumno = aludeportivo.get(j).getGrupo().getActividad().getIdactividad();
                if (idactividadtabla == idactividadtablaalumno) {
                    aux = aux + 1;
                    if (aludeportivo.get(j).getSexo().equals("M")) {
                        Masculino = Masculino + 1;
                    } else {
                        if (aludeportivo.get(j).getSexo().equals("F")) {
                            Femenino = Femenino + 1;
                        }
                    }
                }

            }
            setTotalhombres(getTotalhombres() + Masculino);
            setTotalmujeres(getTotalmujeres() + Femenino);
            totalactividad = totalactividad + Masculino + Femenino;
            totaltablaactida = Totalhombres + Totalmujeres;
//            System.out.println("Alumnos: " + aux);
//            System.out.println("Hombres: " + Masculino);
//            System.out.println("Mujeres: " + Femenino);

            hombres.set(actdeportiva.get(i).getCatActividad().getNombre(), Masculino);
            mujeres.set(actdeportiva.get(i).getCatActividad().getNombre(), Femenino);
            crearactividad(actdeportiva.get(i).getCatActividad().getNombre(), Masculino, Femenino, totalactividad);

        }
        model.addSeries(hombres);
        model.addSeries(mujeres);

        ///FIN for para contar alumnos de actividades deportivas 
        return model;
    }
    //*********Crear tabla Actividad
    List<tablasgraact> act = new ArrayList<>();

    public List<tablasgraact> crearactividad(String Actividades, int H, int M, int totalactividad) {
        tablasgraact obj = new tablasgraact(Actividades, H, M, totalactividad);
        act.add(obj);
        //System.out.println("Lista tabla actvidad: " + act);
        return act;

    }

    //*********FIN  tabla Actividad
    private void createBarModel() {
        barModel = initBarModel();

        barModel.setTitle("Hombres y Mujeres por Actividad");
        barModel.setLegendPosition("ne");

        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Actividades");

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Tolal");
        yAxis.setMin(0);
        yAxis.setMax(50);

    }
//////FIN PRIMERA GRAFICA////////////////////////////////////////////////////////

//////SEGUNDA GRAFICA////////////////////////////////////////////////////////////    
    public BarChartModel getBarModel2() {
        return barModel2;
    }

    private BarChartModel initBarModel2() {
        BarChartModel model = new BarChartModel();
        List<Alumno> objalumno = facadealumno.findAll();
        List<Alumno> aludeportivo = new ArrayList<Alumno>();
        List<Alumno> alucultural = new ArrayList<Alumno>();

        List<CatCarreras> objcarrera = facadecarrera.findAll();
        int idalumno;
        ///for para separar alumnos deportivo de los cultural
        for (int i = 0; i < objalumno.size(); i++) {
            if (objalumno.get(i).getGrupo().getActividad().getCatActividad().getTipo().equals("Deportivo")) {
                idalumno = objalumno.get(i).getNoCtrl();
                entidadalumno = facadealumno.find(idalumno);
                aludeportivo.add(entidadalumno);
            } else {
                if (objalumno.get(i).getGrupo().getActividad().getCatActividad().getTipo().equals("Cultural")) {
                    idalumno = objalumno.get(i).getNoCtrl();
                    entidadalumno = facadealumno.find(idalumno);
                    alucultural.add(entidadalumno);
                }
            }
        }
//        System.out.println("Lista ALUMNO DEPORTIVO: " + aludeportivo);
//        System.out.println("Lista ALUMNO CULTURAL: " + alucultural);
        ///FIN del for para separar alumnos deportivo de los cultural

        ///for para contar alumnos de carreras deportivas 
        int idcarrera, idcarreratablaalumno, aux = 0, Mas = 0, Fem = 0;

        ChartSeries hom = new ChartSeries();
        hom.setLabel("Hombres");
        ChartSeries muj = new ChartSeries();
        muj.setLabel("Mujeres");
        for (int i = 0; i < objcarrera.size(); i++) {
            idcarrera = objcarrera.get(i).getIdCatCarreras();
            aux = 0;
            Mas = 0;
            Fem = 0;
            totalcarrera=0;
            //System.out.println("idCarrera: " + idcarrera + " " + objcarrera.get(i).getNomCarrera());
            for (int j = 0; j < aludeportivo.size(); j++) {
                idcarreratablaalumno = aludeportivo.get(j).getCatCarreras().getIdCatCarreras();
                if (idcarrera == idcarreratablaalumno) {
                    aux = aux + 1;
                    if (aludeportivo.get(j).getSexo().equals("M")) {
                        Mas = Mas + 1;
                    } else {
                        if (aludeportivo.get(j).getSexo().equals("F")) {
                            Fem = Fem + 1;
                        }
                    }
                }
            }
            
//            System.out.println("Alumnos: " + aux);
//            System.out.println("Hombres: " + Mas);
//            System.out.println("Mujeres: " + Fem);
            totalcarrera=totalcarrera+Mas+Fem;
            hom.set(objcarrera.get(i).getNomCarrera(), Mas);
            muj.set(objcarrera.get(i).getNomCarrera(), Fem);
            crearcarrera(objcarrera.get(i).getNomCarrera(), Mas, Fem,totalcarrera);

        }
        model.addSeries(hom);
        model.addSeries(muj);

        ///FIN for para contar alumnos de carreras deportivas 
        return model;
    }

    //*********Crear tabla Carrera
    List<tablasgracarr> carrera = new ArrayList<>();

    public List<tablasgracarr> crearcarrera(String Carrera, int H, int M, int totalcarrera) {
        tablasgracarr obj = new tablasgracarr(Carrera, H, M,totalcarrera);
        carrera.add(obj);
        //System.out.println("Lista tabla carrera: " + carrera);
        return carrera;

    }

    //*********FIN  tabla Carrera
    private void createBarModel2() {
        barModel2 = initBarModel2();

        barModel2.setTitle("Hombres y Mujeres por Carrera");
        barModel2.setLegendPosition("ne");

        Axis xAxis = barModel2.getAxis(AxisType.X);
        xAxis.setLabel("Carreras");

        Axis yAxis = barModel2.getAxis(AxisType.Y);
        yAxis.setLabel("Tolal");
        yAxis.setMin(0);
        yAxis.setMax(50);

    }
//////FIN SEGUNDA GRAFICA///////////////////////////////////////////////////////    

    /**
     * @return the entidadalumno
     */
    public Alumno getEntidadalumno() {
        return entidadalumno;
    }

    /**
     * @param entidadalumno the entidadalumno to set
     */
    public void setEntidadalumno(Alumno entidadalumno) {
        this.entidadalumno = entidadalumno;
    }

    /**
     * @return the entidadactividad
     */
    public Actividad getEntidadactividad() {
        return entidadactividad;
    }

    /**
     * @param entidadactividad the entidadactividad to set
     */
    public void setEntidadactividad(Actividad entidadactividad) {
        this.entidadactividad = entidadactividad;
    }

    /**
     * @return the entidadcarrera
     */
    public CatCarreras getEntidadcarrera() {
        return entidadcarrera;
    }

    /**
     * @param entidadcarrera the entidadcarrera to set
     */
    public void setEntidadcarrera(CatCarreras entidadcarrera) {
        this.entidadcarrera = entidadcarrera;
    }

    /**
     * @return the Totalhombres
     */
    public int getTotalhombres() {
        return Totalhombres;
    }

    /**
     * @param Totalhombres the Totalhombres to set
     */
    public void setTotalhombres(int Totalhombres) {
        this.Totalhombres = Totalhombres;
    }

    /**
     * @return the Totalmujeres
     */
    public int getTotalmujeres() {
        return Totalmujeres;
    }

    /**
     * @param Totalmujeres the Totalmujeres to set
     */
    public void setTotalmujeres(int Totalmujeres) {
        this.Totalmujeres = Totalmujeres;
    }

    /**
     * @return the totaltablaactida
     */
    public int getTotaltablaactida() {
        return totaltablaactida;
    }

    /**
     * @param totaltablaactida the totaltablaactida to set
     */
    public void setTotaltablaactida(int totaltablaactida) {
        this.totaltablaactida = totaltablaactida;
    }

}
