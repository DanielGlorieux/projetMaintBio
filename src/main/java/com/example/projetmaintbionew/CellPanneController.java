package com.example.projetmaintbionew;

import com.jfoenix.controls.JFXListCell;
import databaseHelper.DBHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

public class CellPanneController extends JFXListCell<Intervention> implements Initializable {


    @FXML
    private ImageView UpdtBtn;

    @FXML
    private Label datePanneLabel;

    @FXML
    private ImageView deleteBtn;

    @FXML
    private Label descPanneLabel;

    @FXML
    private ImageView iconeEquipment;

    @FXML
    private Label lieuEquipementLabel;

    @FXML
    private Label marqueEquipementLabel;

    @FXML
    private Label modelLabel;

    @FXML
    private Label modeleEquipementLabel;

    @FXML
    private HBox rootPane;

    private DBHandler databaseHandler;

    private FXMLLoader fxmlLoader;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        databaseHandler = new DBHandler();

    }

    @Override
    protected void updateItem(Intervention myInterventon, boolean empty) {
        super.updateItem(myInterventon, empty);

        if (empty || myInterventon == null){
            setText(null);
            setGraphic(null);
        }else {
            if (fxmlLoader == null ) {
                fxmlLoader = new FXMLLoader(getClass()
                        .getResource("cellPanneView.fxml"));
                fxmlLoader.setController(this);

                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            marqueEquipementLabel.setText(myInterventon.getMarque());
            modeleEquipementLabel.setText(myInterventon.getDesignation());
            lieuEquipementLabel.setText(myInterventon.getLieuAff());
            descPanneLabel.setText(myInterventon.getDescription());

            //int equipmtId = myEquipmt.getEquipmentId();

            UpdtBtn.setOnMouseClicked(event -> {

            });

            /*deleteBtn.setOnMouseClicked(event -> {
                try {
                    databaseHandler.deleteEquipment(equipmtId);
                    //System.out.println("ID Equipment "+ equipmtId);

                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                getListView().getItems().remove(getItem());

            });*/

            setText(null);
            setGraphic(rootPane);
        }
    }
}
