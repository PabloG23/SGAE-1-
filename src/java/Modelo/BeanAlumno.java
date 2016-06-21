/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import entities.Alumno;
import entities.CatCarreras;
import facade.AlumnoFacade;
import facade.CatCarrerasFacade;
import facade.GrupoFacade;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class BeanAlumno {

   private Alumno entidadAlumno = new Alumno();
   
   @Inject
   private AlumnoFacade alumnoFacade;
   @Inject
   private CatCarrerasFacade carrerasFacade;
   @Inject 
   GrupoFacade grupoFacade;
   
   private int idCatCarreras;
   private int idGrupo;
    
    public BeanAlumno() {
    }
    
  
   
    
     public List<CatCarreras> jalarCarreras()
     {
        List<CatCarreras> obj= carrerasFacade.findAll();
        return obj;
    }

    public int getIdCatCarreras() {
        return idCatCarreras;
    }

    public void setIdCatCarreras(int idCatCarreras) {
        this.idCatCarreras = idCatCarreras;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    
}
