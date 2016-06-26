/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entities.CatActividad;
import facade.CatActFacade;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.inject.Inject;

@ManagedBean
public class MultiSelectView {

    private List<SelectItem> categories;
    private List<SelectItem> lista;
    private String selection;
    private CatActividad catac = new CatActividad();
    @Inject
    private CatActFacade cat;

    @PostConstruct
    public void init() {
        categories = new ArrayList<SelectItem>();
        lista = new ArrayList<SelectItem>();
        SelectItemGroup Deportivo = new SelectItemGroup("Deportivo");
        SelectItemGroup Cultural = new SelectItemGroup("Cultural");

        List<CatActividad> obj = cat.findAll();
        Iterator<CatActividad> it = obj.iterator();

        for (int i = 0; i < obj.size(); i++) {
            SelectItem tmp = new SelectItem(it.next().getNombre());
            System.out.println("TMP1: " + tmp.getLabel());
            Deportivo.setSelectItems(new SelectItem[]{tmp});

        }
        SelectItem Escolta = new SelectItem("Escolta");
        Cultural.setSelectItems(new SelectItem[]{Escolta});

        categories.add(Deportivo);
        categories.add(Cultural);
    }

    public List<SelectItem> getCategories() {
        return categories;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }
    public void destroyWorld() {
        addMessage("Actividad", "Agregada");
    }
     
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
