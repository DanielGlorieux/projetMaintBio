package com.example.projetmaintbionew;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import databaseHelper.DBHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.MenuButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {


    @FXML
    private JFXButton addUsrBtn;

    @FXML
    private JFXButton btnCreatUsr;

    @FXML
    private HBox change;

    @FXML
    private VBox changeP;

    @FXML
    private JFXButton deleteUsrBtn;

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
    private MenuItem miProfAdm;

    @FXML
    private MenuItem miProfTC;

    @FXML
    private MenuItem miProfTI;

    @FXML
    private MenuItem miProfUtili;

    @FXML
    private JFXTextField nameUsr;

    @FXML
    private MenuButton profileUsr;

    @FXML
    private JFXPasswordField pwdUsr;

    @FXML
    private JFXButton signalPanBtn;

    @FXML
    private JFXButton updUsrBtn;

    @FXML
    private JFXButton usrGesBtn;

    private String selectedProfile;
    public static int userId;

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

        final String[] profileTexte = new String[1];

        miProfUtili.setOnAction(event -> setSelectedProfile("Utilisateur"));
        miProfTI.setOnAction(event -> setSelectedProfile("Technicien_Intervenant"));
        miProfTC.setOnAction(event -> setSelectedProfile("Technicien_Chef"));
        miProfAdm.setOnAction(event -> setSelectedProfile("Administrateur"));

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

        addUsrBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    changeP.getChildren().clear();
                    changeP.getChildren().add((Node) FXMLLoader.load(getClass().getResource("ajouterUtilisateur.fxml")));
                    //changePane.setVisible(true);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        btnCreatUsr.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBHandler databaseHandler = new DBHandler();

                String profile = getSelectedProfile();


                if(!nameUsr.getText().equals("")&& !profile.equals("") && ! pwdUsr.getText().equals("")){

                    String userName = nameUsr.getText();
                    String password = pwdUsr.getText();


                    Utilisateur user = new Utilisateur(userName,password,profile);


                    DBHandler.addUser(user);

                    // Remise des champs à vides
                    nameUsr.setText("");
                    pwdUsr.setText("");

                    //profileUsr.setText("");

                }
            }
        });

        updUsrBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                try {
                    changeP.getChildren().clear();
                    changeP.getChildren().add((Node) FXMLLoader.load(getClass().getResource("modifierUtilisateurView.fxml")));
                    //changePane.setVisible(true);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        equipGesBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                /*try {
                    change.getChildren().clear();
                    change.getChildren().add((Node) FXMLLoader.load(getClass().getResource("gestionEquipementView.fxml")));
                    MainPageController.userId = getUserId();
                    //changePane.setVisible(true);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }*/

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("gestionEquipementView.fxml"));
                    Node gestionEquipementView = loader.load();
                    GestionEquipementController gestionEquipementController = loader.getController();
                    gestionEquipementController.setUserId(userId);

                    change.getChildren().clear();
                    change.getChildren().add(gestionEquipementView);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void setSelectedProfile(String profile) {
        this.selectedProfile = profile;
        //System.out.println("Selected Profile: " + selectedProfile);
        // You can perform additional actions here based on the selected profile
    }

    public String getSelectedProfile() {
        return selectedProfile;
    }

    public void setUserId(int userId) {

        this.userId = userId;
        System.out.println("User Id is " + this.userId);

    }

    public int getUserId(){
        return this.userId;
    }
}
