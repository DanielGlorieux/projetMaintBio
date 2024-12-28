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

public class CellePanneTIController extends JFXListCell<PanneEquipmentData> implements Initializable {

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

    @FXML
    private Label prevLabel;

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
                        .getResource("cellPanneTIView.fxml"));
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
            prevLabel.setText(myInterventon.getPrevDate());

            int intId = myInterventon.getInterventionId();


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
                        int userId = AppSession.getInstance().getUserId();

                        // Validate the intervention and update the panne table with the comment
                        databaseHandler.validateInterventionAndUpdatePanneWithComment(myInterventon.getInterventionId(), comment, userId);

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
