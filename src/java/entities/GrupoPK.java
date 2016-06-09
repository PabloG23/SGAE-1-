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
public class GrupoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idGrupo")
    private int idGrupo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Actividad_idActividad")
    private int actividadidActividad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Promotor_idPromotor")
    private int promotoridPromotor;

    public GrupoPK() {
    }

    public GrupoPK(int idGrupo, int actividadidActividad, int promotoridPromotor) {
        this.idGrupo = idGrupo;
        this.actividadidActividad = actividadidActividad;
        this.promotoridPromotor = promotoridPromotor;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public int getActividadidActividad() {
        return actividadidActividad;
    }

    public void setActividadidActividad(int actividadidActividad) {
        this.actividadidActividad = actividadidActividad;
    }

    public int getPromotoridPromotor() {
        return promotoridPromotor;
    }

    public void setPromotoridPromotor(int promotoridPromotor) {
        this.promotoridPromotor = promotoridPromotor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idGrupo;
        hash += (int) actividadidActividad;
        hash += (int) promotoridPromotor;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrupoPK)) {
            return false;
        }
        GrupoPK other = (GrupoPK) object;
        if (this.idGrupo != other.idGrupo) {
            return false;
        }
        if (this.actividadidActividad != other.actividadidActividad) {
            return false;
        }
        if (this.promotoridPromotor != other.promotoridPromotor) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.GrupoPK[ idGrupo=" + idGrupo + ", actividadidActividad=" + actividadidActividad + ", promotoridPromotor=" + promotoridPromotor + " ]";
    }
    
}
