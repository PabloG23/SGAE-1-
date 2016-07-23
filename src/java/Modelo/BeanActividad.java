/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import entities.Actividad;
import entities.CatActividad;
import facade.ActividadFacade;
import facade.CatActFacade;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.application.FacesMessage;

import javax.inject.Inject;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author omar
 */
@ManagedBean
@SessionScoped
public class BeanActividad implements Serializable {

    private Actividad actividad = new Actividad();

    private int idCatActividad;
    private boolean verificaractividad = false;

    @Inject
    private ActividadFacade actividadFacade;

    @Inject
    private CatActFacade catalogoFacade;

    public void add() {
        CatActividad catActividad = catalogoFacade.find(this.idCatActividad);
        verificaractividad = buscaractividad();
        if (verificaractividad == true) {
            mensajeactivdad();
        } else {
            if (verificaractividad == false) {
                actividad.setCatActividad(catActividad);
                actividadFacade.create(actividad);
                destroyWorld();
            }
        }

    }

    public List<String> completarAño(String año) {
        List<String> obj = new ArrayList<>();
        for (int i = 2016; i < 2030; i++) {
            obj.add(año + i);
        }

        return obj;
    }

    public List<CatActividad> jalarNombres() {
        List<CatActividad> obj = catalogoFacade.findAll();
        return obj;
    }

    public boolean buscaractividad() {
        boolean respuesta = false;
        CatActividad catActividad = catalogoFacade.find(this.idCatActividad);
        List<Actividad> obj = actividadFacade.findAll();
        for (int i = 0; i < obj.size(); i++) {
            if (catActividad.getIdCatAct().equals(obj.get(i).getCatActividad().getIdCatAct())) {
                //System.out.println("Si esta este catact en actividad: " + catActividad.getIdCatAct() + "--" + obj.get(i).getCatActividad().getIdCatAct());
                respuesta = true;
                break;
            } else {
                //System.out.println("NO esta este catact en actividad");
                respuesta = false;
            }
        }
        //System.out.println("respuesta:" + respuesta);
        return respuesta;
    }

    /**
     * @return the actividad
     */
    public Actividad getActividad() {
        return actividad;
    }

    /**
     * @param actividad the actividad to set
     */
    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public int getIdCatActividad() {
        return idCatActividad;
    }

    public void setIdCatActividad(int idCatActividad) {
        this.idCatActividad = idCatActividad;
    }

    public void destroyWorld() {
        addMessage("Actividad del semestre Agregada", "Agregada");
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void mensajeactivdad() {
        agregarmensaje("Esta Actividad ya ha sido agregada anteriormente", "Agregada");
    }

    public void agregarmensaje(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     * @return the verificaractividad
     */
    public boolean isVerificaractividad() {
        return verificaractividad;
    }

    /**
     * @param verificaractividad the verificaractividad to set
     */
    public void setVerificaractividad(boolean verificaractividad) {
        this.verificaractividad = verificaractividad;
    }
}
