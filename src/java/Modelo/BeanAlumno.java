/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import entities.Alumno;
import entities.CapDif;
import entities.CatCarreras;
import entities.Grupo;
import facade.AlumnoFacade;
import facade.CapacidadesFacade;
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
    @Inject
    CapacidadesFacade capacidadFacade;

    private int idCatCarreras;
    private int idGrupo;
    private int idCapDif;

    public BeanAlumno() {
    }

    public void agregarAlumno() {

        CapDif capac = capacidadFacade.find(this.idCapDif);
        Grupo grupo = grupoFacade.find(this.idGrupo);
        CatCarreras carrera= carrerasFacade.find(this.idCatCarreras);
        
        entidadAlumno.setCatCarreras(carrera);
        entidadAlumno.setGrupo(grupo);
        entidadAlumno.setCapDif(capac);
        entidadAlumno.setAcreditado(false);
        entidadAlumno.setNoAcreditado(true);
        entidadAlumno.setReconocimiento(false);
        entidadAlumno.setSeleccionado(false);

        alumnoFacade.create(entidadAlumno);
    }

    public List<CatCarreras> jalarCarreras() {
        List<CatCarreras> obj = carrerasFacade.findAll();
        return obj;
    }

    public List<Grupo> jalarGrupos() {
        List<Grupo> obj = grupoFacade.findAll();
        return obj;
    }

    public List<CapDif> jalarCapacidades() {
        List<CapDif> obj = capacidadFacade.findAll();
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

    public Alumno getEntidadAlumno() {
        return entidadAlumno;
    }

    public void setEntidadAlumno(Alumno entidadAlumno) {
        this.entidadAlumno = entidadAlumno;
    }

    public int getIdCapDif() {
        return idCapDif;
    }

    public void setIdCapDif(int idCapDif) {
        this.idCapDif = idCapDif;
    }

}
