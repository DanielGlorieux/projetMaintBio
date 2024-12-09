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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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

    /*public void setModAnneServiceTf(DatePicker modAnneServiceTf) {
        this.modAnneServiceTf = modAnneServiceTf;
    }*/

    /*public void setModAnneServiceTf(Integer equipment) {
        this.modAnneServiceTf = equipment;
    }*/

    /*public JFXTextField getModDesignationTf() {
        return modDesignationTf;
    }*/

    public String getModDesignationTf() {
        return this.modDesignationTf.getText();
    }

    /*public void setModDesignationTf(JFXTextField modDesignationTf) {
        this.modDesignationTf = modDesignationTf;
    }*/

    public void setModDesignationTf(String equipment) {
        this.modDesignationTf.setText(equipment);
    }

    /*public JFXTextField getModMarqueTf() {
        return modMarqueTf;
    }*/

    public String getModMarqueTf() {
        return this.modMarqueTf.getText();
    }

    /*public void setModMarqueTf(JFXTextField modMarqueTf) {
        this.modMarqueTf = modMarqueTf;
    }*/

    public void setModMarqueTf(String equipment) {
        this.modMarqueTf.setText(equipment);
    }

    /*public JFXTextField getModModeleTf() {
        return modModeleTf;
    }*/

    public String getModModeleTf() {
        return this.modModeleTf.getText();
    }

    /*public void setModModeleTf(JFXTextField modModeleTf) {
        this.modModeleTf = modModeleTf;
    }*/

    public void setModModeleTf(String equipment) {
        this.modModeleTf.setText(equipment);
    }

    /*public JFXTextField getModNumbseriTf() {
        return modNumbseriTf;
    }*/

    public String getModNumbseriTf() {
        return this.modNumbseriTf.getText();
    }



    /*public void setModNumbseriTf(JFXTextField modNumbseriTf) {
        this.modNumbseriTf = modNumbseriTf;
    }*/

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
