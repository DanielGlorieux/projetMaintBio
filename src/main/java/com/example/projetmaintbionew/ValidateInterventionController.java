package com.example.projetmaintbionew;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class ValidateInterventionController {

    @FXML
    private JFXButton modBtnAddValidate;

    @FXML
    private JFXTextArea rappTF;

    private int interventionId;

    private boolean validated = false;

    @FXML
    void validateCliked(ActionEvent event) {
        validated = true;
        ((Stage) rappTF.getScene().getWindow()).close();
    }


    public JFXButton getModBtnAddValidate() {
        return modBtnAddValidate;
    }

    public void setModBtnAddValidate(JFXButton modBtnAddValidate) {
        this.modBtnAddValidate = modBtnAddValidate;
    }

    public String getRappTF() {
        return rappTF.getText();
    }

    /*public void setRappTF(String rappTF) {
        this.rappTF = rappTF;
    }*/

    public void setInterventionId(int interventionId) {

        this.interventionId = interventionId;
    }

    public boolean isValidated() {
        return validated;
    }
}
