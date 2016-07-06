/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author omar
 */
@ManagedBean
public class ChartView implements Serializable {

    private BarChartModel barModel, barModel2;

    @PostConstruct
    public void init() {
        createBarModels();
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public BarChartModel getBarModel2() {
        return barModel2;
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();

        ChartSeries boys = new ChartSeries();
        boys.setLabel("Hombres");
        boys.set("Ajedrez", 52);
        boys.set("Futbol", 60);
        boys.set("Basquetbol", 110);
        boys.set("Voleivol", 135);
        boys.set("Natación", 120);

        ChartSeries girls = new ChartSeries();
        girls.setLabel("Mujeres");
        girls.set("Ajedrez", 52);
        girls.set("Futbol", 60);
        girls.set("Basquetbol", 110);
        girls.set("Voleivol", 135);
        girls.set("Natación", 120);

        model.addSeries(boys);
        model.addSeries(girls);

        return model;
    }

    private BarChartModel initBarModel2() {
        BarChartModel model = new BarChartModel();

        ChartSeries boys = new ChartSeries();
        boys.setLabel("Hombres");
        boys.set("ISC", 52);
        boys.set("IM", 60);
        boys.set("IQ", 110);
        boys.set("II", 135);
        boys.set("IEM", 120);

        ChartSeries girls = new ChartSeries();
        girls.set("ISC", 52);
        girls.set("IM", 60);
        girls.set("IQ", 110);
        girls.set("II", 135);
        girls.set("IEM", 120);

        model.addSeries(boys);
        model.addSeries(girls);

        return model;
    }

    private void createBarModels() {
        createBarModel();
        createBarModel2();

    }

    private void createBarModel() {
        barModel = initBarModel();

        barModel.setTitle("Hombres y Mujeres por Actividad");
        barModel.setLegendPosition("ne");

        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Actividades");

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Tolal");
        yAxis.setMin(0);
        yAxis.setMax(200);

    }

    private void createBarModel2() {
        barModel2 = initBarModel2();

        barModel2.setTitle("Hombres y Mujeres por Carrera");
        barModel2.setLegendPosition("ne");

        Axis xAxis = barModel2.getAxis(AxisType.X);
        xAxis.setLabel("Carreras");

        Axis yAxis = barModel2.getAxis(AxisType.Y);
        yAxis.setLabel("Tolal");
        yAxis.setMin(0);
        yAxis.setMax(200);

    }

}
