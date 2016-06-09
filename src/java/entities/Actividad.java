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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "Actividad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actividad.findAll", query = "SELECT a FROM Actividad a"),
    @NamedQuery(name = "Actividad.findByIdActividad", query = "SELECT a FROM Actividad a WHERE a.idActividad = :idActividad"),
    @NamedQuery(name = "Actividad.findByTipoact", query = "SELECT a FROM Actividad a WHERE a.tipoact = :tipoact"),
    @NamedQuery(name = "Actividad.findByNombreAct", query = "SELECT a FROM Actividad a WHERE a.nombreAct = :nombreAct"),
    @NamedQuery(name = "Actividad.findByA\u00f1o", query = "SELECT a FROM Actividad a WHERE a.a\u00f1o = :a\u00f1o"),
    @NamedQuery(name = "Actividad.findBySemestre", query = "SELECT a FROM Actividad a WHERE a.semestre = :semestre")})
public class Actividad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idActividad")
    private Integer idActividad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipoact")
    private String tipoact;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombreAct")
    private String nombreAct;
    @Basic(optional = false)
    @NotNull
    @Column(name = "a\u00f1o")
    private int año;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "semestre")
    private String semestre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actividad")
    private Collection<CatActividad> catActividadCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actividad")
    private Collection<Grupo> grupoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actividad")
    private Collection<Evento> eventoCollection;

    public Actividad() {
    }

    public Actividad(Integer idActividad) {
        this.idActividad = idActividad;
    }

    public Actividad(Integer idActividad, String tipoact, String nombreAct, int año, String semestre) {
        this.idActividad = idActividad;
        this.tipoact = tipoact;
        this.nombreAct = nombreAct;
        this.año = año;
        this.semestre = semestre;
    }

    public Integer getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Integer idActividad) {
        this.idActividad = idActividad;
    }

    public String getTipoact() {
        return tipoact;
    }

    public void setTipoact(String tipoact) {
        this.tipoact = tipoact;
    }

    public String getNombreAct() {
        return nombreAct;
    }

    public void setNombreAct(String nombreAct) {
        this.nombreAct = nombreAct;
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

    @XmlTransient
    public Collection<CatActividad> getCatActividadCollection() {
        return catActividadCollection;
    }

    public void setCatActividadCollection(Collection<CatActividad> catActividadCollection) {
        this.catActividadCollection = catActividadCollection;
    }

    @XmlTransient
    public Collection<Grupo> getGrupoCollection() {
        return grupoCollection;
    }

    public void setGrupoCollection(Collection<Grupo> grupoCollection) {
        this.grupoCollection = grupoCollection;
    }

    @XmlTransient
    public Collection<Evento> getEventoCollection() {
        return eventoCollection;
    }

    public void setEventoCollection(Collection<Evento> eventoCollection) {
        this.eventoCollection = eventoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActividad != null ? idActividad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actividad)) {
            return false;
        }
        Actividad other = (Actividad) object;
        if ((this.idActividad == null && other.idActividad != null) || (this.idActividad != null && !this.idActividad.equals(other.idActividad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Actividad[ idActividad=" + idActividad + " ]";
    }
    
}
