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
import java.util.List;
import javax.inject.Named;
import javax.inject.Inject;
//import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author omar
 */
//@Named(value = "beanActividad")
//@Dependent
@ManagedBean
@SessionScoped
public class BeanActividad implements Serializable {

    private Actividad actividad = new Actividad();

    @Inject
    private ActividadFacade ac;
    @Inject
    private CatActFacade cat;

    public void add() {
        System.out.println("Agregando actividad");
        ac.create(actividad);
    }

    public List<String> completarAño(String año) {
        List<String> obj = new ArrayList<String>();
        for (int i = 2016; i < 2030; i++) {
            obj.add(año + i);
        }

        return obj;
    }

    public List<CatActividad> jalarNombres() {
        List<CatActividad> obj = cat.findAll();

        return obj;
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

}
