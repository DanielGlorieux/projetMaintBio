package com.example.projetmaintbionew;

import com.jfoenix.controls.JFXListCell;
import databaseHelper.DBHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;

public class CellEquipementUserController extends JFXListCell<Equipement> implements Initializable {

    @FXML
    private Label EquipmentLabel;

    @FXML
    private ImageView UpdtBtn;

    @FXML
    private Label dateAjLabel;

    @FXML
    private ImageView deleteBtn;

    @FXML
    private Label designationLabel;

    @FXML
    private Label etatLabel;

    @FXML
    private ImageView iconeEquipment;

    @FXML
    private HBox rootPane;

    @FXML
    private Label serviceLabel;

    @FXML
    private ImageView signPanneBtn;

    private FXMLLoader fxmlLoader;

    private DBHandler databaseHandler;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        databaseHandler = new DBHandler();

    }

    @Override
    protected void updateItem(Equipement myEquipmt, boolean empty) {
        super.updateItem(myEquipmt, empty);

        if (empty || myEquipmt == null){
            setText(null);
            setGraphic(null);
        }else {
            if (fxmlLoader == null ) {
                fxmlLoader = new FXMLLoader(getClass()
                        .getResource("cellEquipementUserView.fxml"));
                fxmlLoader.setController(this);

                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            EquipmentLabel.setText(myEquipmt.getNumserie());
            designationLabel.setText(myEquipmt.getDesignation());
            etatLabel.setText(myEquipmt.getEtat());
            serviceLabel.setText(myEquipmt.getService());
            dateAjLabel.setText(myEquipmt.getAnneAcquis().toString());


            setText(null);
            setGraphic(rootPane);
        }
    }
}
