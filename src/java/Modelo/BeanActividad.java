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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.inject.Inject;
//import javax.enterprise.context.Dependent;
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

    @Inject
    private ActividadFacade actividadFacade;

    @Inject
    private CatActFacade catalogoFacade;

    public void add() {
        CatActividad catActividad = catalogoFacade.find(this.idCatActividad);
        actividad.setCatActividad(catActividad);
        actividadFacade.create(actividad);
        destroyWorld();
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

        System.out.println("haber: " + catActividad.getIdCatAct());
        Iterator<Actividad> it = obj.iterator();

        while (it.hasNext()) {
            if (catActividad.getIdCatAct().equals(it.next().getCatActividad().getIdCatAct())) {
                System.out.println("SI ESTA: " + ((it.next().getCatActividad().getIdCatAct()) - 1));
                respuesta = true;
                System.out.println("respuesta: " + respuesta);
            } else {
                respuesta = false;
                System.out.println("NO ESTA respuesta: " + respuesta);

            }
        }

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
}
