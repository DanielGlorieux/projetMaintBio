package com.example.projetmaintbionew;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateEquipmentFormController implements Initializable {

    private static int userId;

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
    private DatePicker modAnneServiceTf;

    @FXML
    private JFXButton modBtnAddValidate;

    @FXML
    private JFXTextField modDesignationTf;

    @FXML
    private MenuButton modEtatEquipMb;

    @FXML
    private JFXTextField modMarqueTf;

    @FXML
    private JFXTextField modModeleTf;

    @FXML
    private JFXTextField modNumbseriTf;

    @FXML
    private MenuButton modSalleAffMb;

    @FXML
    private MenuButton modServAffMb;

    @FXML
    private MenuButton modSrcAcquisMb;

    public String selectedSalleAff;

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

    public String servSelectionner;

    private String serviceAffectation;

    private String selectedServAff;

    private String selectedSourceAcquis;

    private String selectedEtatEquip;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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

        miServAffBlocOp.setOnAction(event -> setSelectedServAff("Bloc Opératoire"));
        miServAffImg.setOnAction(event -> setSelectedServAff("Imagerie médicale"));
        miServAffLabo.setOnAction(event -> setSelectedServAff("Laboratoire de biologie médicale"));
        miServAffMat.setOnAction(event -> setSelectedServAff("Maternité"));
        miServAffPed.setOnAction(event -> setSelectedServAff("Pédiatrie"));
        miServAffUrg.setOnAction(event -> setSelectedServAff("Urgences médicales et chirurgicales"));

        miSourceAcquisEtat.setOnAction(event -> setSelectedSourceAcquis("Etat"));
        miSourceAcquisFondP.setOnAction(event -> setSelectedSourceAcquis("Fonds_Propre"));
        miSourceAcquisPart.setOnAction(event -> setSelectedSourceAcquis("Partenaire"));

        miEtatEquipBon.setOnAction(event -> setSelectedEtatEquip("Bon"));
        miEtatEquipMauv.setOnAction(event -> setSelectedEtatEquip("Mauvais"));
        miEtatEquipPass.setOnAction(event -> setSelectedEtatEquip("Passable"));




    }

    private void setSelectedEtatEquip(String etatE) {
        this.selectedEtatEquip = etatE;
        //System.out.println("Selected Profile: " + selectedProfile);
        // You can perform additional actions here based on the selected profile
    }

    public String getSelectedEtatEquip() {
        return selectedEtatEquip;
    }

    private void setSelectedSourceAcquis(String sourceA) {
        this.selectedSourceAcquis = sourceA;
        //System.out.println("Selected Profile: " + selectedProfile);
        // You can perform additional actions here based on the selected profile
    }

    public String getSelectedSourceAcquis() {
        return selectedSourceAcquis;
    }

    private void setSelectedSalleAff(String salleA) {
        this.selectedSalleAff = salleA;
        //System.out.println("Selected Profile: " + selectedProfile);
        // You can perform additional actions here based on the selected profile
    }

    private void setSelectedServAff(String servA) {
        this.selectedServAff = servA;
        //System.out.println("Selected Profile: " + selectedProfile);
        // You can perform additional actions here based on the selected profile
    }

    public String getSelectedServAff(){
        return selectedServAff;
    }

    public MenuItem getMiEtatEquipMauv() {
        return miEtatEquipMauv;
    }

    public void setMiEtatEquipMauv(MenuItem miEtatEquipMauv) {
        this.miEtatEquipMauv = miEtatEquipMauv;
    }

    public MenuItem getMiEtatEquipPass() {
        return miEtatEquipPass;
    }

    public void setMiEtatEquipPass(MenuItem miEtatEquipPass) {
        this.miEtatEquipPass = miEtatEquipPass;
    }

    public MenuItem getMiSalleAff1() {
        return miSalleAff1;
    }

    public void setMiSalleAff1(MenuItem miSalleAff1) {
        this.miSalleAff1 = miSalleAff1;
    }

    public MenuItem getMiSalleAff2() {
        return miSalleAff2;
    }

    public void setMiSalleAff2(MenuItem miSalleAff2) {
        this.miSalleAff2 = miSalleAff2;
    }

    public MenuItem getMiSalleAff3() {
        return miSalleAff3;
    }

    public void setMiSalleAff3(MenuItem miSalleAff3) {
        this.miSalleAff3 = miSalleAff3;
    }

    public MenuItem getMiSalleAff4() {
        return miSalleAff4;
    }

    public void setMiSalleAff4(MenuItem miSalleAff4) {
        this.miSalleAff4 = miSalleAff4;
    }

    public MenuItem getMiSalleAff5() {
        return miSalleAff5;
    }

    public void setMiSalleAff5(MenuItem miSalleAff5) {
        this.miSalleAff5 = miSalleAff5;
    }

    public MenuItem getMiServAffBlocOp() {
        return miServAffBlocOp;
    }

    public void setMiServAffBlocOp(MenuItem miServAffBlocOp) {
        this.miServAffBlocOp = miServAffBlocOp;
    }

    public MenuItem getMiServAffImg() {
        return miServAffImg;
    }

    public void setMiServAffImg(MenuItem miServAffImg) {
        this.miServAffImg = miServAffImg;
    }

    public MenuItem getMiServAffLabo() {
        return miServAffLabo;
    }

    public void setMiServAffLabo(MenuItem miServAffLabo) {
        this.miServAffLabo = miServAffLabo;
    }

    public MenuItem getMiServAffMat() {
        return miServAffMat;
    }

    public void setMiServAffMat(MenuItem miServAffMat) {
        this.miServAffMat = miServAffMat;
    }

    public MenuItem getMiServAffPed() {
        return miServAffPed;
    }

    public void setMiServAffPed(MenuItem miServAffPed) {
        this.miServAffPed = miServAffPed;
    }

    public MenuItem getMiServAffUrg() {
        return miServAffUrg;
    }

    public void setMiServAffUrg(MenuItem miServAffUrg) {
        this.miServAffUrg = miServAffUrg;
    }

    public MenuItem getMiSourceAcquisEtat() {
        return miSourceAcquisEtat;
    }

    public void setMiSourceAcquisEtat(MenuItem miSourceAcquisEtat) {
        this.miSourceAcquisEtat = miSourceAcquisEtat;
    }

    public MenuItem getMiSourceAcquisFondP() {
        return miSourceAcquisFondP;
    }

    public void setMiSourceAcquisFondP(MenuItem miSourceAcquisFondP) {
        this.miSourceAcquisFondP = miSourceAcquisFondP;
    }

    public MenuItem getMiSourceAcquisPart() {
        return miSourceAcquisPart;
    }

    public void setMiSourceAcquisPart(MenuItem miSourceAcquisPart) {
        this.miSourceAcquisPart = miSourceAcquisPart;
    }

    public JFXButton getModBtnAddValidate() {
        return modBtnAddValidate;
    }

    public void setModBtnAddValidate(JFXButton modBtnAddValidate) {
        this.modBtnAddValidate = modBtnAddValidate;
    }

    public MenuButton getModEtatEquipMb() {
        return modEtatEquipMb;
    }

    public void setModEtatEquipMb(MenuButton modEtatEquipMb) {
        this.modEtatEquipMb = modEtatEquipMb;
    }

    public MenuButton getModSalleAffMb() {
        return modSalleAffMb;
    }

    public void setModSalleAffMb(MenuButton modSalleAffMb) {
        this.modSalleAffMb = modSalleAffMb;
    }

    public MenuButton getModServAffMb() {
        return modServAffMb;
    }

    public void setModServAffMb(MenuButton modServAffMb) {
        this.modServAffMb = modServAffMb;
    }

    public MenuButton getModSrcAcquisMb() {
        return modSrcAcquisMb;
    }

    public void setModSrcAcquisMb(MenuButton modSrcAcquisMb) {
        this.modSrcAcquisMb = modSrcAcquisMb;
    }

    public MenuItem getMiEtatEquipBon() {
        return miEtatEquipBon;
    }

    public void setMiEtatEquipBon(MenuItem miEtatEquipBon) {
        this.miEtatEquipBon = miEtatEquipBon;
    }

    public DatePicker getModAnneServiceTf() {
        return modAnneServiceTf;
    }

    public String getModDesignationTf() {
        return this.modDesignationTf.getText();
    }

    public void setModDesignationTf(String equipment) {
        this.modDesignationTf.setText(equipment);
    }

    public String getModMarqueTf() {
        return this.modMarqueTf.getText();
    }

    public void setModMarqueTf(String equipment) {
        this.modMarqueTf.setText(equipment);
    }

    public void setServiceAffectation(String serviceAffectation) {
        this.serviceAffectation = serviceAffectation;
    }

    public String getServiceAffectation() {
        return this.serviceAffectation;
    }

    public String getModModeleTf() {
        return this.modModeleTf.getText();
    }

    public void setModModeleTf(String equipment) {
        this.modModeleTf.setText(equipment);
    }

    public String getModNumbseriTf() {
        return this.modNumbseriTf.getText();
    }



    /*public void setModNumbseriTf(JFXTextField modNumbseriTf) {
        this.modNumbseriTf = modNumbseriTf;
    }*/

    public String getSelectedSalleAff() {
        return selectedSalleAff;
    }

    public void setModNumbseriTf(String equipment) {
        this.modNumbseriTf.setText(equipment);
    }

    public int getUserId() {
        //System.out.println("from getUserId() " + userId);

        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
        //System.out.println("FromUpdate setUserId " + this.userId);
    }
}
