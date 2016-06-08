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
    @NamedQuery(name = "Actividad_1.findAll", query = "SELECT a FROM Actividad_1 a"),
    @NamedQuery(name = "Actividad_1.findByIdActividad", query = "SELECT a FROM Actividad_1 a WHERE a.idActividad = :idActividad"),
    @NamedQuery(name = "Actividad_1.findByTipo", query = "SELECT a FROM Actividad_1 a WHERE a.tipo = :tipo"),
    @NamedQuery(name = "Actividad_1.findByNombreAct", query = "SELECT a FROM Actividad_1 a WHERE a.nombreAct = :nombreAct"),
    @NamedQuery(name = "Actividad_1.findByA\u00f1o", query = "SELECT a FROM Actividad_1 a WHERE a.a\u00f1o = :a\u00f1o"),
    @NamedQuery(name = "Actividad_1.findBySemestre", query = "SELECT a FROM Actividad_1 a WHERE a.semestre = :semestre")})
public class Actividad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idActividad")
    private Integer idActividad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipo")
    private String tipo;
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

    public Actividad(Integer idActividad, String tipo, String nombreAct, int año, String semestre) {
        this.idActividad = idActividad;
        this.tipo = tipo;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
        return "entities.Actividad_1[ idActividad=" + idActividad + " ]";
    }
    
}
