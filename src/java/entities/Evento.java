/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author omar
 */
@Entity
@Table(name = "Evento")
@XmlRootElement

public class Evento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEvento")
    private Integer idEvento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipo_evento")
    private String tipoEvento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre_evento")
    private String nombreEvento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "inst_organizadora")
    private String instOrganizadora;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "evento")
    private String evento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_evento")
    @Temporal(TemporalType.DATE)
    private Date fechaEvento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hombres")
    private int hombres;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mujeres")
    private int mujeres;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total")
    private int total;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "resultado")
    private String resultado;

    @JoinColumn(name = "Actividad_idActividad", referencedColumnName = "idActividad", insertable = true, updatable = true)
    @ManyToOne(optional = false)
    private Actividad actividad;
    public Evento() {
    }

    

    

    

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getInstOrganizadora() {
        return instOrganizadora;
    }

    public void setInstOrganizadora(String instOrganizadora) {
        this.instOrganizadora = instOrganizadora;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public int getHombres() {
        return hombres;
    }

    public void setHombres(int hombres) {
        this.hombres = hombres;
    }

    public int getMujeres() {
        return mujeres;
    }

    public void setMujeres(int mujeres) {
        this.mujeres = mujeres;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    /**
     * @return the actividad
     */
    public Actividad getActividad() {
        return actividad;
    }

    /**
     * @param actividad the actividad to set
     */
    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

   

   

}
