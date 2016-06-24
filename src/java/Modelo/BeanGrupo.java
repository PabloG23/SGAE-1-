/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import entities.Actividad;
import entities.CatActividad;
import entities.Grupo;
import entities.Promotor;
import facade.ActividadFacade;
import facade.GrupoFacade;
import facade.PromotorFacade;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author pablog23
 */
@ManagedBean
@RequestScoped
public class BeanGrupo {

    private Grupo entidadGrupo = new Grupo();

    @Inject
    private GrupoFacade grupoFacade;

    @Inject
    private PromotorFacade promotorFacade;

    @Inject
    private ActividadFacade actividadFacade;

    private int idpromotor;
    private int idactividad;
    private List<String> lista = new ArrayList<>();

    public BeanGrupo() {
    }

    public void agregarGrupo() {
        Actividad obj = actividadFacade.find(this.idactividad);
        entidadGrupo.setActividad(obj);

        Promotor obj1 = promotorFacade.find(this.idpromotor);
        entidadGrupo.setPromotor(obj1);

        entidadGrupo.setAño(obj.getAño());
        entidadGrupo.setDias(concatenar());
        entidadGrupo.setIdGrupo(generarIdGrupo(idactividad, idpromotor, obj.getAño()));
        grupoFacade.create(entidadGrupo);
    }

    public Grupo getEntidadGrupo() {
        return entidadGrupo;
    }

    public void setEntidadGrupo(Grupo entidadGrupo) {
        this.entidadGrupo = entidadGrupo;
    }

    public int generarIdGrupo(Integer idactividad, Integer idpromotor, Integer año) {
        List<Grupo> obj = grupoFacade.findAll();
        int contador = obj.size() + 1;
        String idgrupo = "";
        String g_idact = String.valueOf(idactividad);
        String g_idprom = String.valueOf(idpromotor);
        String g_año = String.valueOf(año);
        String g_contador = String.valueOf(contador);
        idgrupo += g_idact + g_idprom + g_año + g_contador;

        int id_grupo = Integer.parseInt(idgrupo);
        System.out.println("idgrupoGenerado:" + id_grupo);
        return id_grupo;
    }

    public List<String> completarAño(String año) {
        List<String> obj = new ArrayList<>();
        for (int i = 2016; i < 2030; i++) {
            obj.add(año + i);
        }
        return obj;
    }

    public String concatenar() {
        String x = "";
        for (String dia : lista) {

            x += dia + " ";
        }
        return x;
    }

    public int getIdpromotor() {
        return idpromotor;
    }

    public void setIdpromotor(int idpromotor) {
        this.idpromotor = idpromotor;
    }

    public List<Promotor> jalarNombresPromotores() {
        List<Promotor> obj = promotorFacade.findAll();
        return obj;
    }

    public List<Actividad> jalarActividades() {
        List<Actividad> obj = actividadFacade.findAll();
        return obj;
    }

    public int getIdactividad() {
        return idactividad;
    }

    public void setIdactividad(int idactividad) {
        this.idactividad = idactividad;
    }

    public List<String> getLista() {
        return lista;
    }

    public void setLista(List<String> lista) {
        this.lista = lista;
    }

}
