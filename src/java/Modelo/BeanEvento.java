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
import java.util.List;

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
    
    public List<Evento> jalarEvento(){
        List<Evento> obj = eventoFacade.findAll();
        return obj;
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
