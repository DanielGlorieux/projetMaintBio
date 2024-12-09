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

public class CellPanneController extends JFXListCell<PanneEquipmentData> implements Initializable {


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
    protected void updateItem(PanneEquipmentData myInterventon, boolean empty) {
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

            int intId = myInterventon.getInterventionId();

            if (myInterventon.isTransmitted()) {
                Image transmittedImage = new Image(getClass().getResourceAsStream("/traiteVal.png"));
                UpdtBtn.setImage(transmittedImage);
            } else {
                Image defaultImage = new Image(getClass().getResourceAsStream("/traiter.png"));
                UpdtBtn.setImage(defaultImage);
            }

            //System.out.println("Updating intervention with ID: " + intId);

            UpdtBtn.setOnMouseClicked(event -> {
                //System.out.println("Update Button Clicked!");
                try {

                    //System.out.println("EquipId " + myEquipmt.getEquipmentId());

                    databaseHandler.transIntervention(
                            myInterventon.getInterventionId());
                    //System.out.println("Update successful!");

                    Image updatedImage = new Image(getClass().getResourceAsStream("/traiteVal.png"));
                    UpdtBtn.setImage(updatedImage);

                    //update our listController
                    // updateTaskController.refreshList();
                } catch (SQLException e) {
                    System.out.println("SQL Exception: " + e.getMessage());
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    System.out.println("ClassNotFoundException: " + e.getMessage());
                    e.printStackTrace();
                }

            });

            /*deleteBtn.setOnMouseClicked(event -> {

                *//*FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("validateInterventionView.fxml"));

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));

                ValidateInterventionController  validateInterventionController = loader.getController();
                validateInterventionController.setRappTF(PanneEquipmentData.getRappInterv());*//*


                try {
                    databaseHandler.validateInterventionAndUpdatePanne(myInterventon.getInterventionId());
                    //System.out.println("ID Equipment "+ equipmtId);

                    Image validatedImage = new Image(getClass().getResourceAsStream("/validerSet.png"));
                    deleteBtn.setImage(validatedImage);

                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                getListView().getItems().remove(getItem());

            });*/

            deleteBtn.setOnMouseClicked(event -> {
                try {
                    // Load the ValidateInterventionView
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("validateInterventionView.fxml"));
                    Parent root = loader.load();

                    // Get the controller for ValidateInterventionView
                    ValidateInterventionController validateInterventionController = loader.getController();

                    // Pass intervention ID to the controller
                    validateInterventionController.setInterventionId(myInterventon.getInterventionId());

                    // Show the view
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.showAndWait(); // Wait for the user to complete action in the view

                    // After the window is closed, process validation and update
                    if (validateInterventionController.isValidated()) {
                        String comment = validateInterventionController.getRappTF();

                        // Validate the intervention and update the panne table with the comment
                        databaseHandler.validateInterventionAndUpdatePanneWithComment(myInterventon.getInterventionId(), comment);

                        // Update the button image to indicate validation
                        Image validatedImage = new Image(getClass().getResourceAsStream("/validerSet.png"));
                        deleteBtn.setImage(validatedImage);

                        // Remove the item from the ListView
                        getListView().getItems().remove(getItem());
                    }

                } catch (IOException | SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            });


            setText(null);
            setGraphic(rootPane);
        }
    }
}
