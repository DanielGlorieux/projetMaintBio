package com.example.projetmaintbionew;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private JFXButton btnAdm;

    @FXML
    private JFXButton btnCo;

    @FXML
    private JFXTextField usrN;

    @FXML
    private JFXPasswordField usrPwd;

    @FXML
    private JFXButton logoBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Image icon1 = new Image(getClass().getResourceAsStream("/logoMaintBio1.png"));
        ImageView iconView1 = new ImageView(icon1);
        iconView1.setFitWidth(65);
        iconView1.setFitHeight(65);
        logoBtn.setGraphic(iconView1);
        logoBtn.setMinWidth(66);
        logoBtn.setMinHeight(66);

        btnAdm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                btnCo.getScene().getWindow().hide();
                Stage stage = new Stage();
                try{
                    Parent root = FXMLLoader.load(getClass().getResource("adminPageView.fxml"));
                    Scene scene = new Scene(root);
                    stage.setTitle("MaintBio");
                    //Image ic = new Image(getClass().getResource("/logo.png").toExternalForm());
                    //stage.getIcons().add(ic);
                    stage.setScene(scene);
                }catch (IOException e){
                    System.out.println(e);
                }
                stage.show();
            }
        });

    }
}