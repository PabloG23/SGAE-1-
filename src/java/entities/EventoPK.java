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
public class EventoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idevento")
    private int idevento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Promotor_idPromotor")
    private int promotoridPromotor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Actividad_idActividad")
    private int actividadidActividad;

    public EventoPK() {
    }

    public EventoPK(int idevento, int promotoridPromotor, int actividadidActividad) {
        this.idevento = idevento;
        this.promotoridPromotor = promotoridPromotor;
        this.actividadidActividad = actividadidActividad;
    }

    public int getIdevento() {
        return idevento;
    }

    public void setIdevento(int idevento) {
        this.idevento = idevento;
    }

    public int getPromotoridPromotor() {
        return promotoridPromotor;
    }

    public void setPromotoridPromotor(int promotoridPromotor) {
        this.promotoridPromotor = promotoridPromotor;
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
        hash += (int) idevento;
        hash += (int) promotoridPromotor;
        hash += (int) actividadidActividad;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EventoPK)) {
            return false;
        }
        EventoPK other = (EventoPK) object;
        if (this.idevento != other.idevento) {
            return false;
        }
        if (this.promotoridPromotor != other.promotoridPromotor) {
            return false;
        }
        if (this.actividadidActividad != other.actividadidActividad) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.EventoPK[ idevento=" + idevento + ", promotoridPromotor=" + promotoridPromotor + ", actividadidActividad=" + actividadidActividad + " ]";
    }
    
}
