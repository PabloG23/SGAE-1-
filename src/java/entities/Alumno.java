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
 * @author pablog23
 */
@Entity
@Table(name = "Alumno")
@XmlRootElement

public class Alumno implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "noCtrl")
    private Integer noCtrl;
    
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
    @Size(min = 1, max = 45)
    @Column(name = "semestre")
    private String semestre;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "reincidencia")
    private String reincidencia;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "acreditado")
    private boolean acreditado;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "noAcreditado")
    private boolean noAcreditado;
    
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "edad")
    private int edad;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "sexo")
    private String sexo;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "seleccionado")
    private boolean seleccionado;
   
    @Basic(optional = false)
    @NotNull
    @Column(name = "reconocimiento")
    private boolean reconocimiento;
    
    @JoinColumn(name = "Grupo_idGrupo1", referencedColumnName = "idGrupo")
    @ManyToOne(optional = false)
    private Grupo grupo;
    
    @JoinColumn(name = "CatCarreras_idCatCarreras", referencedColumnName = "idCatCarreras")
    @ManyToOne(optional = false)
    private CatCarreras catCarreras;
    
    @JoinColumn(name = "CapDif_idCapDif", referencedColumnName = "idCapDif")
    @ManyToOne(optional = false)
    private CapDif capDif;

    public Alumno() {
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

    public boolean isAcreditado() {
        return acreditado;
    }

    public void setAcreditado(boolean acreditado) {
        this.acreditado = acreditado;
    }

    public boolean isNoAcreditado() {
        return noAcreditado;
    }

    public void setNoAcreditado(boolean noAcreditado) {
        this.noAcreditado = noAcreditado;
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

    public boolean getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    public boolean getReconocimiento() {
        return reconocimiento;
    }

    public void setReconocimiento(boolean reconocimiento) {
        this.reconocimiento = reconocimiento;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public CatCarreras getCatCarreras() {
        return catCarreras;
    }

    public void setCatCarreras(CatCarreras catCarreras) {
        this.catCarreras = catCarreras;
    }

    public Integer getNoCtrl() {
        return noCtrl;
    }

    public void setNoCtrl(Integer noCtrl) {
        this.noCtrl = noCtrl;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getReincidencia() {
        return reincidencia;
    }

    public void setReincidencia(String reincidencia) {
        this.reincidencia = reincidencia;
    }

    public CapDif getCapDif() {
        return capDif;
    }

    public void setCapDif(CapDif capDif) {
        this.capDif = capDif;
    }

    
    

    
    
}
