/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import entities.Grupousuarios;

import entities.Promotor;
import entities.Usuarios;
import facade.GrupousuariosFacade;

import facade.PromotorFacade;
import facade.UsuariosFacade;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.mail.MessagingException;

/**
 *
 * @author pablog23
 */
@ManagedBean
@SessionScoped
public class BeanPromotor implements Serializable {

    private Promotor promotorEntidad = new Promotor();
    private Usuarios usuariosentidad = new Usuarios();
    private Grupousuarios grupousuariosentidad = new Grupousuarios();
    private int idPromotor;
    private String receptor;
    private final String asunto = "Usuario y Contraseña para el SGAE";
    private String mensaje;
    private String statusMessage = "";

    @Inject
    private PromotorFacade promotorFacade;

    @Inject
    private UsuariosFacade usuariosFacade;

    @Inject
    private GrupousuariosFacade grupousuariosFacade;

    public BeanPromotor() {

    }

    public List<Promotor> jalarPromotores() {
        List<Promotor> obj = promotorFacade.findAll();
        return obj;

    }

    public void agregarPromotor() {
        //Crear Promotor
        promotorEntidad.setStatus(true);
        promotorFacade.create(promotorEntidad);
        destroyWorld();
        //CrearUsuario
        String pass = "Prom" + generarpass.getPassword(generarpass.MINUSCULAS + generarpass.MAYUSCULAS + generarpass.NUMEROS, 6);
        promotorEntidad = promotorFacade.find(promotorEntidad.getIdPromotor());
        usuariosentidad.setPromotoridPromotor(promotorEntidad);
        usuariosentidad.setUsuario(generarPromotor());
        usuariosentidad.setContraseña(pass);
        usuariosentidad.setStatus("Activo");
        usuariosentidad.setTiposUsuario("Promotor");
        usuariosFacade.create(usuariosentidad);
        //Crear ususario en Grupo
        usuariosentidad = usuariosFacade.find(usuariosentidad.getIdUsua());
        grupousuariosentidad.setUsuariosidUsua(usuariosentidad);
        grupousuariosentidad.setUsuario(usuariosentidad.getUsuario());
        grupousuariosentidad.setNombregrupo(usuariosentidad.getTiposUsuario());
        grupousuariosFacade.create(grupousuariosentidad);
        //enviar correo con usuario y pass
        setStatusMessage("Message Sent");
        receptor=promotorEntidad.getCorreo();
        mensaje="Usuario: " + usuariosentidad.getUsuario() + "\nContraseña: " + usuariosentidad.getContraseña();
        try {
            MailService.sendMessage(receptor, asunto, mensaje);
        }
        catch(MessagingException ex) {
            setStatusMessage(ex.getMessage());
        }

    }

    public void send() {
        setStatusMessage("Message Sent");
        try {
            MailService.sendMessage(getReceptor(), getAsunto(), getMensaje());
        } catch (MessagingException ex) {
            setStatusMessage(ex.getMessage());
        }
        // return "index1";  // redisplay page with status message
    }

    public String generarPromotor() {
        String nompromotor = "Promotor00";
        int numero;
        List<Usuarios> listausuarios = usuariosFacade.findAll();
        numero = listausuarios.size() - 1;
        String prom = String.valueOf(numero);
        String nombrefinal = nompromotor + prom;
        //System.out.println("nombreeeee:"+ nombrefinal);

        return nombrefinal;
    }

    public Promotor getPromotor() {
        return promotorEntidad;
    }

    public void setPromotor(Promotor promotor) {
        this.promotorEntidad = promotor;
    }

    public void destroyWorld() {
        addMessage("Promotor Agregado", "Agregada");
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String logo = externalContext.getRealPath("") + File.separator + "resources" + File.separator + "image" + File.separator + "ddd.jpg";

        pdf.add(Image.getInstance(logo));
    }

    /**
     * @return the usuariosentidad
     */
    public Usuarios getUsuariosentidad() {
        return usuariosentidad;
    }

    /**
     * @param usuariosentidad the usuariosentidad to set
     */
    public void setUsuariosentidad(Usuarios usuariosentidad) {
        this.usuariosentidad = usuariosentidad;
    }

    /**
     * @return the grupousuariosentidad
     */
    public Grupousuarios getGrupousuariosentidad() {
        return grupousuariosentidad;
    }

    /**
     * @param grupousuariosentidad the grupousuariosentidad to set
     */
    public void setGrupousuariosentidad(Grupousuarios grupousuariosentidad) {
        this.grupousuariosentidad = grupousuariosentidad;
    }

    /**
     * @return the receptor
     */
    public String getReceptor() {
        return receptor;
    }

    /**
     * @param receptor the receptor to set
     */
    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    /**
     * @return the asunto
     */
    public String getAsunto() {
        return asunto;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return the statusMessage
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * @param statusMessage the statusMessage to set
     */
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

}
