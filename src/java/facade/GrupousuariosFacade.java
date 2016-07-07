/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entities.Grupousuarios;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author omar
 */
@Stateless
public class GrupousuariosFacade extends AbstractFacade<Grupousuarios>{
    @PersistenceContext(unitName = "SGAEPU")
    private EntityManager em;
 
    public GrupousuariosFacade(){
        super(Grupousuarios.class);
    }

    @Override
    protected EntityManager getEntityManager() {
       return em;
    }
}
