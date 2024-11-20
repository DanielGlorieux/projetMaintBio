package com.example.projetmaintbionew;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;


public class AdminPageController implements Initializable {

    @FXML
    private JFXTextField admN;

    @FXML
    private JFXPasswordField admPwd;

    @FXML
    private JFXButton btnCoAdm;


    @FXML
    private JFXButton logoBtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Image icon2 = new Image(getClass().getResourceAsStream("/logoMaintBio1.png"));
        ImageView iconView2 = new ImageView(icon2);
        iconView2.setFitWidth(65);
        iconView2.setFitHeight(65);
        logoBtn.setGraphic(iconView2);
        logoBtn.setMinWidth(66);
        logoBtn.setMinHeight(66);

    }
}
