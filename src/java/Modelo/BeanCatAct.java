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
         destroyWorld();
    }

    public CatActFacade getCat() {
        return catalogoFacade;
    }

    public void setCat(CatActFacade cat) {
        this.catalogoFacade = cat;
    }
    public void destroyWorld() {
        addMessage("Actividad Agregada", "Agregada");
    }
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
