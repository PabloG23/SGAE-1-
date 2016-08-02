/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import entities.Alumno;
import entities.Grupo;
import entities.Usuarios;
import facade.AlumnoFacade;
import facade.GrupoFacade;
import facade.UsuariosFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
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
    private String usuarionombre = "";
    private String usuario;
    private String contraseña;
    private Grupo entidadGrupo = new Grupo();
    private Alumno entidadalumno = new Alumno();
    @Inject
    private UsuariosFacade usuariosfacade;
    @Inject
    GrupoFacade grupoFacade;
    @Inject
    AlumnoFacade alumnoFacade;

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
                usuarionombre = usuarios.getUsuario();
                buscarusutabla();
            }

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario y/o Contraseña invalida"));
        }
        return outcome;
    }

    public int buscarusutabla() {
        int numeroretornado = 0;
        List<Usuarios> usuobj = usuariosfacade.findAll();
        for (int i = 0; i < usuobj.size(); i++) {
            if (usuobj.get(i).getUsuario().equals(usuarionombre)) {
                //System.out.println("Si esta: " + usuobj.get(i).getPromotoridPromotor().getIdPromotor());
                numeroretornado = usuobj.get(i).getPromotoridPromotor().getIdPromotor();
                break;

            } else {
               // System.out.println("no esta");
            }

        }
        //System.out.println("Numero retornadofifado: " + numeroretornado);
        return numeroretornado;

    }
    /////CODIGO PARA SACAR LOS GRUPOS DE UN PROMOTOR/////
    int pruebaidpromotor;
    int idgrupo;
    int idpromotorcomparar;

    public List<Grupo> jalargrupodepromotorlogeado() {
        pruebaidpromotor = buscarusutabla();
        int numeropromotor;
        String idcadena = "";
        List<Grupo> listagruposprom = new ArrayList<Grupo>();
        List<Grupo> gruobj = grupoFacade.findAll();
        for (int i = 0; i < gruobj.size(); i++) {
            idgrupo = gruobj.get(i).getIdGrupo();
            //idcadena = Integer.toString(idgrupo);
            idpromotorcomparar = gruobj.get(i).getPromotor().getIdPromotor();                    
            //numeropromotor = Integer.parseInt("" + idcadena.charAt(1));
            if (idpromotorcomparar == pruebaidpromotor) {
                setEntidadGrupo(grupoFacade.find(this.idgrupo));
                listagruposprom.add(getEntidadGrupo());
                //       System.out.println("grupos del promotor1: " + idgrupo);
                //     System.out.println("Lista chida: " + listagruposprom);
            }
//            else {
//                System.out.println("estos no son grupos de este promotor: " + idgrupo);
//            }

        }
        //System.out.println("Lista chidafinal: " + listagruposprom);
        return listagruposprom;

    }
    /////FIN DE CODIGO PARA SACAR LOS GRUPOS DE UN PROMOTOR/////

    ////CODIGO PARA JALAR LOS ALUMNOS DE CADA GRUPO/////
    private int idgruposeleccionado=2220162;
    private int idcomparar;

    public List<Alumno> alumno_grupo() {

        entidadGrupo = grupoFacade.find(this.idgruposeleccionado);

        
        idcomparar = entidadGrupo.getIdGrupo();
        //System.out.println("!!!Entidad grupo: " + entidadGrupo);
        //System.out.println("iddegrupo: " + idcomparar);
        List<Alumno> alumnos = alumnoFacade.findAll();
        List<Alumno> listaalumno = new ArrayList<Alumno>();
        for (int i = 0; i < alumnos.size(); i++) {
            if (idcomparar == alumnos.get(i).getGrupo().getIdGrupo()) {
                entidadalumno = alumnoFacade.find(alumnos.get(i).getNoCtrl());
                listaalumno.add(entidadalumno);
          //      System.out.println("Alumno: " + entidadalumno.getNombre());
            }
        }
        //System.out.println("Lista del grupo: " + listaalumno);
        return listaalumno;

    }
    ////FIN DE CODIGO PARA JALAR LOS ALUMNOS DE CADA GRUPO/////
    
    public List<Alumno> alumnos_reconocidos(){
        List<Alumno> aReco = new ArrayList<Alumno>();
        List<Alumno> aRecoRet = new ArrayList<Alumno>();
        
        aReco=alumno_grupo();
        
        for (int i = 0; i < aReco.size(); i++) {
            System.out.println("{çç}"+aReco.get(i));
            if (aReco.get(i).getReconocimiento()==true) {
                aRecoRet.add(aReco.get(i));
            }
        }
        return aRecoRet;
    }

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

    /**
     * @return the entidadGrupo
     */
    public Grupo getEntidadGrupo() {
        return entidadGrupo;
    }

    /**
     * @param entidadGrupo the entidadGrupo to set
     */
    public void setEntidadGrupo(Grupo entidadGrupo) {
        this.entidadGrupo = entidadGrupo;
    }

    /**
     * @return the idgruposeleccionado
     */
    public int getIdgruposeleccionado() {
        return idgruposeleccionado;
    }

    /**
     * @param idgruposeleccionado the idgruposeleccionado to set
     */
    public void setIdgruposeleccionado(int idgruposeleccionado) {
        this.idgruposeleccionado = idgruposeleccionado;
    }

    /**
     * @return the entidadalumno
     */
    public Alumno getEntidadalumno() {
        return entidadalumno;
    }

    /**
     * @param entidadalumno the entidadalumno to set
     */
    public void setEntidadalumno(Alumno entidadalumno) {
        this.entidadalumno = entidadalumno;
    }
}
