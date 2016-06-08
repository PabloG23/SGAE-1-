/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author omar
 */
@Entity
@Table(name = "Grupo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grupo.findAll", query = "SELECT g FROM Grupo g"),
    @NamedQuery(name = "Grupo.findByIdGrupo", query = "SELECT g FROM Grupo g WHERE g.grupoPK.idGrupo = :idGrupo"),
    @NamedQuery(name = "Grupo.findByDias", query = "SELECT g FROM Grupo g WHERE g.dias = :dias"),
    @NamedQuery(name = "Grupo.findByHorario", query = "SELECT g FROM Grupo g WHERE g.horario = :horario"),
    @NamedQuery(name = "Grupo.findBySemestre", query = "SELECT g FROM Grupo g WHERE g.semestre = :semestre"),
    @NamedQuery(name = "Grupo.findByA\u00f1o", query = "SELECT g FROM Grupo g WHERE g.a\u00f1o = :a\u00f1o"),
    @NamedQuery(name = "Grupo.findByActividadidActividad", query = "SELECT g FROM Grupo g WHERE g.grupoPK.actividadidActividad = :actividadidActividad"),
    @NamedQuery(name = "Grupo.findByPromotoridPromotor", query = "SELECT g FROM Grupo g WHERE g.grupoPK.promotoridPromotor = :promotoridPromotor")})
public class Grupo implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GrupoPK grupoPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "dias")
    private String dias;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "horario")
    private String horario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "semestre")
    private String semestre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "a\u00f1o")
    private String año;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grupo")
    private Collection<Alumno> alumnoCollection;
    @JoinColumn(name = "Actividad_idActividad", referencedColumnName = "idActividad", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Actividad actividad;
    @JoinColumn(name = "Promotor_idPromotor", referencedColumnName = "idPromotor", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Promotor promotor;

    public Grupo() {
    }

    public Grupo(GrupoPK grupoPK) {
        this.grupoPK = grupoPK;
    }

    public Grupo(GrupoPK grupoPK, String dias, String horario, String semestre, String año) {
        this.grupoPK = grupoPK;
        this.dias = dias;
        this.horario = horario;
        this.semestre = semestre;
        this.año = año;
    }

    public Grupo(int idGrupo, int actividadidActividad, int promotoridPromotor) {
        this.grupoPK = new GrupoPK(idGrupo, actividadidActividad, promotoridPromotor);
    }

    public GrupoPK getGrupoPK() {
        return grupoPK;
    }

    public void setGrupoPK(GrupoPK grupoPK) {
        this.grupoPK = grupoPK;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    @XmlTransient
    public Collection<Alumno> getAlumnoCollection() {
        return alumnoCollection;
    }

    public void setAlumnoCollection(Collection<Alumno> alumnoCollection) {
        this.alumnoCollection = alumnoCollection;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public Promotor getPromotor() {
        return promotor;
    }

    public void setPromotor(Promotor promotor) {
        this.promotor = promotor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (grupoPK != null ? grupoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupo)) {
            return false;
        }
        Grupo other = (Grupo) object;
        if ((this.grupoPK == null && other.grupoPK != null) || (this.grupoPK != null && !this.grupoPK.equals(other.grupoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Grupo[ grupoPK=" + grupoPK + " ]";
    }
    
}
