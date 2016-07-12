/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import entities.Usuarios;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author omar
 */
@ManagedBean
@SessionScoped
public class BeanUsuario implements Serializable {

    private Usuarios usuarios = new Usuarios();

    private String usuario;
    private String contraseña;

    public String login() {
        String outcome = "Control_Acce";
        System.out.println("usuario:" + usuarios.getUsuario() + usuarios.getContraseña());

        try {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            request.login(usuarios.getUsuario(), usuarios.getContraseña());
            if (this.usuarios.getUsuario().equals("JefeD")) {
                outcome = "/JefeDepto/index_JD?faces-redirect=true";
            }
            if (this.usuarios.getUsuario().equals("JefeO")) {
                outcome = "/JefeOficina/index_JO?faces-redirect=true";
            }
            if (!this.usuarios.getUsuario().equals("JefeD") && !this.usuarios.getUsuario().equals("JefeO")) {
                outcome = "/Promotor/indexPromotor?faces-redirect=true";
            }

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario y/o Contraseña invalida"));
        }
        return outcome;
    }

//    public void logout() {
//        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//        ec.invalidateSession();
//        try {
//            ec.redirect(ec.getRequestContextPath());
//        } catch (IOException ex) {
//            Logger.getLogger(BeanUsuario.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public void logout() throws IOException {
        // FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        this.usuarios = null;
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.invalidate();
        FacesContext.getCurrentInstance().getExternalContext().redirect("https://localhost:8080/SGAE/");
    }

    public void cerrarsecion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

    }

    public void logoutin() {
        ExternalContext ctx
                = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath
                = ((ServletContext) ctx.getContext()).getContextPath();

        try {
            // Usar el contexto de JSF para invalidar la sesión,
            // NO EL DE SERVLETS (nada de HttpServletRequest)
            ((HttpSession) ctx.getSession(false)).invalidate();

            // Redirección de nuevo con el contexto de JSF,
            // si se usa una HttpServletResponse fallará.
            // Sin embargo, como ya está fuera del ciclo de vida 
            // de JSF se debe usar la ruta completa -_-U
            ctx.redirect(ctxPath + "/faces/Control_Acce.xhtml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @return the usuarios
     */
    public Usuarios getUsuarios() {
        return usuarios;
    }

    /**
     * @param usuarios the usuarios to set
     */
    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the contraseña
     */
    public String getContraseña() {
        return contraseña;
    }

    /**
     * @param contraseña the contraseña to set
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    private boolean error = false;

    public String cerrarSesionS() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.
                getExternalContext().getSession(false);
        session.invalidate();
        return "success";
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
