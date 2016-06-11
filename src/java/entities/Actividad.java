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
@Table(name = "Actividad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actividad.findAll", query = "SELECT a FROM Actividad a"),
    @NamedQuery(name = "Actividad.findByIdActividad", query = "SELECT a FROM Actividad a WHERE a.actividadPK.idActividad = :idActividad"),
    @NamedQuery(name = "Actividad.findByA\u00f1o", query = "SELECT a FROM Actividad a WHERE a.a\u00f1o = :a\u00f1o"),
    @NamedQuery(name = "Actividad.findBySemestre", query = "SELECT a FROM Actividad a WHERE a.semestre = :semestre"),
    @NamedQuery(name = "Actividad.findByCatActividadidCatAct", query = "SELECT a FROM Actividad a WHERE a.actividadPK.catActividadidCatAct = :catActividadidCatAct")})
public class Actividad implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ActividadPK actividadPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "a\u00f1o")
    private int año;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "semestre")
    private String semestre;
    @JoinColumn(name = "CatActividad_idCatAct", referencedColumnName = "idCatAct", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CatActividad catActividad;

    public Actividad() {
    }

    public Actividad(ActividadPK actividadPK) {
        this.actividadPK = actividadPK;
    }

    public Actividad(ActividadPK actividadPK, int año, String semestre) {
        this.actividadPK = actividadPK;
        this.año = año;
        this.semestre = semestre;
    }

    public Actividad(int idActividad, int catActividadidCatAct) {
        this.actividadPK = new ActividadPK(idActividad, catActividadidCatAct);
    }

    public ActividadPK getActividadPK() {
        return actividadPK;
    }

    public void setActividadPK(ActividadPK actividadPK) {
        this.actividadPK = actividadPK;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public CatActividad getCatActividad() {
        return catActividad;
    }

    public void setCatActividad(CatActividad catActividad) {
        this.catActividad = catActividad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (actividadPK != null ? actividadPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actividad)) {
            return false;
        }
        Actividad other = (Actividad) object;
        if ((this.actividadPK == null && other.actividadPK != null) || (this.actividadPK != null && !this.actividadPK.equals(other.actividadPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Actividad[ actividadPK=" + actividadPK + " ]";
    }
    
}
