package com.example.projetmaintbionew;


import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {

    @FXML
    private HBox change;

    @FXML
    private JFXButton equipGesBtn;

    @FXML
    private JFXButton equipGesBtn1;

    @FXML
    private JFXButton exitBtn;

    @FXML
    private JFXButton intervGesBtn;

    @FXML
    private JFXButton logoBtn;

    @FXML
    private JFXButton signalPanBtn;

    @FXML
    private JFXButton usrGesBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Image icon2 = new Image(getClass().getResourceAsStream("/logoMaintBio1.png"));
        ImageView iconView2 = new ImageView(icon2);
        iconView2.setFitWidth(65);
        iconView2.setFitHeight(65);
        logoBtn.setGraphic(iconView2);
        logoBtn.setMinWidth(66);
        logoBtn.setMinHeight(66);

        Image icon1 = new Image(getClass().getResourceAsStream("/logOut.png"));
        ImageView iconView1 = new ImageView(icon1);
        iconView1.setFitWidth(20);
        iconView1.setFitHeight(20);
        exitBtn.setGraphic(iconView1);
        exitBtn.setMinWidth(125);
        exitBtn.setMinHeight(34);

        Image icon3 = new Image(getClass().getResourceAsStream("/users.png"));
        ImageView iconView3 = new ImageView(icon3);
        iconView3.setFitWidth(15);
        iconView3.setFitHeight(15);
        usrGesBtn.setGraphic(iconView3);
        usrGesBtn.setMinWidth(197);
        usrGesBtn.setMinHeight(34);

        Image icon4 = new Image(getClass().getResourceAsStream("/equipement.png"));
        ImageView iconView4 = new ImageView(icon4);
        iconView4.setFitWidth(15);
        iconView4.setFitHeight(15);
        equipGesBtn.setGraphic(iconView4);
        equipGesBtn.setMinWidth(197);
        equipGesBtn.setMinHeight(34);

        Image icon5 = new Image(getClass().getResourceAsStream("/signals.png"));
        ImageView iconView5 = new ImageView(icon5);
        iconView5.setFitWidth(15);
        iconView5.setFitHeight(15);
        signalPanBtn.setGraphic(iconView5);
        signalPanBtn.setMinWidth(197);
        signalPanBtn.setMinHeight(34);

        usrGesBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    change.getChildren().clear();
                    change.getChildren().add((Node) FXMLLoader.load(getClass().getResource("gestionUtilisateurView.fxml")));
                    //changePane.setVisible(true);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
