/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
 
@ManagedBean
public class MultiSelectView {
     
    private List<SelectItem> categories;    
    private String selection;
 
    @PostConstruct
    public void init() {
        categories = new ArrayList<SelectItem>();
        
        SelectItemGroup Deportivo = new SelectItemGroup("Deportivo");
        SelectItemGroup Cultural = new SelectItemGroup("Cultural");
               
                 
        SelectItem Ajedrez = new SelectItem("Ajedrez");
        SelectItem Basquetbol = new SelectItem("Basquetbol");
        SelectItem Futbol = new SelectItem("Futbol");
        SelectItem Natacion = new SelectItem("Natación");
         
        SelectItem Escolta = new SelectItem("Escolta");
                                            
        Deportivo.setSelectItems(new SelectItem[]{Ajedrez, Basquetbol, Futbol, Natacion});
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
}