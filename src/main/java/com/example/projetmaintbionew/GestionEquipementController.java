package com.example.projetmaintbionew;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import databaseHelper.DBHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;

public class GestionEquipementController implements Initializable {

    private static int userId;

    private DBHandler databaseHandler;

    @FXML
    private JFXButton addEquipMenuBtn;

    @FXML
    private DatePicker anneServiceTf;

    @FXML
    private JFXButton btnAddValidate;

    @FXML
    private HBox change;

    @FXML
    private VBox changeP;

    @FXML
    private JFXButton deleteEquipMenuBtn;

    @FXML
    private JFXTextField designationTf;

    @FXML
    private MenuButton etatEquipMb;

    @FXML
    private JFXTextField marqueTf;

    @FXML
    private MenuItem miEtatEquipBon;

    @FXML
    private MenuItem miEtatEquipMauv;

    @FXML
    private MenuItem miEtatEquipPass;

    @FXML
    private MenuItem miSalleAff1;

    @FXML
    private MenuItem miSalleAff2;

    @FXML
    private MenuItem miSalleAff3;

    @FXML
    private MenuItem miSalleAff4;

    @FXML
    private MenuItem miSalleAff5;

    @FXML
    private MenuItem miServAffBlocOp;

    @FXML
    private MenuItem miServAffImg;

    @FXML
    private MenuItem miServAffLabo;

    @FXML
    private MenuItem miServAffMat;

    @FXML
    private MenuItem miServAffPed;

    @FXML
    private MenuItem miServAffUrg;

    @FXML
    private MenuItem miSourceAcquisEtat;

    @FXML
    private MenuItem miSourceAcquisFondP;

    @FXML
    private MenuItem miSourceAcquisPart;

    @FXML
    private JFXTextField modTf;

    @FXML
    private JFXTextField numbseriTf;

    @FXML
    private MenuButton salleAffMb;

    @FXML
    private MenuButton servAffMb;

    @FXML
    private MenuButton srcAcquisMb;

    @FXML
    private JFXButton updEquipMenuBtn;

    @FXML
    private JFXButton visEquipMenuBtn;

    private String selectedSourceAcquis;
    private String selectedEtatEquip;
    private String selectedServiceAff;
    private String selectedSalleAff;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        databaseHandler = new DBHandler();
        Equipement equipement = new Equipement();

        miSourceAcquisEtat.setOnAction(event -> setSelectedSourceAcquis("Etat"));
        miSourceAcquisFondP.setOnAction(event -> setSelectedSourceAcquis("Fonds_Propre"));
        miSourceAcquisPart.setOnAction(event -> setSelectedSourceAcquis("Partenaire"));


        miEtatEquipBon.setOnAction(event -> setSelectedEtatEquip("Bon"));
        miEtatEquipMauv.setOnAction(event -> setSelectedEtatEquip("Mauvais"));
        miEtatEquipPass.setOnAction(event -> setSelectedEtatEquip("Passable"));


        miServAffBlocOp.setOnAction(event -> setSelectedServiceAff("Bloc_Operatoire"));
        miServAffImg.setOnAction(event -> setSelectedServiceAff("Imagerie_Médicale"));
        miServAffLabo.setOnAction(event -> setSelectedServiceAff("Laboratoire_Biologie_Medicale"));
        miServAffMat.setOnAction(event -> setSelectedServiceAff("Maternite"));
        miServAffPed.setOnAction(event -> setSelectedServiceAff("Pediatrie"));
        miServAffUrg.setOnAction(event -> setSelectedServiceAff("Urgences"));

        miSalleAff1.setOnAction(event -> setSelectedSalleAff("Salle1"));
        miSalleAff2.setOnAction(event -> setSelectedSalleAff("Salle2"));
        miSalleAff3.setOnAction(event -> setSelectedSalleAff("Salle3"));
        miSalleAff4.setOnAction(event -> setSelectedSalleAff("Salle4"));
        miSalleAff5.setOnAction(event -> setSelectedSalleAff("Salle5"));


        btnAddValidate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Calendar calendar = Calendar.getInstance();

                java.sql.Timestamp timestamp =
                        new java.sql.Timestamp(calendar.getTimeInMillis());

                String desigationText = designationTf.getText().trim();
                String marqueText = marqueTf.getText().trim();
                String modelText = modTf.getText().trim();
                String numSerieText = numbseriTf.getText().trim();
                int annee = getYearFromDatePicker(anneServiceTf);

                if (!desigationText.equals("") || !marqueText.equals("") || !modelText.equals("") ||
                !numSerieText.equals("")){

                    System.out.println("User Id: " + GestionEquipementController.userId);

                    equipement.setUserId(MainPageController.userId);
                    equipement.setDesignation(desigationText);
                    equipement.setEtat(selectedEtatEquip);
                    equipement.setModel(modelText);
                    equipement.setNumserie(numSerieText);
                    equipement.setMarque(marqueText);
                    equipement.setService(selectedServiceAff);
                    equipement.setSourceAcquis(selectedSourceAcquis);
                    equipement.setSalleAff(selectedSalleAff);
                    equipement.setAnneMisServ(annee);
                    equipement.setAnneAcquis(timestamp);

                    DBHandler.addEquipment(equipement);

                    designationTf.setText("");
                    marqueTf.setText("");
                    modTf.setText("");
                    numbseriTf.setText("");
                    anneServiceTf.setValue(null);


                }

            }
        });

        updEquipMenuBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                /*try {
                    changeP.getChildren().clear();
                    changeP.getChildren().add((Node)FXMLLoader.load(getClass().getResource("listEquipementView.fxml")) );

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }*/

                /*try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("listEquipementView.fxml"));
                    Node uptEquipementView = loader.load();
                    UpdateEquipmentFormController  updtEquipementController = loader.getController();
                    updtEquipementController.setUserId(userId);

                    changeP.getChildren().clear();
                    changeP.getChildren().add(uptEquipementView);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }*/

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("listEquipementView.fxml"));
                    Node listEquipementView = loader.load();

                    // Pass user ID to ListEquipementController
                    ListEquipementController listEquipementController = loader.getController();
                    listEquipementController.setUserId(userId); // Use the userId from GestionEquipementController

                    changeP.getChildren().clear();
                    changeP.getChildren().add(listEquipementView);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    public Integer getYearFromDatePicker(DatePicker datePicker) {
        LocalDate selectedDate = datePicker.getValue(); // Get the selected date
        if (selectedDate != null) {
            int year = selectedDate.getYear(); // Extract the year
            //System.out.println("Selected year: " + year);
            return year;

        } else {
            System.out.println("No date selected");
            return null;
        }
    }



    private void setSelectedSourceAcquis(String sourceA) {
        this.selectedSourceAcquis = sourceA;
        //System.out.println("Selected Profile: " + selectedProfile);
        // You can perform additional actions here based on the selected profile
    }

    public String getSelectedSourceAcquis() {
        return selectedSourceAcquis;
    }


    private void setSelectedEtatEquip(String etatE) {
        this.selectedEtatEquip = etatE;
        //System.out.println("Selected Profile: " + selectedProfile);
        // You can perform additional actions here based on the selected profile
    }

    public String getSelectedEtatEquip() {
        return selectedEtatEquip;
    }



    private void setSelectedServiceAff(String servA) {
        this.selectedServiceAff = servA;
        //System.out.println("Selected Profile: " + selectedProfile);
        // You can perform additional actions here based on the selected profile
    }

    public String getSelectedServiceAff() {
        return selectedServiceAff;
    }



    private void setSelectedSalleAff(String salleA) {
        this.selectedSalleAff = salleA;
        //System.out.println("Selected Profile: " + selectedProfile);
        // You can perform additional actions here based on the selected profile
    }

    public String getSelectedSalleAff() {
        return selectedSalleAff;
    }

    public int getUserId() {
        System.out.println("from getUserId() " + userId);

        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
        System.out.println("From setUserId " + this.userId);
    }
}
