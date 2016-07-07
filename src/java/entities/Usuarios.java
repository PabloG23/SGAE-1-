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
@Table(name = "Usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u"),
    @NamedQuery(name = "Usuarios.findByIdUsua", query = "SELECT u FROM Usuarios u WHERE u.idUsua = :idUsua"),
    @NamedQuery(name = "Usuarios.findByUsuario", query = "SELECT u FROM Usuarios u WHERE u.usuario = :usuario"),
    @NamedQuery(name = "Usuarios.findByContrase\u00f1a", query = "SELECT u FROM Usuarios u WHERE u.contrase\u00f1a = :contrase\u00f1a"),
    @NamedQuery(name = "Usuarios.findByStatus", query = "SELECT u FROM Usuarios u WHERE u.status = :status"),
    @NamedQuery(name = "Usuarios.findByTiposUsuario", query = "SELECT u FROM Usuarios u WHERE u.tiposUsuario = :tiposUsuario")})
public class Usuarios implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuariosidUsua")
    private Collection<Grupousuarios> grupousuariosCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUsua")
    private Integer idUsua;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "contrase\u00f1a")
    private String contraseña;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipos_usuario")
    private String tiposUsuario;
    @JoinColumn(name = "Promotor_idPromotor", referencedColumnName = "idPromotor")
    @ManyToOne
    private Promotor promotoridPromotor;

    public Usuarios() {
    }

    public Usuarios(Integer idUsua) {
        this.idUsua = idUsua;
    }

    public Usuarios(Integer idUsua, String usuario, String contraseña, String status, String tiposUsuario) {
        this.idUsua = idUsua;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.status = status;
        this.tiposUsuario = tiposUsuario;
    }

    public Integer getIdUsua() {
        return idUsua;
    }

    public void setIdUsua(Integer idUsua) {
        this.idUsua = idUsua;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTiposUsuario() {
        return tiposUsuario;
    }

    public void setTiposUsuario(String tiposUsuario) {
        this.tiposUsuario = tiposUsuario;
    }

    public Promotor getPromotoridPromotor() {
        return promotoridPromotor;
    }

    public void setPromotoridPromotor(Promotor promotoridPromotor) {
        this.promotoridPromotor = promotoridPromotor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsua != null ? idUsua.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.idUsua == null && other.idUsua != null) || (this.idUsua != null && !this.idUsua.equals(other.idUsua))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Usuarios[ idUsua=" + idUsua + " ]";
    }

    @XmlTransient
    public Collection<Grupousuarios> getGrupousuariosCollection() {
        return grupousuariosCollection;
    }

    public void setGrupousuariosCollection(Collection<Grupousuarios> grupousuariosCollection) {
        this.grupousuariosCollection = grupousuariosCollection;
    }
    
}
