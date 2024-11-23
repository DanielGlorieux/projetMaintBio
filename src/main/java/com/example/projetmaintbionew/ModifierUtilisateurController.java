package com.example.projetmaintbionew;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import databaseHelper.DBHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ModifierUtilisateurController implements Initializable {

    @FXML
    private JFXButton btnUpDUsr;

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
    private MenuButton newProfileUsr;

    @FXML
    private JFXPasswordField newPwdUsr;

    private String selectedProfile;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        miProfUtili.setOnAction(event -> setSelectedProfile("Utilisateur"));
        miProfTI.setOnAction(event -> setSelectedProfile("Technicien_Intervenant"));
        miProfTC.setOnAction(event -> setSelectedProfile("Technicien_Chef"));
        miProfAdm.setOnAction(event -> setSelectedProfile("Administrateur"));

        /*btnUpDUsr.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBHandler databaseHandler = new DBHandler();
                String profile = getSelectedProfile();

                if(!nameUsr.getText().equals("")&& !profile.equals("") && ! newPwdUsr.getText().equals("")){

                    String userName = nameUsr.getText();
                    String password = newPwdUsr.getText();


                    Utilisateur user = new Utilisateur(userName,password,profile);


                    try {
                        DBHandler.updateUser(user);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                    // Remise des champs à vides
                    nameUsr.setText("");
                    newPwdUsr.setText("");

                    //profileUsr.setText("");

                }
            }
        });*/

        btnUpDUsr.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBHandler databaseHandler = new DBHandler();
                String profile = getSelectedProfile();

                if (!nameUsr.getText().equals("") && !profile.equals("") && !newPwdUsr.getText().equals("")) {

                    String userName = nameUsr.getText();
                    String password = newPwdUsr.getText();

                    Utilisateur user = new Utilisateur(userName, password, profile);

                    try {
                        DBHandler.updateUser(user);

                        // Show success alert
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Succes");
                        alert.setHeaderText(null);
                        alert.setContentText("Utilisateur modifier avec succes!");
                        alert.showAndWait();

                        // Clear the fields
                        nameUsr.setText("");
                        newPwdUsr.setText("");

                    } catch (SQLException e) {
                        // Show error alert for SQL exception
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur dans la base de données");
                        alert.setHeaderText("Echec de modification");
                        alert.setContentText("Une erreur s'est produite lors de la mise à jour de l'utilisateur. Veuillez réessayer.");
                        alert.showAndWait();
                    } catch (ClassNotFoundException e) {
                        // Show error alert for class not found exception
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur");
                        alert.setHeaderText("Echec de modification");
                        alert.setContentText("Une erreur système s'est produite. Veuillez contacter l'assistance.");
                        alert.showAndWait();
                    }

                } else {
                    // Show warning alert for missing fields
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Attention !");
                    alert.setHeaderText(null);
                    alert.setContentText("Veuillez remplir tous les champs obligatoires avant la mise à jour.");
                    alert.showAndWait();
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
}
