package com.example.projetmaintbionew;

import databaseHelper.DBHandler;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;

public class StatisticsController implements Initializable {

    private static int userId;

    @FXML
    private HBox change;

    @FXML
    private PieChart pieChart;

    @FXML
    private StackedBarChart<String, Number> stackBarChar;

    @FXML
    private CategoryAxis xAxe;

    @FXML
    private NumberAxis yAxe;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //compteur homme et femme
        int cptEquipment = 0;
        int cptPanne = 0;

        DBHandler dbHandler = new DBHandler();

        try {
            ResultSet rs = dbHandler.getEquipment();
            ResultSet rs2 = dbHandler.getPanne();
            while (rs.next()){
                cptEquipment++;
            }
            while (rs2.next()){
                cptPanne++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("NbMachines",cptEquipment),
                new PieChart.Data("NbPannes", cptPanne)
        );

        pieChartData.forEach(data ->
                data.nameProperty().bind(Bindings.concat(
                        data.getName()," total : ", data.pieValueProperty()
                )));

        pieChart.getData().addAll(pieChartData);

        try {
            Map<String, Integer> panneByService = dbHandler.getPanneCountByService();
            Map<String, Integer> interventionByService = dbHandler.getInterventionCountByService();
            populateStackedBarChart(panneByService, interventionByService);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*private void populateStackedBarChart(Map<String, Integer> panneByService) {
        // Create a series for the chart
        XYChart.Series<String, Number> panneSeries = new XYChart.Series<>();
        panneSeries.setName("Nombre de Pannes");

        // Populate the series with data
        panneByService.forEach((service, count) -> {
            XYChart.Data<String, Number> dataPoint = new XYChart.Data<>(service, count);
            panneSeries.getData().add(dataPoint);
        });

        // Add the series to the chart
        stackBarChar.getData().clear(); // Clear existing data
        stackBarChar.getData().add(panneSeries);
    }*/

    private void populateStackedBarChart(Map<String, Integer> panneByService, Map<String, Integer> interventionByService) {
        // Create series for pannes
        XYChart.Series<String, Number> panneSeries = new XYChart.Series<>();
        panneSeries.setName("Nombre de pannes");

        // Create series for interventions
        XYChart.Series<String, Number> interventionSeries = new XYChart.Series<>();
        interventionSeries.setName("Nombre d'interventions");

        // Populate the series with data
        panneByService.forEach((service, count) -> {
            XYChart.Data<String, Number> dataPoint = new XYChart.Data<>(service, count);
            panneSeries.getData().add(dataPoint);
        });

        interventionByService.forEach((service, count) -> {
            XYChart.Data<String, Number> dataPoint = new XYChart.Data<>(service, count);
            interventionSeries.getData().add(dataPoint);
        });

        // Add the series to the chart
        stackBarChar.getData().clear(); // Clear existing data
        stackBarChar.getData().addAll(panneSeries, interventionSeries);
    }
}
