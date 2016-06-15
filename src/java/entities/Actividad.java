/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Actividad implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idActividad")
    private Integer idActividad;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "a\u00f1o")
    private int año;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "semestre")
    private String semestre;
    
    @JoinColumn(name = "CatActividad_idCatAct", referencedColumnName = "idCatAct", insertable = true, updatable = true)
    @ManyToOne(optional = false)
    private CatActividad catActividad;

    public Actividad() {
    }

    public int getIdactividad() {
        return idActividad;
    }

    public void setIdactividad(int idactividad) {
        this.idActividad = idactividad;
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
        if ((this.idActividad == null && other.idActividad != null) || 
                (this.idActividad != null && !this.idActividad.equals(other.idActividad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Actividad[ idActividad=" + idActividad + " ]";
    }
    
}
