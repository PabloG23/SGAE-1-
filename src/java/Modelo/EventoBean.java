/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author pablog23
 */
@ManagedBean
@RequestScoped
public class EventoBean {

    private String tipo_evento;
    private String nombre_evento;
    private String institucion;
    private String evento;
    private Date fecha_evento;
    private int hombres;
    private int mujeres;
    private int total;
    private String resultado;


    public String getTipo_evento() {
        return tipo_evento;
    }

    public void setTipo_evento(String tipo_evento) {
        this.tipo_evento = tipo_evento;
    }

    public String getNombre_evento() {
        return nombre_evento;
    }

    public void setNombre_evento(String nombre_evento) {
        this.nombre_evento = nombre_evento;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public Date getFecha_evento() {
        return fecha_evento;
    }

    public void setFecha_evento(Date fecha_evento) {
        this.fecha_evento = fecha_evento;
    }

    public int getHombres() {
        return hombres;
    }

    public void setHombres(int hombres) {
        this.hombres = hombres;
    }

    public int getMujeres() {
        return mujeres;
    }

    public void setMujeres(int mujeres) {
        this.mujeres = mujeres;
    }

    public int getTotal() {
        total=hombres+mujeres;
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    
    /**
     * Creates a new instance of EventoBean
     */
    public EventoBean() {
    }

    public Connection getConnection() {
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/SGAE";
        String usuario = "root";
        String password ="9kLY_trkAP_k";
        try{
            con=DriverManager.getConnection(url, usuario, password);
            if (con!=null) {
                System.out.println("Conexion exitosa");
            }else{
                System.out.println("error de conexion");
            }
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        finally{
            
        }
        return con;
    }
    
    public void agregarEventoo(){
        PreparedStatement ps =null;
        Connection con = getConnection();
        ResultSet rsf =null;
        String sql = "INSERT INTO Evento (tipo_evento,nombre_evento,inst_organizadora,evento,fecha_evento,hombres,mujeres,resultado) VALUES(?,?,?,?,?,?,?,?)";
        
        try{
            ps=con.prepareStatement(sql);
            
            ps.setString(1, getTipo_evento());
            ps.setString(2, getNombre_evento());
            ps.setString(3, getInstitucion());
            ps.setString(4, getEvento());
            ps.setDate(5, (java.sql.Date) getFecha_evento());
            ps.setInt(6, getHombres());
            ps.setInt(7, getMujeres());
            ps.setString(8, getResultado());
            
            ps.execute();
            
            con.close();
            System.out.println("Dato agregado correctamente");
            
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
