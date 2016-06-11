/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import entities.Actividad;
import facade.ActividadFacade;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.inject.Named;
import javax.inject.Inject;
//import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author omar
 */
//@Named(value = "beanActividad")
//@Dependent
@ManagedBean
public class BeanActividad {

    private String tipoact;
    private String nombre;
    private int año;
    private String semestre;
    private Actividad actividad;

    @Inject
    private ActividadFacade ac;

    public Connection getConnection() {
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/SGAE";
        String user = "root";
        String password = "9kLY_trkAP_k";
        try {
            con = DriverManager.getConnection(url, user, password);
            if (con != null) {
                System.out.println("Conexion exitosa");
            } else {
                System.out.println("error de conexion");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
        }
        return con;
    }

    public void add() {
            Actividad actividad = new Actividad();
        actividad.setTipoact(tipoact);
        actividad.setNombreAct(nombre);
        actividad.setAño(año);
        actividad.setSemestre(semestre);
        ac.create(actividad);

//        PreparedStatement ps = null;
//        //PreparedStatement pc = null;
//        Connection con = getConnection();
//        ResultSet rsf = null;
//        String sql = "INSERT INTO Actividad (tipoact, nombreAct, año, semestre) VALUES (?,?,?,?)";
////        String sql1 = "INSERT INTO CatActividad (nombre, Actividad_idActividad, tipo) VALUES (?,?,?)";
//
//        
//        try {
//            ps = con.prepareStatement(sql);
//            ps.setString(1, getTipoact());
//            ps.setString(2, getNombre());
//            ps.setString(3, getAño());
//            ps.setString(4, getSemestre());
//            ps.execute();
//            
//            
//            con.close();
//            System.out.println("Dato agregado correctamente");
//            System.out.println("id:"+ps.getMetaData());
//        } catch (Exception e) {
//            System.out.println(e);
//        }
    }

    /**
     * Creates a new instance of BeanActividad
     */
    public BeanActividad() {
    }

    /**
     * @return the tipoact
     */
    public String getTipoact() {
        return tipoact;
    }

    /**
     * @param tipoact the tipoact to set
     */
    public void setTipoact(String tipoact) {
        this.tipoact = tipoact;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the año
     */
    public int getAño() {
        return año;
    }

    /**
     * @param año the año to set
     */
    public void setAño(int año) {
        this.año = año;
    }

    /**
     * @return the semestre
     */
    public String getSemestre() {
        return semestre;
    }

    /**
     * @param semestre the semestre to set
     */
    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    /**
     * @return the actividad
     */
    public Actividad getActividad() {
        return actividad;
    }

    /**
     * @param actividad the actividad to set
     */
    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

}
