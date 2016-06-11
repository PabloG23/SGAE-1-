/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;


import entities.Promotor;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pablog23
 */
@Stateless
public class PromotorFacade extends AbstractFacade<Promotor>{
    @PersistenceContext(unitName = "SGAEPU")
    private EntityManager em;
    
    public PromotorFacade(){
        super(Promotor.class);
    }

    @Override
    protected EntityManager getEntityManager() {
       return em;
    }
}
