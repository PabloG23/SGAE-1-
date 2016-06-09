/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
    @NamedQuery(name = "CatActividad.findByIdCatAct", query = "SELECT c FROM CatActividad c WHERE c.catActividadPK.idCatAct = :idCatAct"),
    @NamedQuery(name = "CatActividad.findByNombre", query = "SELECT c FROM CatActividad c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CatActividad.findByActividadidActividad", query = "SELECT c FROM CatActividad c WHERE c.catActividadPK.actividadidActividad = :actividadidActividad"),
    @NamedQuery(name = "CatActividad.findByTipo", query = "SELECT c FROM CatActividad c WHERE c.tipo = :tipo")})
public class CatActividad implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CatActividadPK catActividadPK;
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
    @JoinTable(name = "Promotor_has_CatActividad", joinColumns = {
        @JoinColumn(name = "CatActividad_idCatAct", referencedColumnName = "idCatAct")}, inverseJoinColumns = {
        @JoinColumn(name = "Promotor_idPromotor", referencedColumnName = "idPromotor")})
    @ManyToMany
    private Collection<Promotor> promotorCollection;
    @JoinColumn(name = "Actividad_idActividad", referencedColumnName = "idActividad", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Actividad actividad;

    public CatActividad() {
    }

    public CatActividad(CatActividadPK catActividadPK) {
        this.catActividadPK = catActividadPK;
    }

    public CatActividad(CatActividadPK catActividadPK, String nombre, String tipo) {
        this.catActividadPK = catActividadPK;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public CatActividad(int idCatAct, int actividadidActividad) {
        this.catActividadPK = new CatActividadPK(idCatAct, actividadidActividad);
    }

    public CatActividadPK getCatActividadPK() {
        return catActividadPK;
    }

    public void setCatActividadPK(CatActividadPK catActividadPK) {
        this.catActividadPK = catActividadPK;
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
    public Collection<Promotor> getPromotorCollection() {
        return promotorCollection;
    }

    public void setPromotorCollection(Collection<Promotor> promotorCollection) {
        this.promotorCollection = promotorCollection;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (catActividadPK != null ? catActividadPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatActividad)) {
            return false;
        }
        CatActividad other = (CatActividad) object;
        if ((this.catActividadPK == null && other.catActividadPK != null) || (this.catActividadPK != null && !this.catActividadPK.equals(other.catActividadPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CatActividad[ catActividadPK=" + catActividadPK + " ]";
    }
    
}
