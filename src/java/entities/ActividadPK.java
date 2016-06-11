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
 * @author pablog23
 */
@Embeddable
public class ActividadPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idActividad")
    private int idActividad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CatActividad_idCatAct")
    private int catActividadidCatAct;

    public ActividadPK() {
    }

    public ActividadPK(int idActividad, int catActividadidCatAct) {
        this.idActividad = idActividad;
        this.catActividadidCatAct = catActividadidCatAct;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public int getCatActividadidCatAct() {
        return catActividadidCatAct;
    }

    public void setCatActividadidCatAct(int catActividadidCatAct) {
        this.catActividadidCatAct = catActividadidCatAct;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idActividad;
        hash += (int) catActividadidCatAct;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActividadPK)) {
            return false;
        }
        ActividadPK other = (ActividadPK) object;
        if (this.idActividad != other.idActividad) {
            return false;
        }
        if (this.catActividadidCatAct != other.catActividadidCatAct) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ActividadPK[ idActividad=" + idActividad + ", catActividadidCatAct=" + catActividadidCatAct + " ]";
    }
    
}
