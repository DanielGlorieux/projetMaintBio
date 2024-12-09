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
    private MenuItem miSalleOp;
    @FXML
    private MenuItem miSalleReveil;
    @FXML
    private MenuItem miSalleSterilisation;
    @FXML
    private MenuItem miBureauSUS;
    @FXML
    private MenuItem miBureauChefService;
    @FXML
    private MenuItem miBureauMedecin;
    @FXML
    private MenuItem miBureauAnesthesistes;
    @FXML
    private MenuItem miSallePreparationPatients;
    @FXML
    private MenuItem miSalleGarde;
    @FXML
    private MenuItem miSAS;
    @FXML
    private MenuItem miVestiaire;
    @FXML
    private MenuItem miMagasin;
    @FXML
    private MenuItem miSalleTravail;
    @FXML
    private MenuItem miBoxAccouchement;
    @FXML
    private MenuItem miPoolSurveillance;
    @FXML
    private MenuItem miSallePansement;
    @FXML
    private MenuItem miBureauSageFemme;
    @FXML
    private MenuItem miBureauInfirmier;
    @FXML
    private MenuItem miSalleStagiaire;
    @FXML
    private MenuItem miSalleStaff;
    @FXML
    private MenuItem miSalleEchographie;
    @FXML
    private MenuItem miSalleVidoir;
    @FXML
    private MenuItem miSalleNettoyageDesinfection;
    @FXML
    private MenuItem miBoxPrematures;
    @FXML
    private MenuItem miSalleHospitalisation;
    @FXML
    private MenuItem miSalleObservation;
    @FXML
    private MenuItem miSoinsInfirmiers;
    @FXML
    private MenuItem miSallePremiersSoins;
    @FXML
    private MenuItem miSalleTri;
    @FXML
    private MenuItem miSalleDechoquage;
    @FXML
    private MenuItem miSalleBiochimie;
    @FXML
    private MenuItem miSalleHematologie;
    @FXML
    private MenuItem miSalleImmunologie;
    @FXML
    private MenuItem miSalleBiologieMoleculaire;
    @FXML
    private MenuItem miSallePrelevements;
    @FXML
    private MenuItem miSalleBacteriologie;
    @FXML
    private MenuItem miSalleParasitologie;
    @FXML
    private MenuItem miLaverieSterilisation;
    @FXML
    private MenuItem miSallePreparationMilieuxCulture;
    @FXML
    private MenuItem miSalleBanqueSang;
    @FXML
    private MenuItem miSalleOsPoumon;
    @FXML
    private MenuItem miSalleMammographiePanoramiqueDentaire;
    @FXML
    private MenuItem miSalleTelecommandee;
    @FXML
    private MenuItem miSalleManipulation;
    @FXML
    private MenuItem miSalleInterpretationGenerale;
    @FXML
    private MenuItem miLocalReprographie;
    @FXML
    private MenuItem miSalleScanner;
    @FXML
    private MenuItem miSalleInterpretation;
    @FXML
    private MenuItem miAttenteGrabataires;
    @FXML
    private MenuItem miSalleEchographie1;
    @FXML
    private MenuItem miSalleEchographie2;
    @FXML
    private MenuItem miBureauRadiologue;
    @FXML
    private MenuItem miSalleIntrusionIRM;

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

        miSalleOp.setOnAction(event -> setSelectedSalleAff("Salle d’opération"));
        miSalleReveil.setOnAction(event -> setSelectedSalleAff("Salle de réveil"));
        miSalleSterilisation.setOnAction(event -> setSelectedSalleAff("Salle de stérilisation"));
        miBureauSUS.setOnAction(event -> setSelectedSalleAff("Bureau du SUS"));
        miBureauChefService.setOnAction(event -> setSelectedSalleAff("Bureau du chef de service"));
        miBureauMedecin.setOnAction(event -> setSelectedSalleAff("Bureau médecin"));
        miBureauAnesthesistes.setOnAction(event -> setSelectedSalleAff("Bureau des anesthésistes"));
        miSallePreparationPatients.setOnAction(event -> setSelectedSalleAff("Salle de préparation des patients"));
        miSalleGarde.setOnAction(event -> setSelectedSalleAff("Salle de garde"));
        miSAS.setOnAction(event -> setSelectedSalleAff("SAS"));
        miVestiaire.setOnAction(event -> setSelectedSalleAff("Vestiaire"));
        miMagasin.setOnAction(event -> setSelectedSalleAff("Magasin"));
        miSalleTravail.setOnAction(event -> setSelectedSalleAff("Salle de travail"));
        miBoxAccouchement.setOnAction(event -> setSelectedSalleAff("Boxe d’accouchement"));
        miPoolSurveillance.setOnAction(event -> setSelectedSalleAff("Pool de surveillance technique"));
        miSallePansement.setOnAction(event -> setSelectedSalleAff("Salle de pansement"));
        miBureauSageFemme.setOnAction(event -> setSelectedSalleAff("Bureau sage femme"));
        miBureauInfirmier.setOnAction(event -> setSelectedSalleAff("Bureau infirmier"));
        miSalleStagiaire.setOnAction(event -> setSelectedSalleAff("Salle stagiaire"));
        miSalleStaff.setOnAction(event -> setSelectedSalleAff("Salle de staff"));
        miSalleEchographie.setOnAction(event -> setSelectedSalleAff("Salle d’échographie"));
        miSalleVidoir.setOnAction(event -> setSelectedSalleAff("Salle de vidoir"));
        miSalleNettoyageDesinfection.setOnAction(event -> setSelectedSalleAff("Salle de nettoyage et désinfection"));
        miBoxPrematures.setOnAction(event -> setSelectedSalleAff("Box des prématurés"));
        miSalleHospitalisation.setOnAction(event -> setSelectedSalleAff("Salle d’hospitalisation"));
        miSalleObservation.setOnAction(event -> setSelectedSalleAff("Salle d’observation"));
        miSoinsInfirmiers.setOnAction(event -> setSelectedSalleAff("Soins infirmiers"));
        miSallePremiersSoins.setOnAction(event -> setSelectedSalleAff("Salle de premiers soins"));
        miSalleTri.setOnAction(event -> setSelectedSalleAff("Salle de tri"));
        miSalleDechoquage.setOnAction(event -> setSelectedSalleAff("Salle de déchoquage"));
        miSalleBiochimie.setOnAction(event -> setSelectedSalleAff("Salle de biochimie"));
        miSalleHematologie.setOnAction(event -> setSelectedSalleAff("Salle d’hématologie"));
        miSalleImmunologie.setOnAction(event -> setSelectedSalleAff("Salle d’immunologie"));
        miSalleBiologieMoleculaire.setOnAction(event -> setSelectedSalleAff("Salle de biologie moléculaire"));
        miSallePrelevements.setOnAction(event -> setSelectedSalleAff("Salle de prélèvements"));
        miSalleBacteriologie.setOnAction(event -> setSelectedSalleAff("Salle de bactériologie"));
        miSalleParasitologie.setOnAction(event -> setSelectedSalleAff("Salle de parasitologie"));
        miLaverieSterilisation.setOnAction(event -> setSelectedSalleAff("Laverie + stérilisation"));
        miSallePreparationMilieuxCulture.setOnAction(event -> setSelectedSalleAff("Salle de préparation des milieux de culture"));
        miSalleBanqueSang.setOnAction(event -> setSelectedSalleAff("Salle de banque de sang"));
        miSalleOsPoumon.setOnAction(event -> setSelectedSalleAff("Salle os/poumon"));
        miSalleMammographiePanoramiqueDentaire.setOnAction(event -> setSelectedSalleAff("Salle de mammographie + panoramique dentaire"));
        miSalleTelecommandee.setOnAction(event -> setSelectedSalleAff("Salle télécommandée"));
        miSalleManipulation.setOnAction(event -> setSelectedSalleAff("Salle de manipulation"));
        miSalleInterpretationGenerale.setOnAction(event -> setSelectedSalleAff("Salle d’interprétation générale"));
        miLocalReprographie.setOnAction(event -> setSelectedSalleAff("Local reprographie"));
        miSalleScanner.setOnAction(event -> setSelectedSalleAff("Salle scanner"));
        miSalleInterpretation.setOnAction(event -> setSelectedSalleAff("Salle d’interprétation"));
        miAttenteGrabataires.setOnAction(event -> setSelectedSalleAff("Attente malades grabataires"));
        miSalleEchographie1.setOnAction(event -> setSelectedSalleAff("Salle d’échographie 1"));
        miSalleEchographie2.setOnAction(event -> setSelectedSalleAff("Salle d’échographie 2"));
        miBureauRadiologue.setOnAction(event -> setSelectedSalleAff("Bureau radiologue"));
        miSalleIntrusionIRM.setOnAction(event -> setSelectedSalleAff("Salle d’intrusion pour IRM"));


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

        addEquipMenuBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouterEquipementView.fxml"));
                    Node ajouterEquipementView = loader.load();

                    // Pass user ID to ListEquipementController
                    AjouterEquipementController ajouterEquipementController = loader.getController();
                    ajouterEquipementController.setUserId(userId); // Use the userId from GestionEquipementController

                    changeP.getChildren().clear();
                    changeP.getChildren().add(ajouterEquipementView);
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

    public static int getUserId() {
        System.out.println("from getUserId() " + userId);

        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
        System.out.println("From setUserId " + this.userId);
    }
}
