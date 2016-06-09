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
public class SeleccionadosReconocimientosPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idSeleccionadosReconocimientos")
    private int idSeleccionadosReconocimientos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Alumno_noCtrl")
    private int alumnonoCtrl;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Alumno_Grupo_idGrupo1")
    private int alumnoGrupoidGrupo1;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Alumno_Grupo_Actividad_idActividad1")
    private int alumnoGrupoActividadidActividad1;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Alumno_Grupo_Promotor_idPromotor1")
    private int alumnoGrupoPromotoridPromotor1;

    public SeleccionadosReconocimientosPK() {
    }

    public SeleccionadosReconocimientosPK(int idSeleccionadosReconocimientos, int alumnonoCtrl, int alumnoGrupoidGrupo1, int alumnoGrupoActividadidActividad1, int alumnoGrupoPromotoridPromotor1) {
        this.idSeleccionadosReconocimientos = idSeleccionadosReconocimientos;
        this.alumnonoCtrl = alumnonoCtrl;
        this.alumnoGrupoidGrupo1 = alumnoGrupoidGrupo1;
        this.alumnoGrupoActividadidActividad1 = alumnoGrupoActividadidActividad1;
        this.alumnoGrupoPromotoridPromotor1 = alumnoGrupoPromotoridPromotor1;
    }

    public int getIdSeleccionadosReconocimientos() {
        return idSeleccionadosReconocimientos;
    }

    public void setIdSeleccionadosReconocimientos(int idSeleccionadosReconocimientos) {
        this.idSeleccionadosReconocimientos = idSeleccionadosReconocimientos;
    }

    public int getAlumnonoCtrl() {
        return alumnonoCtrl;
    }

    public void setAlumnonoCtrl(int alumnonoCtrl) {
        this.alumnonoCtrl = alumnonoCtrl;
    }

    public int getAlumnoGrupoidGrupo1() {
        return alumnoGrupoidGrupo1;
    }

    public void setAlumnoGrupoidGrupo1(int alumnoGrupoidGrupo1) {
        this.alumnoGrupoidGrupo1 = alumnoGrupoidGrupo1;
    }

    public int getAlumnoGrupoActividadidActividad1() {
        return alumnoGrupoActividadidActividad1;
    }

    public void setAlumnoGrupoActividadidActividad1(int alumnoGrupoActividadidActividad1) {
        this.alumnoGrupoActividadidActividad1 = alumnoGrupoActividadidActividad1;
    }

    public int getAlumnoGrupoPromotoridPromotor1() {
        return alumnoGrupoPromotoridPromotor1;
    }

    public void setAlumnoGrupoPromotoridPromotor1(int alumnoGrupoPromotoridPromotor1) {
        this.alumnoGrupoPromotoridPromotor1 = alumnoGrupoPromotoridPromotor1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idSeleccionadosReconocimientos;
        hash += (int) alumnonoCtrl;
        hash += (int) alumnoGrupoidGrupo1;
        hash += (int) alumnoGrupoActividadidActividad1;
        hash += (int) alumnoGrupoPromotoridPromotor1;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeleccionadosReconocimientosPK)) {
            return false;
        }
        SeleccionadosReconocimientosPK other = (SeleccionadosReconocimientosPK) object;
        if (this.idSeleccionadosReconocimientos != other.idSeleccionadosReconocimientos) {
            return false;
        }
        if (this.alumnonoCtrl != other.alumnonoCtrl) {
            return false;
        }
        if (this.alumnoGrupoidGrupo1 != other.alumnoGrupoidGrupo1) {
            return false;
        }
        if (this.alumnoGrupoActividadidActividad1 != other.alumnoGrupoActividadidActividad1) {
            return false;
        }
        if (this.alumnoGrupoPromotoridPromotor1 != other.alumnoGrupoPromotoridPromotor1) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.SeleccionadosReconocimientosPK[ idSeleccionadosReconocimientos=" + idSeleccionadosReconocimientos + ", alumnonoCtrl=" + alumnonoCtrl + ", alumnoGrupoidGrupo1=" + alumnoGrupoidGrupo1 + ", alumnoGrupoActividadidActividad1=" + alumnoGrupoActividadidActividad1 + ", alumnoGrupoPromotoridPromotor1=" + alumnoGrupoPromotoridPromotor1 + " ]";
    }
    
}
