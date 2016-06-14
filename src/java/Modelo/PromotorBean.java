/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import entities.Promotor;
import facade.PromotorFacade;
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
public class PromotorBean {

    /**
     * Creates a new instance of PromotorBean
     */
    private String ap_pat;
    private String ap_mat;
    private String nombre;
    private String correo;
    private String telefono;
    private boolean status;
    private String especialidades;
    private Promotor promotor;
    
    @Inject
    private PromotorFacade pc;
    
    public PromotorBean() {

    }

    public String getAp_pat() {
        return ap_pat;
    }

    public void setAp_pat(String ap_pat) {
        this.ap_pat = ap_pat;
    }

    public String getAp_mat() {
        return ap_mat;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    public void setAp_mat(String ap_mat) {
        this.ap_mat = ap_mat;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(String especialidades) {
        this.especialidades = especialidades;
    }

    public PromotorFacade getPc() {
        return pc;
    }

    public void setPc(PromotorFacade pc) {
        this.pc = pc;
    }

    

    

    public Promotor getPromotor() {
        return promotor;
    }

    public void setPromotor(Promotor promotor) {
        this.promotor = promotor;
    }

    public void agregarPromotor() {
        
        Promotor promotor = new Promotor();
        
        promotor.setIdPromotor(12);
        promotor.setApeMat(ap_mat);
        promotor.setApePat(ap_pat);
        promotor.setNombre(correo); 
        promotor.setTelefono(telefono);
        promotor.setStatus(false);
        promotor.setEspecialidades(especialidades);
        pc.create(promotor);

    }

}
