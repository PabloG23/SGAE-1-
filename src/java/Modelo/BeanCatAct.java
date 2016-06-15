/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import entities.CatActividad;
import facade.CatActFacade;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
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
    private CatActFacade cat;

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
        CatActividad catalogo = new CatActividad();
        catalogo.setNombre(nombre);
        catalogo.setTipo(tipoact);
        cat.create(catalogo);
    }

    public CatActFacade getCat() {
        return cat;
    }

    public void setCat(CatActFacade cat) {
        this.cat = cat;
    }
    
    
}
