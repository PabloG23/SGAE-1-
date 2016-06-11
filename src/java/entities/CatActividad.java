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
 * @author pablog23
 */
@Entity
@Table(name = "CatActividad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatActividad.findAll", query = "SELECT c FROM CatActividad c"),
    @NamedQuery(name = "CatActividad.findByIdCatAct", query = "SELECT c FROM CatActividad c WHERE c.idCatAct = :idCatAct"),
    @NamedQuery(name = "CatActividad.findByNombre", query = "SELECT c FROM CatActividad c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CatActividad.findByTipo", query = "SELECT c FROM CatActividad c WHERE c.tipo = :tipo")})
public class CatActividad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCatAct")
    private Integer idCatAct;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipo")
    private String tipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "catActividad")
    private Collection<Actividad> actividadCollection;

    public CatActividad() {
    }

    public CatActividad(Integer idCatAct) {
        this.idCatAct = idCatAct;
    }

    public CatActividad(Integer idCatAct, String nombre, String tipo) {
        this.idCatAct = idCatAct;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public Integer getIdCatAct() {
        return idCatAct;
    }

    public void setIdCatAct(Integer idCatAct) {
        this.idCatAct = idCatAct;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @XmlTransient
    public Collection<Actividad> getActividadCollection() {
        return actividadCollection;
    }

    public void setActividadCollection(Collection<Actividad> actividadCollection) {
        this.actividadCollection = actividadCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCatAct != null ? idCatAct.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatActividad)) {
            return false;
        }
        CatActividad other = (CatActividad) object;
        if ((this.idCatAct == null && other.idCatAct != null) || (this.idCatAct != null && !this.idCatAct.equals(other.idCatAct))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CatActividad[ idCatAct=" + idCatAct + " ]";
    }
    
}
