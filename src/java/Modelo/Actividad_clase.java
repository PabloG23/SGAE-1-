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
    private String nombre;
    private String semestre;
    private Activi actividad;


    public Connection getConnection() {
        Connection con = null;

        String url = "jdbc:mysql://localhost:3306/SGAE";
        
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
        String sql = "INSERT INTO activi (nombre, semestre) VALUES (?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, getNombre());
            ps.setString(2, getSemestre());
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
    public List<Activi> getCustomerList() throws SQLException {
        Connection con = getConnection();
        PreparedStatement ps
                = con.prepareStatement(
                        "select nombre, semestre from activi");

        //get customer data from database
        ResultSet result = ps.executeQuery();

        List<Activi> list = new ArrayList<Activi>();

        while (result.next()) {
            Activi cust = new Activi();

            cust.setNombre(result.getString("nombre"));
            cust.setSemestre(result.getString("semestre"));

            //store all data into a List
            list.add(cust);
        }

        return list;
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
    public Activi getActividad() {
        return actividad;
    }

    /**
     * @param actividad the actividad to set
     */
    public void setActividad(Activi actividad) {
        this.actividad = actividad;
    }
}
