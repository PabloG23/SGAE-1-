/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pablog23
 */
@Entity
@Table(name = "SeleccionadosReconocimientos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SeleccionadosReconocimientos.findAll", query = "SELECT s FROM SeleccionadosReconocimientos s"),
    @NamedQuery(name = "SeleccionadosReconocimientos.findByIdSeleccionadosReconocimientos", query = "SELECT s FROM SeleccionadosReconocimientos s WHERE s.seleccionadosReconocimientosPK.idSeleccionadosReconocimientos = :idSeleccionadosReconocimientos"),
    @NamedQuery(name = "SeleccionadosReconocimientos.findBySemestre", query = "SELECT s FROM SeleccionadosReconocimientos s WHERE s.semestre = :semestre"),
    @NamedQuery(name = "SeleccionadosReconocimientos.findByA\u00f1o", query = "SELECT s FROM SeleccionadosReconocimientos s WHERE s.a\u00f1o = :a\u00f1o"),
    @NamedQuery(name = "SeleccionadosReconocimientos.findByNoCtrl", query = "SELECT s FROM SeleccionadosReconocimientos s WHERE s.noCtrl = :noCtrl"),
    @NamedQuery(name = "SeleccionadosReconocimientos.findByAlumnonoCtrl", query = "SELECT s FROM SeleccionadosReconocimientos s WHERE s.seleccionadosReconocimientosPK.alumnonoCtrl = :alumnonoCtrl"),
    @NamedQuery(name = "SeleccionadosReconocimientos.findByAlumnoGrupoidGrupo1", query = "SELECT s FROM SeleccionadosReconocimientos s WHERE s.seleccionadosReconocimientosPK.alumnoGrupoidGrupo1 = :alumnoGrupoidGrupo1"),
    @NamedQuery(name = "SeleccionadosReconocimientos.findByAlumnoGrupoActividadidActividad1", query = "SELECT s FROM SeleccionadosReconocimientos s WHERE s.seleccionadosReconocimientosPK.alumnoGrupoActividadidActividad1 = :alumnoGrupoActividadidActividad1"),
    @NamedQuery(name = "SeleccionadosReconocimientos.findByAlumnoGrupoPromotoridPromotor1", query = "SELECT s FROM SeleccionadosReconocimientos s WHERE s.seleccionadosReconocimientosPK.alumnoGrupoPromotoridPromotor1 = :alumnoGrupoPromotoridPromotor1")})
public class SeleccionadosReconocimientos implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SeleccionadosReconocimientosPK seleccionadosReconocimientosPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "semestre")
    private int semestre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "a\u00f1o")
    private String año;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "noCtrl")
    private String noCtrl;
    @JoinColumns({
        @JoinColumn(name = "Alumno_noCtrl", referencedColumnName = "noCtrl", insertable = false, updatable = false),
        @JoinColumn(name = "Alumno_Grupo_idGrupo1", referencedColumnName = "Grupo_idGrupo1", insertable = false, updatable = false),
        @JoinColumn(name = "Alumno_Grupo_Actividad_idActividad1", referencedColumnName = "Grupo_Actividad_idActividad1", insertable = false, updatable = false),
        @JoinColumn(name = "Alumno_Grupo_Promotor_idPromotor1", referencedColumnName = "Grupo_Promotor_idPromotor1", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Alumno alumno;

    public SeleccionadosReconocimientos() {
    }

    public SeleccionadosReconocimientos(SeleccionadosReconocimientosPK seleccionadosReconocimientosPK) {
        this.seleccionadosReconocimientosPK = seleccionadosReconocimientosPK;
    }

    public SeleccionadosReconocimientos(SeleccionadosReconocimientosPK seleccionadosReconocimientosPK, int semestre, String año, String noCtrl) {
        this.seleccionadosReconocimientosPK = seleccionadosReconocimientosPK;
        this.semestre = semestre;
        this.año = año;
        this.noCtrl = noCtrl;
    }

    public SeleccionadosReconocimientos(int idSeleccionadosReconocimientos, int alumnonoCtrl, int alumnoGrupoidGrupo1, int alumnoGrupoActividadidActividad1, int alumnoGrupoPromotoridPromotor1) {
        this.seleccionadosReconocimientosPK = new SeleccionadosReconocimientosPK(idSeleccionadosReconocimientos, alumnonoCtrl, alumnoGrupoidGrupo1, alumnoGrupoActividadidActividad1, alumnoGrupoPromotoridPromotor1);
    }

    public SeleccionadosReconocimientosPK getSeleccionadosReconocimientosPK() {
        return seleccionadosReconocimientosPK;
    }

    public void setSeleccionadosReconocimientosPK(SeleccionadosReconocimientosPK seleccionadosReconocimientosPK) {
        this.seleccionadosReconocimientosPK = seleccionadosReconocimientosPK;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public String getNoCtrl() {
        return noCtrl;
    }

    public void setNoCtrl(String noCtrl) {
        this.noCtrl = noCtrl;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seleccionadosReconocimientosPK != null ? seleccionadosReconocimientosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeleccionadosReconocimientos)) {
            return false;
        }
        SeleccionadosReconocimientos other = (SeleccionadosReconocimientos) object;
        if ((this.seleccionadosReconocimientosPK == null && other.seleccionadosReconocimientosPK != null) || (this.seleccionadosReconocimientosPK != null && !this.seleccionadosReconocimientosPK.equals(other.seleccionadosReconocimientosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.SeleccionadosReconocimientos[ seleccionadosReconocimientosPK=" + seleccionadosReconocimientosPK + " ]";
    }
    
}
