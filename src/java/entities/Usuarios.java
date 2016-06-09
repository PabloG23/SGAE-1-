/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
 * @author pablog23
 */
@Entity
@Table(name = "Usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u"),
    @NamedQuery(name = "Usuarios.findByIdUsua", query = "SELECT u FROM Usuarios u WHERE u.usuariosPK.idUsua = :idUsua"),
    @NamedQuery(name = "Usuarios.findByUsuario", query = "SELECT u FROM Usuarios u WHERE u.usuario = :usuario"),
    @NamedQuery(name = "Usuarios.findByContrase\u00f1a", query = "SELECT u FROM Usuarios u WHERE u.contrase\u00f1a = :contrase\u00f1a"),
    @NamedQuery(name = "Usuarios.findByStatus", query = "SELECT u FROM Usuarios u WHERE u.status = :status"),
    @NamedQuery(name = "Usuarios.findByTiposUsuario", query = "SELECT u FROM Usuarios u WHERE u.tiposUsuario = :tiposUsuario"),
    @NamedQuery(name = "Usuarios.findByAlumnonoCtrl", query = "SELECT u FROM Usuarios u WHERE u.usuariosPK.alumnonoCtrl = :alumnonoCtrl"),
    @NamedQuery(name = "Usuarios.findByPromotoridPromotor", query = "SELECT u FROM Usuarios u WHERE u.usuariosPK.promotoridPromotor = :promotoridPromotor")})
public class Usuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsuariosPK usuariosPK;
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
    @Column(name = "tipos_usuario")
    private int tiposUsuario;
    @JoinColumn(name = "Promotor_idPromotor", referencedColumnName = "idPromotor", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Promotor promotor;
    @JoinColumn(name = "Alumno_noCtrl", referencedColumnName = "noCtrl", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Alumno alumno;

    public Usuarios() {
    }

    public Usuarios(UsuariosPK usuariosPK) {
        this.usuariosPK = usuariosPK;
    }

    public Usuarios(UsuariosPK usuariosPK, String usuario, String contraseña, String status, int tiposUsuario) {
        this.usuariosPK = usuariosPK;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.status = status;
        this.tiposUsuario = tiposUsuario;
    }

    public Usuarios(int idUsua, int alumnonoCtrl, int promotoridPromotor) {
        this.usuariosPK = new UsuariosPK(idUsua, alumnonoCtrl, promotoridPromotor);
    }

    public UsuariosPK getUsuariosPK() {
        return usuariosPK;
    }

    public void setUsuariosPK(UsuariosPK usuariosPK) {
        this.usuariosPK = usuariosPK;
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

    public int getTiposUsuario() {
        return tiposUsuario;
    }

    public void setTiposUsuario(int tiposUsuario) {
        this.tiposUsuario = tiposUsuario;
    }

    public Promotor getPromotor() {
        return promotor;
    }

    public void setPromotor(Promotor promotor) {
        this.promotor = promotor;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuariosPK != null ? usuariosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.usuariosPK == null && other.usuariosPK != null) || (this.usuariosPK != null && !this.usuariosPK.equals(other.usuariosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Usuarios[ usuariosPK=" + usuariosPK + " ]";
    }
    
}
