/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entities.Usuarios;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author omar
 */
@Stateless
public class UsuariosFacade extends AbstractFacade<Usuarios>{
    @PersistenceContext(unitName = "SGAEPU")
    private EntityManager em;
 
    public UsuariosFacade(){
        super(Usuarios.class);
    }

    @Override
    protected EntityManager getEntityManager() {
       return em;
    }
}
