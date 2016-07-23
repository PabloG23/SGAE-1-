/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author omar
 */
public class tablasgraact {

    private String Actividades;
    private int H;
    private int M;
    private int totalactividad;

    public tablasgraact(String Actividades, int H, int M, int totalactividad) {
        this.Actividades = Actividades;
        this.H = H;
        this.M = M;
        this.totalactividad=totalactividad;
    }

    /**
     * @return the Actividades
     */
    public String getActividades() {
        return Actividades;
    }

    /**
     * @param Actividades the Actividades to set
     */
    public void setActividades(String Actividades) {
        this.Actividades = Actividades;
    }

    /**
     * @return the H
     */
    public int getH() {
        return H;
    }

    /**
     * @param H the H to set
     */
    public void setH(int H) {
        this.H = H;
    }

    /**
     * @return the M
     */
    public int getM() {
        return M;
    }

    /**
     * @param M the M to set
     */
    public void setM(int M) {
        this.M = M;
    }

    /**
     * @return the totalactividad
     */
    public int getTotalactividad() {
        return totalactividad;
    }

    /**
     * @param totalactividad the totalactividad to set
     */
    public void setTotalactividad(int totalactividad) {
        this.totalactividad = totalactividad;
    }
}
