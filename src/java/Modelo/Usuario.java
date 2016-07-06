package Modelo;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "usuario")
@SessionScoped
public class Usuario implements Serializable {

    private String nombre;

    public String getNombre() {
        if (nombre == null) {
            getDatosUsuario();
        }
        return nombre == null ? "" : nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Obtiene el nombre del usuario de la sesión
     */
    private void getDatosUsuario() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        Object objPeticion = context.getRequest();
        if (!(objPeticion instanceof HttpServletRequest)) {
            System.out.println("Error objeto es: "
                    + objPeticion.getClass().toString());
            return;
        }
        HttpServletRequest peticion = (HttpServletRequest) objPeticion;
        nombre = peticion.getRemoteUser();
        if (nombre == null) {
            logout();
        }
    }
    /**
     * Invalida la Sesión y redigiré a la página de inicio
     */
    public void logout() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.invalidateSession();
        try {
            ec.redirect(ec.getRequestContextPath());
        } catch (IOException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
