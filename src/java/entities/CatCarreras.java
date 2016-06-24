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
 * @author pablog23
 */
@Entity
@Table(name = "CatCarreras")
@XmlRootElement

public class CatCarreras implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idCatCarreras")
    private Integer idCatCarreras;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nomCarrera")
    private String nomCarrera;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "catCarreras")
    private Collection<Alumno> alumnoCollection;

    public CatCarreras() {
    }

    public CatCarreras(Integer idCatCarreras) {
        this.idCatCarreras = idCatCarreras;
    }

    public CatCarreras(Integer idCatCarreras, String nomCarrera) {
        this.idCatCarreras = idCatCarreras;
        this.nomCarrera = nomCarrera;
    }

    public Integer getIdCatCarreras() {
        return idCatCarreras;
    }

    public void setIdCatCarreras(Integer idCatCarreras) {
        this.idCatCarreras = idCatCarreras;
    }

    public String getNomCarrera() {
        return nomCarrera;
    }

    public void setNomCarrera(String nomCarrera) {
        this.nomCarrera = nomCarrera;
    }

    @XmlTransient
    public Collection<Alumno> getAlumnoCollection() {
        return alumnoCollection;
    }

    public void setAlumnoCollection(Collection<Alumno> alumnoCollection) {
        this.alumnoCollection = alumnoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCatCarreras != null ? idCatCarreras.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatCarreras)) {
            return false;
        }
        CatCarreras other = (CatCarreras) object;
        if ((this.idCatCarreras == null && other.idCatCarreras != null) || (this.idCatCarreras != null && !this.idCatCarreras.equals(other.idCatCarreras))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CatCarreras[ idCatCarreras=" + idCatCarreras + " ]";
    }
    
}
