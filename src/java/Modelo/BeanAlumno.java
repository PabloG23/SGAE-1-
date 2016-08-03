/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import entities.Alumno;
import entities.CapDif;
import entities.CatCarreras;
import entities.Grupo;
import facade.AlumnoFacade;
import facade.CapacidadesFacade;
import facade.CatCarrerasFacade;
import facade.GrupoFacade;
import java.util.ArrayList;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author pablog23
 */
@ManagedBean
@SessionScoped
public class BeanAlumno {

    private Alumno entidadAlumno = new Alumno();
    private Grupo entidadGrupo = new Grupo();

    @Inject
    private AlumnoFacade alumnoFacade;
    @Inject
    private CatCarrerasFacade carrerasFacade;
    @Inject
    GrupoFacade grupoFacade;
    @Inject
    CapacidadesFacade capacidadFacade;

    private int idCatCarreras;
    private int idGrupo;
    private int idCapDif;

    public BeanAlumno() {
    }

    public void agregarAlumno() {

        CapDif capac = capacidadFacade.find(this.idCapDif);
        Grupo grupo = grupoFacade.find(this.idGrupo);
        CatCarreras carrera = carrerasFacade.find(this.idCatCarreras);

        entidadAlumno.setCatCarreras(carrera);
        entidadAlumno.setGrupo(grupo);
        entidadAlumno.setCapDif(capac);
        entidadAlumno.setAcreditado(false);
        entidadAlumno.setNoAcreditado(true);
        entidadAlumno.setReconocimiento(false);
        entidadAlumno.setSeleccionado(false);

        alumnoFacade.create(entidadAlumno);
        destroyWorld();
    }

    public List<CatCarreras> jalarCarreras() {
        List<CatCarreras> obj = carrerasFacade.findAll();
        return obj;
    }
    /////PRUEBA PARA SACAR LOS GRUPOS DE UN PROMOTOR/////
    int pruebaidpromotor = 2;
    int idgrupo;

    public List<Grupo> jalargrupodepromotorlogeado() {
        int numeropromotor;
        String idcadena = "";
        List<Grupo> listagruposprom = new ArrayList<Grupo>();
        List<Grupo> gruobj = grupoFacade.findAll();
        for (int i = 0; i < gruobj.size(); i++) {
            idgrupo = gruobj.get(i).getIdGrupo();
            idcadena = Integer.toString(idgrupo);
            numeropromotor = Integer.parseInt("" + idcadena.charAt(1));
            if (numeropromotor == pruebaidpromotor) {
                setEntidadGrupo(grupoFacade.find(this.idgrupo));
                listagruposprom.add(getEntidadGrupo());
                System.out.println("grupos del promotor1: " + idgrupo);
                System.out.println("Lista chida: " + listagruposprom);
                //System.out.println("Datos traidos: "+entidadGrupo.getDias()+entidadGrupo.getHorario()+entidadGrupo.getPromotor().getNombre());
            } else {
                System.out.println("estos no son grupos de este promotor: " + idgrupo);
            }

        }
        System.out.println("Lista chidafinal: " + listagruposprom);
        return listagruposprom;

    }
    /////FIN DE PRUEBA PARA SACAR LOS GRUPOS DE UN PROMOTOR/////

    public void calificarAlumno(Alumno alumno) {
        alumno.setAcreditado(!alumno.isAcreditado());
        alumno.setNoAcreditado(!alumno.isNoAcreditado());
        alumnoFacade.edit(alumno);
        alumnoCalificado();
    }

    public void reconocerAlumno(Alumno alumno) {
        alumno.setReconocimiento(!alumno.getReconocimiento());
        alumnoFacade.edit(alumno);
    }

    public void seleccionarAlumno(Alumno alumno) {
        alumno.setSeleccionado(!alumno.getSeleccionado());
        alumnoFacade.edit(alumno);
    }

    public List<Alumno> alumnos_rec() {
        List<Alumno> alumnosR = alumnoFacade.findAll();
        List<Alumno> alumnosN = new ArrayList<Alumno>();

        for (int i = 0; i < alumnosR.size(); i++) {

            if (alumnosR.get(i).getReconocimiento() == true) {
                alumnosN.add(alumnosR.get(i));
            }
        }
        return alumnosN;
    }

    public List<Alumno> alumnos_hab() {
        List<Alumno> alumnosR = alumnoFacade.findAll();
        List<Alumno> alumnosN = new ArrayList<Alumno>();

        for (int i = 0; i < alumnosR.size(); i++) {

            if (alumnosR.get(i).getSeleccionado() == true) {
                alumnosN.add(alumnosR.get(i));
            }
        }
        return alumnosN;
    }
    
    public void destroyWorld() {
        addMessage("Te has inscrito al grupo satisfactoriamente", "Agregado");
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void alumnoCalificado() {
        addMessage1("Alumno Acreditado Satisfactoriamente", "Acreditado");
    }

    public void addMessage1(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public List<Grupo> jalarGrupos() {
        List<Grupo> obj = grupoFacade.findAll();
        return obj;
    }

    public List<CapDif> jalarCapacidades() {
        List<CapDif> obj = capacidadFacade.findAll();
        return obj;
    }

    public List<Alumno> jalarAlumnos() {
        List<Alumno> obj = alumnoFacade.findAll();
        return obj;
    }

    public int getIdCatCarreras() {
        return idCatCarreras;
    }

    public void setIdCatCarreras(int idCatCarreras) {
        this.idCatCarreras = idCatCarreras;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Alumno getEntidadAlumno() {
        return entidadAlumno;
    }

    public void setEntidadAlumno(Alumno entidadAlumno) {
        this.entidadAlumno = entidadAlumno;
    }

    public int getIdCapDif() {
        return idCapDif;
    }

    public void setIdCapDif(int idCapDif) {
        this.idCapDif = idCapDif;
    }

    /**
     * @return the entidadGrupo
     */
    public Grupo getEntidadGrupo() {
        return entidadGrupo;
    }

    /**
     * @param entidadGrupo the entidadGrupo to set
     */
    public void setEntidadGrupo(Grupo entidadGrupo) {
        this.entidadGrupo = entidadGrupo;
    }

}
