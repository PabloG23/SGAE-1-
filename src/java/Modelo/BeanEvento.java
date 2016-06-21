/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import entities.Actividad;
import entities.Evento;
import entities.Promotor;
import facade.ActividadFacade;
import facade.EventoFacade;
import facade.PromotorFacade;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author pablog23
 */
@ManagedBean
@RequestScoped
public class BeanEvento {

    private Evento entidadEvento = new Evento();

    @Inject
    private PromotorFacade promotorFacade;
    @Inject
    private ActividadFacade actividadFacade;
    @Inject
    private EventoFacade eventoFacade;

    private int idpromotor;
    private int idactividad;

    public BeanEvento() {

    }
    
    public void agregarEvento(){
        Actividad obj = actividadFacade.find(this.idactividad);
        entidadEvento.setActividad(obj);
        
        Promotor obj1 = promotorFacade.find(this.idpromotor);
        entidadEvento.setPromotor(obj1);
        
        entidadEvento.setTotal(total());
        eventoFacade.create(entidadEvento);
        
    }
    
    public int total(){
        int x,y,z;
         x=entidadEvento.getHombres();
         y= entidadEvento.getMujeres();
         z=x+y;
         return z;
    }
    

    public int getIdpromotor() {
        return idpromotor;
    }

    public void setIdpromotor(int idpromotor) {
        this.idpromotor = idpromotor;
    }

    public int getIdactividad() {
        return idactividad;
    }

    public void setIdactividad(int idactividad) {
        this.idactividad = idactividad;
    }

    public Evento getEntidadEvento() {
        return entidadEvento;
    }

    public void setEntidadEvento(Evento entidadEvento) {
        this.entidadEvento = entidadEvento;
    }
    
    

}
