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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author omar
 */
@Entity
@Table(name = "Grupo_usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grupousuarios.findAll", query = "SELECT g FROM Grupousuarios g"),
    @NamedQuery(name = "Grupousuarios.findByIdGrupousuarios", query = "SELECT g FROM Grupousuarios g WHERE g.idGrupousuarios = :idGrupousuarios"),
    @NamedQuery(name = "Grupousuarios.findByUsuario", query = "SELECT g FROM Grupousuarios g WHERE g.usuario = :usuario"),
    @NamedQuery(name = "Grupousuarios.findByNombregrupo", query = "SELECT g FROM Grupousuarios g WHERE g.nombregrupo = :nombregrupo")})
public class Grupousuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idGrupo_usuarios")
    private Integer idGrupousuarios;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombregrupo")
    private String nombregrupo;
    @JoinColumn(name = "Usuarios_idUsua", referencedColumnName = "idUsua")
    @ManyToOne(optional = false)
    private Usuarios usuariosidUsua;

    public Grupousuarios() {
    }

    public Grupousuarios(Integer idGrupousuarios) {
        this.idGrupousuarios = idGrupousuarios;
    }

    public Grupousuarios(Integer idGrupousuarios, String usuario, String nombregrupo) {
        this.idGrupousuarios = idGrupousuarios;
        this.usuario = usuario;
        this.nombregrupo = nombregrupo;
    }

    public Integer getIdGrupousuarios() {
        return idGrupousuarios;
    }

    public void setIdGrupousuarios(Integer idGrupousuarios) {
        this.idGrupousuarios = idGrupousuarios;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombregrupo() {
        return nombregrupo;
    }

    public void setNombregrupo(String nombregrupo) {
        this.nombregrupo = nombregrupo;
    }

    public Usuarios getUsuariosidUsua() {
        return usuariosidUsua;
    }

    public void setUsuariosidUsua(Usuarios usuariosidUsua) {
        this.usuariosidUsua = usuariosidUsua;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGrupousuarios != null ? idGrupousuarios.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupousuarios)) {
            return false;
        }
        Grupousuarios other = (Grupousuarios) object;
        if ((this.idGrupousuarios == null && other.idGrupousuarios != null) || (this.idGrupousuarios != null && !this.idGrupousuarios.equals(other.idGrupousuarios))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Grupousuarios[ idGrupousuarios=" + idGrupousuarios + " ]";
    }
    
}
