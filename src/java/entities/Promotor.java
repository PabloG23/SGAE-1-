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
@Table(name = "Promotor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Promotor.findAll", query = "SELECT p FROM Promotor p"),
    @NamedQuery(name = "Promotor.findByIdPromotor", query = "SELECT p FROM Promotor p WHERE p.idPromotor = :idPromotor"),
    @NamedQuery(name = "Promotor.findByApeMat", query = "SELECT p FROM Promotor p WHERE p.apeMat = :apeMat"),
    @NamedQuery(name = "Promotor.findByApePat", query = "SELECT p FROM Promotor p WHERE p.apePat = :apePat"),
    @NamedQuery(name = "Promotor.findByNombre", query = "SELECT p FROM Promotor p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Promotor.findByCorreo", query = "SELECT p FROM Promotor p WHERE p.correo = :correo"),
    @NamedQuery(name = "Promotor.findByTelefono", query = "SELECT p FROM Promotor p WHERE p.telefono = :telefono"),
    @NamedQuery(name = "Promotor.findByStatus", query = "SELECT p FROM Promotor p WHERE p.status = :status")})
public class Promotor implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "promotor")
    private Collection<Evento> eventoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "promotor")
    private Collection<Grupo> grupoCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPromotor")
    private Integer idPromotor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ape_mat")
    private String apeMat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ape_pat")
    private String apePat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "correo")
    private String correo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "telefono")
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private boolean status;

    public Promotor() {
    }

    public Promotor(Integer idPromotor) {
        this.idPromotor = idPromotor;
    }

    public Promotor(Integer idPromotor, String apeMat, String apePat, String nombre, String correo, String telefono, boolean status) {
        this.idPromotor = idPromotor;
        this.apeMat = apeMat;
        this.apePat = apePat;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.status = status;
    }

    public Integer getIdPromotor() {
        return idPromotor;
    }

    public void setIdPromotor(Integer idPromotor) {
        this.idPromotor = idPromotor;
    }

    public String getApeMat() {
        return apeMat;
    }

    public void setApeMat(String apeMat) {
        this.apeMat = apeMat;
    }

    public String getApePat() {
        return apePat;
    }

    public void setApePat(String apePat) {
        this.apePat = apePat;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPromotor != null ? idPromotor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Promotor)) {
            return false;
        }
        Promotor other = (Promotor) object;
        if ((this.idPromotor == null && other.idPromotor != null) || (this.idPromotor != null && !this.idPromotor.equals(other.idPromotor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Promotor[ idPromotor=" + idPromotor + " ]";
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
    
}
