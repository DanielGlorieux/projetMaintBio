package com.example.projetmaintbionew;

import com.jfoenix.controls.JFXListCell;
import databaseHelper.DBHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class cellEquipementController extends JFXListCell<Equipement> implements Initializable {


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
                        .getResource("cellEquipementView.fxml"));
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

            int equipmtId = myEquipmt.getEquipmentId();

            deleteBtn.setOnMouseClicked(event -> {

                try {

                    databaseHandler.deleteEquipment(equipmtId);
                    System.out.println("ID Equipment "+ equipmtId);

                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                getListView().getItems().remove(getItem());

            });



            /*UpdtBtn.setOnMouseClicked(event -> {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/view/updateEquipmtView.fxml"));


                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));

                UpdateTaskController updateTaskController = loader.getController();
                updateTaskController.setTaskField(myTask.getTask());
                updateTaskController.setUpdateDescriptionField(myTask.getDescription());

                updateTaskController.updateTaskButton.setOnAction(event1 -> {

                    Calendar calendar = Calendar.getInstance();

                    java.sql.Timestamp timestamp =
                            new java.sql.Timestamp(calendar.getTimeInMillis());

                    try {

                        System.out.println("taskid " + myTask.getTaskId());

                        databaseHandler.updateTask(timestamp, updateTaskController.getDescription(),
                                updateTaskController.getTask(), myTask.getTaskId());

                        //update our listController
                        // updateTaskController.refreshList();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                });

                stage.show();


            });*/


            setText(null);
            setGraphic(rootPane);
        }
    }
}
