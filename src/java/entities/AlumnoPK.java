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
public class AlumnoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "noCtrl")
    private int noCtrl;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Grupo_idGrupo1")
    private int grupoidGrupo1;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Grupo_Actividad_idActividad1")
    private int grupoActividadidActividad1;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Grupo_Promotor_idPromotor1")
    private int grupoPromotoridPromotor1;

    public AlumnoPK() {
    }

    public AlumnoPK(int noCtrl, int grupoidGrupo1, int grupoActividadidActividad1, int grupoPromotoridPromotor1) {
        this.noCtrl = noCtrl;
        this.grupoidGrupo1 = grupoidGrupo1;
        this.grupoActividadidActividad1 = grupoActividadidActividad1;
        this.grupoPromotoridPromotor1 = grupoPromotoridPromotor1;
    }

    public int getNoCtrl() {
        return noCtrl;
    }

    public void setNoCtrl(int noCtrl) {
        this.noCtrl = noCtrl;
    }

    public int getGrupoidGrupo1() {
        return grupoidGrupo1;
    }

    public void setGrupoidGrupo1(int grupoidGrupo1) {
        this.grupoidGrupo1 = grupoidGrupo1;
    }

    public int getGrupoActividadidActividad1() {
        return grupoActividadidActividad1;
    }

    public void setGrupoActividadidActividad1(int grupoActividadidActividad1) {
        this.grupoActividadidActividad1 = grupoActividadidActividad1;
    }

    public int getGrupoPromotoridPromotor1() {
        return grupoPromotoridPromotor1;
    }

    public void setGrupoPromotoridPromotor1(int grupoPromotoridPromotor1) {
        this.grupoPromotoridPromotor1 = grupoPromotoridPromotor1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) noCtrl;
        hash += (int) grupoidGrupo1;
        hash += (int) grupoActividadidActividad1;
        hash += (int) grupoPromotoridPromotor1;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AlumnoPK)) {
            return false;
        }
        AlumnoPK other = (AlumnoPK) object;
        if (this.noCtrl != other.noCtrl) {
            return false;
        }
        if (this.grupoidGrupo1 != other.grupoidGrupo1) {
            return false;
        }
        if (this.grupoActividadidActividad1 != other.grupoActividadidActividad1) {
            return false;
        }
        if (this.grupoPromotoridPromotor1 != other.grupoPromotoridPromotor1) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.AlumnoPK[ noCtrl=" + noCtrl + ", grupoidGrupo1=" + grupoidGrupo1 + ", grupoActividadidActividad1=" + grupoActividadidActividad1 + ", grupoPromotoridPromotor1=" + grupoPromotoridPromotor1 + " ]";
    }
    
}
