/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import entities.Activi;
import entities.Actividad;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author omar
 */
@Named(value = "actividad")
@RequestScoped
public class Actividad_clase {

    /**
     * Creates a new instance of Actividad
     */
    public Actividad_clase() {
    }

    public Actividad_clase(String nombre, String semestre) {

        this.nombre = nombre;
        this.semestre = semestre;
    }
    private Integer idActividad;
    private String tipo;
    private String nombre;
    private Integer año;
    private String semestre;

    private Activi actividad;

    /**
     * @return the idActividad
     */
    public Integer getIdActividad() {
        return idActividad;
    }

    /**
     * @param idActividad the idActividad to set
     */
    public void setIdActividad(Integer idActividad) {
        this.idActividad = idActividad;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
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
    public Integer getAño() {
        return año;
    }

    /**
     * @param año the año to set
     */
    public void setAño(Integer año) {
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

    

    public Connection getConnection() {
        Connection con = null;

        String url = "jdbc:mysql://127.0.0.1:3306/SGAE";
        String user = "root";
        String password = "password";
        try {
            con = DriverManager.getConnection(url, user, password);
            if (con != null) {
                System.out.println("Conexion exitosa");
            } else {
                System.out.println("PELAS");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
        }
        return con;
    }

    public void add() {
        PreparedStatement ps = null;
        Connection con = getConnection();
        ResultSet rsf = null;
        String sql = "INSERT INTO actividad (nombre, semestre) VALUES (?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, semestre);
            ps.execute();
            con.close();
            System.out.println("Data Added Successfully");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

//    public List<Actividad> getTcclientes() throws SQLException {
//        System.out.println("PUNIOS");
//        ResultSet rs = null;
//        PreparedStatement pst = null;
//        Connection con = getConnection();
//        String stm = "Select nombre,semestre from actividad";
//        List<Actividad> lista = new ArrayList<Actividad>();
//        try {
//            pst = con.prepareStatement(stm);
//            rs = pst.executeQuery();
//
//            while (rs.next()) {
//                Actividad act = new Actividad();
//                act.setNombre(rs.getString("nombre"));
//                act.setSemestre(rs.getString("semestre"));
//
//                lista.add(act);
//                System.out.println(lista);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return lista;
//    }
    public List<Actividad> getCustomerList() throws SQLException {
        Connection con = getConnection();
        PreparedStatement ps
                = con.prepareStatement(
                        "select nombre, semestre from actividad");

        //get customer data from database
        ResultSet result = ps.executeQuery();

        List<Actividad> list = new ArrayList<Actividad>();

        while (result.next()) {
            Actividad cust = new Actividad();

            cust.setNombreAct(result.getString("nombre"));
            cust.setSemestre(result.getString("semestre"));

            //store all data into a List
            list.add(cust);
        }

        return list;
    }
}
