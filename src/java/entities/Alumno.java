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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "Alumno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alumno.findAll", query = "SELECT a FROM Alumno a"),
    @NamedQuery(name = "Alumno.findByNoCtrl", query = "SELECT a FROM Alumno a WHERE a.alumnoPK.noCtrl = :noCtrl"),
    @NamedQuery(name = "Alumno.findByApPat", query = "SELECT a FROM Alumno a WHERE a.apPat = :apPat"),
    @NamedQuery(name = "Alumno.findByApMat", query = "SELECT a FROM Alumno a WHERE a.apMat = :apMat"),
    @NamedQuery(name = "Alumno.findByNombre", query = "SELECT a FROM Alumno a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Alumno.findByCalificacion", query = "SELECT a FROM Alumno a WHERE a.calificacion = :calificacion"),
    @NamedQuery(name = "Alumno.findByEdad", query = "SELECT a FROM Alumno a WHERE a.edad = :edad"),
    @NamedQuery(name = "Alumno.findBySexo", query = "SELECT a FROM Alumno a WHERE a.sexo = :sexo"),
    @NamedQuery(name = "Alumno.findByGrupoidGrupo1", query = "SELECT a FROM Alumno a WHERE a.alumnoPK.grupoidGrupo1 = :grupoidGrupo1"),
    @NamedQuery(name = "Alumno.findByGrupoActividadidActividad1", query = "SELECT a FROM Alumno a WHERE a.alumnoPK.grupoActividadidActividad1 = :grupoActividadidActividad1"),
    @NamedQuery(name = "Alumno.findByGrupoPromotoridPromotor1", query = "SELECT a FROM Alumno a WHERE a.alumnoPK.grupoPromotoridPromotor1 = :grupoPromotoridPromotor1")})
public class Alumno implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AlumnoPK alumnoPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "apPat")
    private String apPat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "apMat")
    private String apMat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "calificacion")
    private String calificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "edad")
    private int edad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "sexo")
    private String sexo;
    @JoinTable(name = "Alumno_has_CapDif", joinColumns = {
        @JoinColumn(name = "Alumno_noCtrl", referencedColumnName = "noCtrl"),
        @JoinColumn(name = "Alumno_Grupo_idGrupo1", referencedColumnName = "Grupo_idGrupo1"),
        @JoinColumn(name = "Alumno_Grupo_Actividad_idActividad1", referencedColumnName = "Grupo_Actividad_idActividad1"),
        @JoinColumn(name = "Alumno_Grupo_Promotor_idPromotor1", referencedColumnName = "Grupo_Promotor_idPromotor1")}, inverseJoinColumns = {
        @JoinColumn(name = "CapDif_idCapDif", referencedColumnName = "idCapDif")})
    @ManyToMany
    private Collection<CapDif> capDifCollection;
    @JoinTable(name = "Alumno_has_CatCarreras", joinColumns = {
        @JoinColumn(name = "Alumno_noCtrl", referencedColumnName = "noCtrl"),
        @JoinColumn(name = "Alumno_Grupo_idGrupo1", referencedColumnName = "Grupo_idGrupo1"),
        @JoinColumn(name = "Alumno_Grupo_Actividad_idActividad1", referencedColumnName = "Grupo_Actividad_idActividad1"),
        @JoinColumn(name = "Alumno_Grupo_Promotor_idPromotor1", referencedColumnName = "Grupo_Promotor_idPromotor1")}, inverseJoinColumns = {
        @JoinColumn(name = "CatCarreras_idCatCarreras", referencedColumnName = "idCatCarreras")})
    @ManyToMany
    private Collection<CatCarreras> catCarrerasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alumno")
    private Collection<Usuarios> usuariosCollection;
    @JoinColumns({
        @JoinColumn(name = "Grupo_idGrupo1", referencedColumnName = "idGrupo", insertable = false, updatable = false),
        @JoinColumn(name = "Grupo_Actividad_idActividad1", referencedColumnName = "Actividad_idActividad", insertable = false, updatable = false),
        @JoinColumn(name = "Grupo_Promotor_idPromotor1", referencedColumnName = "Promotor_idPromotor", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Grupo grupo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alumno")
    private Collection<SeleccionadosReconocimientos> seleccionadosReconocimientosCollection;

    public Alumno() {
    }

    public Alumno(AlumnoPK alumnoPK) {
        this.alumnoPK = alumnoPK;
    }

    public Alumno(AlumnoPK alumnoPK, String apPat, String apMat, String nombre, String calificacion, int edad, String sexo) {
        this.alumnoPK = alumnoPK;
        this.apPat = apPat;
        this.apMat = apMat;
        this.nombre = nombre;
        this.calificacion = calificacion;
        this.edad = edad;
        this.sexo = sexo;
    }

    public Alumno(int noCtrl, int grupoidGrupo1, int grupoActividadidActividad1, int grupoPromotoridPromotor1) {
        this.alumnoPK = new AlumnoPK(noCtrl, grupoidGrupo1, grupoActividadidActividad1, grupoPromotoridPromotor1);
    }

    public AlumnoPK getAlumnoPK() {
        return alumnoPK;
    }

    public void setAlumnoPK(AlumnoPK alumnoPK) {
        this.alumnoPK = alumnoPK;
    }

    public String getApPat() {
        return apPat;
    }

    public void setApPat(String apPat) {
        this.apPat = apPat;
    }

    public String getApMat() {
        return apMat;
    }

    public void setApMat(String apMat) {
        this.apMat = apMat;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @XmlTransient
    public Collection<CapDif> getCapDifCollection() {
        return capDifCollection;
    }

    public void setCapDifCollection(Collection<CapDif> capDifCollection) {
        this.capDifCollection = capDifCollection;
    }

    @XmlTransient
    public Collection<CatCarreras> getCatCarrerasCollection() {
        return catCarrerasCollection;
    }

    public void setCatCarrerasCollection(Collection<CatCarreras> catCarrerasCollection) {
        this.catCarrerasCollection = catCarrerasCollection;
    }

    @XmlTransient
    public Collection<Usuarios> getUsuariosCollection() {
        return usuariosCollection;
    }

    public void setUsuariosCollection(Collection<Usuarios> usuariosCollection) {
        this.usuariosCollection = usuariosCollection;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    @XmlTransient
    public Collection<SeleccionadosReconocimientos> getSeleccionadosReconocimientosCollection() {
        return seleccionadosReconocimientosCollection;
    }

    public void setSeleccionadosReconocimientosCollection(Collection<SeleccionadosReconocimientos> seleccionadosReconocimientosCollection) {
        this.seleccionadosReconocimientosCollection = seleccionadosReconocimientosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (alumnoPK != null ? alumnoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alumno)) {
            return false;
        }
        Alumno other = (Alumno) object;
        if ((this.alumnoPK == null && other.alumnoPK != null) || (this.alumnoPK != null && !this.alumnoPK.equals(other.alumnoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Alumno[ alumnoPK=" + alumnoPK + " ]";
    }
    
}
