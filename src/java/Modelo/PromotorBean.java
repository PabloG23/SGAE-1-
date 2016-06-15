/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import entities.Promotor;
import facade.PromotorFacade;
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
public class PromotorBean implements Serializable {

    private Promotor promotor = new Promotor();

    @Inject
    private PromotorFacade pro;

    public PromotorBean() {

    }

    public void agregarPromotor() {
        promotor.setStatus(true);
        pro.create(promotor);

    }

    public Promotor getPromotor() {
        return promotor;
    }

    public void setPromotor(Promotor promotor) {
        this.promotor = promotor;
    }
}
