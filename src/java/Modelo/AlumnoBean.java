/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author pablog23
 */
@ManagedBean
@RequestScoped
public class AlumnoBean {

    /**
     * Creates a new instance of AlumnoBean
     */
    
    public AlumnoBean() {
    }
    
    private int ctrl;
    private String ap_pat;
    private String ap_mat;
    private String nombre;
    private String calificacion;
    private int edad;
    private String sexo;
    private String correo;

    public int getCtrl() {
        return ctrl;
    }

    public void setCtrl(int ctrl) {
        this.ctrl = ctrl;
    }

    public String getAp_pat() {
        return ap_pat;
    }

    public void setAp_pat(String ap_pat) {
        this.ap_pat = ap_pat;
    }

    public String getAp_mat() {
        return ap_mat;
    }

    public void setAp_mat(String ap_mat) {
        this.ap_mat = ap_mat;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
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
    
     public void agregarAlumno(){
        PreparedStatement ps =null;
        Connection con = getConnection();
        ResultSet rsf =null;
        String sql = "INSERT INTO Alumno (noCtrl,apPat,apMat,nombre,calificacion,edad,sexo,correo) VALUES(?,?,?,?,?,?,?,?)";
        
        try{
            ps=con.prepareStatement(sql);
            
            ps.setInt(1, getCtrl());
            ps.setString(2, getAp_pat());
            ps.setString(3, getAp_mat());
            ps.setString(4, getNombre());
            ps.setString(5, getCalificacion());
            ps.setInt(6, getEdad());
            ps.setString(7, getSexo());
            ps.setString(8, getCorreo());
            
            ps.execute();
            
            con.close();
            System.out.println("Dato agregado correctamente");
            
        }catch (Exception e){
            System.out.println(e);
        }
    }

    
}
