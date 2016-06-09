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
 * @author pablog23
 */
@Entity
@Table(name = "Evento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evento.findAll", query = "SELECT e FROM Evento e"),
    @NamedQuery(name = "Evento.findByIdevento", query = "SELECT e FROM Evento e WHERE e.eventoPK.idevento = :idevento"),
    @NamedQuery(name = "Evento.findByTipoEvento", query = "SELECT e FROM Evento e WHERE e.tipoEvento = :tipoEvento"),
    @NamedQuery(name = "Evento.findByNombreEvento", query = "SELECT e FROM Evento e WHERE e.nombreEvento = :nombreEvento"),
    @NamedQuery(name = "Evento.findByInstOrganizadora", query = "SELECT e FROM Evento e WHERE e.instOrganizadora = :instOrganizadora"),
    @NamedQuery(name = "Evento.findByEvento", query = "SELECT e FROM Evento e WHERE e.evento = :evento"),
    @NamedQuery(name = "Evento.findByFechaEvento", query = "SELECT e FROM Evento e WHERE e.fechaEvento = :fechaEvento"),
    @NamedQuery(name = "Evento.findByHombres", query = "SELECT e FROM Evento e WHERE e.hombres = :hombres"),
    @NamedQuery(name = "Evento.findByMujeres", query = "SELECT e FROM Evento e WHERE e.mujeres = :mujeres"),
    @NamedQuery(name = "Evento.findByResultado", query = "SELECT e FROM Evento e WHERE e.resultado = :resultado"),
    @NamedQuery(name = "Evento.findByPromotoridPromotor", query = "SELECT e FROM Evento e WHERE e.eventoPK.promotoridPromotor = :promotoridPromotor"),
    @NamedQuery(name = "Evento.findByActividadidActividad", query = "SELECT e FROM Evento e WHERE e.eventoPK.actividadidActividad = :actividadidActividad")})
public class Evento implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EventoPK eventoPK;
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
    @Size(min = 1, max = 45)
    @Column(name = "resultado")
    private String resultado;
    @JoinColumn(name = "Promotor_idPromotor", referencedColumnName = "idPromotor", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Promotor promotor;
    @JoinColumn(name = "Actividad_idActividad", referencedColumnName = "idActividad", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Actividad actividad;

    public Evento() {
    }

    public Evento(EventoPK eventoPK) {
        this.eventoPK = eventoPK;
    }

    public Evento(EventoPK eventoPK, String tipoEvento, String nombreEvento, String instOrganizadora, String evento, Date fechaEvento, int hombres, int mujeres, String resultado) {
        this.eventoPK = eventoPK;
        this.tipoEvento = tipoEvento;
        this.nombreEvento = nombreEvento;
        this.instOrganizadora = instOrganizadora;
        this.evento = evento;
        this.fechaEvento = fechaEvento;
        this.hombres = hombres;
        this.mujeres = mujeres;
        this.resultado = resultado;
    }

    public Evento(int idevento, int promotoridPromotor, int actividadidActividad) {
        this.eventoPK = new EventoPK(idevento, promotoridPromotor, actividadidActividad);
    }

    public EventoPK getEventoPK() {
        return eventoPK;
    }

    public void setEventoPK(EventoPK eventoPK) {
        this.eventoPK = eventoPK;
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

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Promotor getPromotor() {
        return promotor;
    }

    public void setPromotor(Promotor promotor) {
        this.promotor = promotor;
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
        hash += (eventoPK != null ? eventoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evento)) {
            return false;
        }
        Evento other = (Evento) object;
        if ((this.eventoPK == null && other.eventoPK != null) || (this.eventoPK != null && !this.eventoPK.equals(other.eventoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Evento[ eventoPK=" + eventoPK + " ]";
    }
    
}
