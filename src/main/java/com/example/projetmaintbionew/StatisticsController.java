package com.example.projetmaintbionew;

import databaseHelper.DBHandler;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StatisticsController implements Initializable {

    private static int userId;

    @FXML
    private HBox change;

    @FXML
    private PieChart pieChart;

    @FXML
    private StackedBarChart<?, ?> stackBarChar;

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

        /*int cptTotal = cptF+cptH;

        lbTotal.setText("Effectif Total : " + cptTotal + " etudiants");*/
    }
}
