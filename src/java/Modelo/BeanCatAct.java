/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import entities.CatActividad;
import facade.CatActFacade;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author pablog23
 */
@ManagedBean
@SessionScoped
public class BeanCatAct implements Serializable {

    /**
     * Creates a new instance of BeanCatAct
     */
    public BeanCatAct() {
    }
    
    private String tipoact;
    private String nombre;
    
    @Inject
    private CatActFacade catalogoFacade;

    public String getTipoact() {
        return tipoact;
    }

    public void setTipoact(String tipoact) {
        this.tipoact = tipoact;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void addCatAct() {
        CatActividad catalogoEntidad = new CatActividad();
        catalogoEntidad.setNombre(nombre);
        catalogoEntidad.setTipo(tipoact);
        catalogoFacade.create(catalogoEntidad);
        displayLocation();
    }

    public CatActFacade getCat() {
        return catalogoFacade;
    }

    public void setCat(CatActFacade cat) {
        this.catalogoFacade = cat;
    }
    public void displayLocation() {
        FacesMessage msg;
        if(nombre != null && tipoact != null)
            msg = new FacesMessage("Registro exitoso", nombre + " of " + tipoact);
        else
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Necesita Ingresar Nombre y", "su tipo"); 
             
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }
    
}
