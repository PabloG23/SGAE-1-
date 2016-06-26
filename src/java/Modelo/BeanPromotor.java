/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import entities.CatActividad;
import entities.Promotor;
import facade.CatActFacade;
import facade.PromotorFacade;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author pablog23
 */
@ManagedBean
@SessionScoped
public class BeanPromotor implements Serializable {

    private Promotor promotorEntidad = new Promotor();

    @Inject
    private PromotorFacade promotorFacade;


    public BeanPromotor() {

    }
    
    public List<Promotor> jalarPromotores(){
        List<Promotor> obj= promotorFacade.findAll();
        return obj;
        
    }

    public void agregarPromotor() {
      
      
        promotorEntidad.setStatus(true);
        promotorFacade.create(promotorEntidad);
        destroyWorld();

    }

    public Promotor getPromotor() {
        return promotorEntidad;
    }

    public void setPromotor(Promotor promotor) {
        this.promotorEntidad = promotor;
    }
    public void destroyWorld() {
        addMessage("Promotor Agregado", "Agregada");
    }
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);
 
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String logo = externalContext.getRealPath("") + File.separator + "resources" + File.separator + "image" + File.separator + "ddd.jpg";
         
        pdf.add(Image.getInstance(logo));
    }
    
}
