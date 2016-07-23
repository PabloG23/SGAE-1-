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
public class tablasgracarr {

    private String Carrera;
    private int H;
    private int M;
private int totalcarrera;
    public tablasgracarr(String Carrera, int H, int M, int totalcarrera) {
        this.Carrera = Carrera;
        this.H = H;
        this.M = M;
        this.totalcarrera=totalcarrera;
    }

    /**
     * @return the Carrera
     */
    public String getCarrera() {
        return Carrera;
    }

    /**
     * @param Carrera the Carrera to set
     */
    public void setCarrera(String Carrera) {
        this.Carrera = Carrera;
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
     * @return the totalcarrera
     */
    public int getTotalcarrera() {
        return totalcarrera;
    }

    /**
     * @param totalcarrera the totalcarrera to set
     */
    public void setTotalcarrera(int totalcarrera) {
        this.totalcarrera = totalcarrera;
    }
    
}
