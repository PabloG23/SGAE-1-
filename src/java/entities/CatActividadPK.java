/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author omar
 */
@Embeddable
public class CatActividadPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idCatAct")
    private int idCatAct;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Actividad_idActividad")
    private int actividadidActividad;

    public CatActividadPK() {
    }

    public CatActividadPK(int idCatAct, int actividadidActividad) {
        this.idCatAct = idCatAct;
        this.actividadidActividad = actividadidActividad;
    }

    public int getIdCatAct() {
        return idCatAct;
    }

    public void setIdCatAct(int idCatAct) {
        this.idCatAct = idCatAct;
    }

    public int getActividadidActividad() {
        return actividadidActividad;
    }

    public void setActividadidActividad(int actividadidActividad) {
        this.actividadidActividad = actividadidActividad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idCatAct;
        hash += (int) actividadidActividad;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatActividadPK)) {
            return false;
        }
        CatActividadPK other = (CatActividadPK) object;
        if (this.idCatAct != other.idCatAct) {
            return false;
        }
        if (this.actividadidActividad != other.actividadidActividad) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CatActividadPK[ idCatAct=" + idCatAct + ", actividadidActividad=" + actividadidActividad + " ]";
    }
    
}
