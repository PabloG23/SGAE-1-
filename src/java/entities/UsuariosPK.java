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
public class UsuariosPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idUsua")
    private int idUsua;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Alumno_noCtrl")
    private int alumnonoCtrl;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Promotor_idPromotor")
    private int promotoridPromotor;

    public UsuariosPK() {
    }

    public UsuariosPK(int idUsua, int alumnonoCtrl, int promotoridPromotor) {
        this.idUsua = idUsua;
        this.alumnonoCtrl = alumnonoCtrl;
        this.promotoridPromotor = promotoridPromotor;
    }

    public int getIdUsua() {
        return idUsua;
    }

    public void setIdUsua(int idUsua) {
        this.idUsua = idUsua;
    }

    public int getAlumnonoCtrl() {
        return alumnonoCtrl;
    }

    public void setAlumnonoCtrl(int alumnonoCtrl) {
        this.alumnonoCtrl = alumnonoCtrl;
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
        hash += (int) idUsua;
        hash += (int) alumnonoCtrl;
        hash += (int) promotoridPromotor;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuariosPK)) {
            return false;
        }
        UsuariosPK other = (UsuariosPK) object;
        if (this.idUsua != other.idUsua) {
            return false;
        }
        if (this.alumnonoCtrl != other.alumnonoCtrl) {
            return false;
        }
        if (this.promotoridPromotor != other.promotoridPromotor) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.UsuariosPK[ idUsua=" + idUsua + ", alumnonoCtrl=" + alumnonoCtrl + ", promotoridPromotor=" + promotoridPromotor + " ]";
    }
    
}
