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

public class cellEquipementController extends JFXListCell<Equipement> implements Initializable {

    private int userId;
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

            UpdtBtn.setOnMouseClicked(event -> {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("updateEquipmentFormView.fxml"));


                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));

                UpdateEquipmentFormController updateEquipmentController = loader.getController();
                updateEquipmentController.setModDesignationTf(myEquipmt.getDesignation());
                updateEquipmentController.setModModeleTf(myEquipmt.getModel());
                updateEquipmentController.setModMarqueTf(myEquipmt.getMarque());
                updateEquipmentController.setModNumbseriTf(myEquipmt.getNumserie());


                updateEquipmentController.getModBtnAddValidate().setOnAction(event1 -> {
                    try {
                        // Comparer les champs actuels avec les champs saisis
                        /*String newDesignation = !updateEquipmentController.getModDesignationTf().equals(myEquipmt.getDesignation())
                                ? updateEquipmentController.getModDesignationTf()
                                : null;*/

                        String newDesignation = updateEquipmentController.getModDesignationTf() != null &&
                                !updateEquipmentController.getModDesignationTf().equals(myEquipmt.getDesignation())
                                ? updateEquipmentController.getModDesignationTf()
                                : null;

                        String newModel = updateEquipmentController.getModModeleTf() != null &&
                                !updateEquipmentController.getModModeleTf().equals(myEquipmt.getModel())
                                ? updateEquipmentController.getModModeleTf()
                                : null;

                        String newMarque = updateEquipmentController.getModMarqueTf() != null &&
                                !updateEquipmentController.getModMarqueTf().equals(myEquipmt.getMarque())
                                ? updateEquipmentController.getModMarqueTf()
                                : null;

                        /*String newMarque = !updateEquipmentController.getModMarqueTf().equals(myEquipmt.getMarque())
                                ? updateEquipmentController.getModMarqueTf()
                                : null;*/

                        /*String newNumSer = !updateEquipmentController.getModNumbseriTf().equals(myEquipmt.getNumserie())
                                ? updateEquipmentController.getModNumbseriTf()
                                : null;*/

                        String newNumSer = updateEquipmentController.getModNumbseriTf() != null &&
                                !updateEquipmentController.getModNumbseriTf().equals(myEquipmt.getNumserie())
                                ? updateEquipmentController.getModNumbseriTf()
                                : null;

                        // Appel de la méthode updateEquipment avec les nouveaux champs
                        databaseHandler.updateEquipment(newDesignation, newModel, newMarque, newNumSer, myEquipmt.getEquipmentId());

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("SUCCES");
                        alert.setHeaderText(null);
                        alert.setContentText("L'équipement a bien été modifié!");
                        alert.showAndWait();

                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();

                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur");
                        alert.setHeaderText("Echec de mise à jour");
                        alert.setContentText("Une erreur s'est produite lors de la modification de l'équipement . Veuillez réessayer à nouveau.");
                        alert.showAndWait();
                    }
                });


                stage.setTitle("Modifier un équipement");

                Image ic = new Image(getClass().getResource("/logoMaintBio1.png").toExternalForm());
                stage.getIcons().add(ic);

                stage.show();


            });

            /*signPanneBtn.setOnMouseClicked(event -> {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("signalPaneView.fxml"));

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));

                SignalPaneController signalPanneController = loader.getController();

                // Pass the userId to the SignalPaneController
                signalPanneController.setUserId(userId);
                signalPanneController.setEquipmentId(getItem().getEquipmentId());

                // Additional logic for signalPanneController, if needed
                Panne panne = new Panne();
                signalPanneController.setDesripPanneTa(panne.getDescription());

                signalPanneController.getBtnSignal().setOnAction(event1 -> {

                    Calendar calendar = Calendar.getInstance();
                    java.sql.Timestamp timestamp = new java.sql.Timestamp(calendar.getTimeInMillis());

                    //System.out.println("EquipId " + panne.getEquipmentId());
                    //System.out.println("User ID: " + userId);
                    //System.out.println("Equipment ID: " + signalPanneController.getEquipmentId());

                    // Logic to handle signaling a panne...
                });

                stage.setTitle("Signaler une panne");
                Image ic = new Image(getClass().getResource("/logoMaintBio1.png").toExternalForm());
                stage.getIcons().add(ic);
                stage.show();
            });*/

            deleteBtn.setOnMouseClicked(event -> {
                try {
                    databaseHandler.deleteEquipment(equipmtId);
                    //System.out.println("ID Equipment "+ equipmtId);

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

    public int getUserId() {
        //System.out.println("from getUserId() " + userId);

        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
        //System.out.println("User ID set in CellEquipementController: " + this.userId);
    }
}
