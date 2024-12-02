package databaseHelper;
import com.example.projetmaintbionew.Equipement;
import com.example.projetmaintbionew.Panne;
import com.example.projetmaintbionew.PanneEquipmentData;
import com.example.projetmaintbionew.Utilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DBHandler extends Configuration{
    static Connection dbConnection;

    public static Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://"+dbHost + ":"
                + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString,dbUser,dbPassword);
        return dbConnection;
    }

    public static void addUser(Utilisateur user) {
        String insert = "INSERT INTO " + Constantes.USER_TABLE + "(" + Constantes.USER_NAME
                + "," + Constantes.USER_PASSWORD + "," + Constantes.USER_PROFILE + ")" + "VALUES(?,?,?)";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);

            preparedStatement.setString(1, user.getNom());
            preparedStatement.setString(2, user.getCode());
            preparedStatement.setString(3, user.getProf());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Methode d'écriture dans la base
    // Ajout d'un equipement

    public static void addEquipment(Equipement equip) {

        String insert = "INSERT INTO " + Constantes.EQUIPMENT_TABLE + " ( " + Constantes.USER_ID + ","
                + Constantes.EQUIPMENT_DESIGNATION + "," + Constantes.EQUIPMENT_MARQUE + "," + Constantes.EQUIPMENT_MODELE + "," +
                Constantes.EQUIPMENT_NUMSERIE + "," + Constantes.EQUIPMENT_ANNEACQUIS + "," + Constantes.EQUIPMENT_ANNESERV + "," +
                Constantes.EQUIPMENT_SOURCEACQUIS + "," + Constantes.EQUIPMENT_ETAT + "," + Constantes.EQUIPMENT_SERVICE + "," + Constantes.EQUIPMENT_SALLEAFF +")"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);

            preparedStatement.setInt(1, equip.getUserId());
            preparedStatement.setString(2, equip.getDesignation());
            preparedStatement.setString(3, equip.getMarque());
            preparedStatement.setString(4, equip.getModel());
            preparedStatement.setString(5, equip.getNumserie());
            preparedStatement.setTimestamp(6, equip.getAnneAcquis());
            preparedStatement.setInt(7, equip.getAnneMisServ());
            preparedStatement.setString(8, equip.getSourceAcquis());
            preparedStatement.setString(9, equip.getEtat());
            preparedStatement.setString(10, equip.getService());
            preparedStatement.setString(11, equip.getSalleAff());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    public static ResultSet getUser(Utilisateur user) {
        ResultSet resultSet = null;


        if (!user.getNom().equals("") || !user.getCode().equals("")) {
            String query = "SELECT * FROM " + Constantes.USER_TABLE + " WHERE "
                    + Constantes.USER_NAME + "=?" + " AND " + Constantes.USER_PASSWORD
                    + "=?";

            // select all from users where username="paulo" and password="password"

            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
                preparedStatement.setString(1, user.getNom());
                preparedStatement.setString(2, user.getCode());

                resultSet = preparedStatement.executeQuery();


            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        } else {
            System.out.println("Please enter your credentials");

        }
        return resultSet;
    }

    public static void updateUser(Utilisateur user) throws SQLException, ClassNotFoundException {
        String query = "UPDATE user SET password=?, profile=? WHERE name=?";


        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setString(1, user.getCode());
        preparedStatement.setString(2, user.getProf());
        preparedStatement.setString(3, user.getNom());
        // preparedStatement.setInt(4, userId);
        //preparedStatement.setInt(4, taskId);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public static ResultSet getEquipment() {

        ResultSet resultEquipment = null;

        String query = "SELECT * FROM " + Constantes.EQUIPMENT_TABLE;

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);

            resultEquipment = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return resultEquipment;
    }

    public void deleteEquipment(int equipmentId) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM " + Constantes.EQUIPMENT_TABLE + " WHERE "+
                Constantes.EQUIPMENT_ID + "=?";

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setInt(1, equipmentId);
        preparedStatement.execute();
        preparedStatement.close();
    }

    public void updateEquipment(String designation, String model, String marque, String numSer, int equipId) throws SQLException, ClassNotFoundException {

        String query = "UPDATE equipment SET designation=?, modele=?, marque=?, num_serie=? WHERE idequipment=?";


        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setString(1, designation);
        preparedStatement.setString(2, model);
        preparedStatement.setString(3, marque);
        preparedStatement.setString(4, numSer);
        preparedStatement.setInt(5, equipId);
        preparedStatement.executeUpdate();
        preparedStatement.close();

    }



    public void addPane(Panne panne) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO panne (idequipment, description, idUser) VALUES (?, ?, ?)";
        /*try {
            Connection conn = getDbConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(insert);
            preparedStatement.setInt(1, panne.getEquipmentId());
            preparedStatement.setString(2, panne.getDescription());
            preparedStatement.setInt(3, panne.getIdUser());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + e.getMessage());
            e.printStackTrace();
        }*/

        Connection conn = getDbConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(insert);
        preparedStatement.setInt(1, panne.getEquipmentId());
        preparedStatement.setString(2, panne.getDescription());
        preparedStatement.setInt(3, panne.getIdUser());
    }

    public static ObservableList<PanneEquipmentData> getPanneAndEquipmentData() {
        ObservableList<PanneEquipmentData> data = FXCollections.observableArrayList();

        String query = """
        SELECT 
        equipment.marque, 
        equipment.designation, 
        panne.description, 
        panne.type, 
        panne.statut 
        FROM projmaintbio.panne 
        LEFT JOIN projmaintbio.equipment 
        ON panne.idequipment = equipment.idequipment
        """;

        try (Connection conn = getDbConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String description = resultSet.getString("description");
                String marque = resultSet.getString("marque");
                String designation = resultSet.getString("designation");
                String type = resultSet.getString("type");
                String statut = resultSet.getString("statut");


                // Add data to the observable list
                data.add(new PanneEquipmentData(designation, marque,description, statut, type));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return data;
    }


    /*public void addPanne(String panne, String numSerie, String marque, String type) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO panne (description, type) VALUES (?, ?) ";
        //String query = "UPDATE equipment SET panne=? WHERE num_serie=? AND marque=?";


        Connection conn = getDbConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(insert);
        preparedStatement.setString(1, panne);
        preparedStatement.setString(2, type);
        preparedStatement.executeUpdate();
        preparedStatement.close();

    }*/

    /*public void addPanne(String panne, String numSerie, String marque, String type) throws SQLException, ClassNotFoundException {
        // Insert into panne table
        //String insertPanneQuery = "INSERT INTO panne (description, type) VALUES (?, ?)";
        String updateEquipmentQuery = "UPDATE equipment SET idpanne = ? WHERE num_serie = ? AND marque = ?";
        String insertPanneQuery = "INSERT INTO panne (description, type, idequipment) VALUES (?, ?, ?)";

        Connection conn = null;
        PreparedStatement insertPanneStmt = null;
        PreparedStatement updateEquipmentStmt = null;

        try {
            conn = getDbConnection();
            // Start a transaction
            conn.setAutoCommit(false);

            // Insert into panne and retrieve generated id
            insertPanneStmt = conn.prepareStatement(insertPanneQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            insertPanneStmt.setString(1, panne);
            insertPanneStmt.setString(2, type);
            insertPanneStmt.executeUpdate();

            // Retrieve generated idpanne
            int idpanne = 0;
            ResultSet rs = insertPanneStmt.getGeneratedKeys();
            if (rs.next()) {
                idpanne = rs.getInt(1);
            }
            rs.close();

            // Update the equipment table
            updateEquipmentStmt = conn.prepareStatement(updateEquipmentQuery);
            updateEquipmentStmt.setInt(1, idpanne);
            updateEquipmentStmt.setString(2, numSerie);
            updateEquipmentStmt.setString(3, marque);
            updateEquipmentStmt.executeUpdate();

            // Commit the transaction
            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback(); // Rollback transaction in case of error
            }
            throw e;
        } finally {
            // Close resources
            if (insertPanneStmt != null) {
                insertPanneStmt.close();
            }
            if (updateEquipmentStmt != null) {
                updateEquipmentStmt.close();
            }
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }*/

    public void addPanne(String panne, String numSerie, String marque, String type) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement insertPanneStmt = null;
        PreparedStatement selectEquipmentStmt = null;
        PreparedStatement updateEquipmentStmt = null;
        PreparedStatement updatePanneQueryStmt = null;
        ResultSet generatedKeys = null;
        ResultSet equipmentResult = null;

        try {
            conn = getDbConnection();
            conn.setAutoCommit(false); // Enable transaction management

            // Step 1: Insert into the panne table
            String insertPanneQuery = "INSERT INTO panne (description, type) VALUES (?, ?)";
            insertPanneStmt = conn.prepareStatement(insertPanneQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            insertPanneStmt.setString(1, panne);
            insertPanneStmt.setString(2, type);
            insertPanneStmt.executeUpdate();

            // Retrieve the generated idpanne
            generatedKeys = insertPanneStmt.getGeneratedKeys();
            if (!generatedKeys.next()) {
                throw new SQLException("Echec retrouvement de idpanne pour la nouvelle panne.");
            }
            int idpanne = generatedKeys.getInt(1);

            // Step 2: Retrieve the corresponding idequipment from the equipment table
            String selectEquipmentQuery = "SELECT idequipment FROM equipment WHERE num_serie = ? AND marque = ?";
            selectEquipmentStmt = conn.prepareStatement(selectEquipmentQuery);
            selectEquipmentStmt.setString(1, numSerie);
            selectEquipmentStmt.setString(2, marque);
            equipmentResult = selectEquipmentStmt.executeQuery();

            if (!equipmentResult.next()) {
                throw new SQLException("No equipment found with the specified num_serie and marque.");
            }
            int idequipment = equipmentResult.getInt("idequipment");

            // Step 3: Update the equipment table to associate the panne with the equipment
            String updateEquipmentQuery = "UPDATE equipment SET idpanne = ? WHERE idequipment = ?";
            updateEquipmentStmt = conn.prepareStatement(updateEquipmentQuery);
            updateEquipmentStmt.setInt(1, idpanne);
            updateEquipmentStmt.setInt(2, idequipment);
            updateEquipmentStmt.executeUpdate();

            // Step 4: Update the equipment table to associate the panne with the equipment
            String updatePanneQuery = "UPDATE panne SET idequipment = ? WHERE idpanne = ?";
            updatePanneQueryStmt = conn.prepareStatement(updatePanneQuery);
            updatePanneQueryStmt.setInt(1, idequipment);
            updatePanneQueryStmt.setInt(2, idpanne);
            updatePanneQueryStmt.executeUpdate();

            // Commit the transaction
            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback(); // Roll back the transaction in case of an error
            }
            throw e;
        } finally {
            // Clean up resources
            if (generatedKeys != null) generatedKeys.close();
            if (equipmentResult != null) equipmentResult.close();
            if (insertPanneStmt != null) insertPanneStmt.close();
            if (selectEquipmentStmt != null) selectEquipmentStmt.close();
            if (updateEquipmentStmt != null) updateEquipmentStmt.close();
            if (updatePanneQueryStmt != null) updatePanneQueryStmt.close();
            if (conn != null) conn.setAutoCommit(true); // Reset auto-commit
        }
    }



    public static ResultSet getPanne() {

        ResultSet resultPanne = null;

        String query = "SELECT * FROM panne";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);

            resultPanne = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return resultPanne;
    }

    public void deleteUser(String name) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM " + Constantes.USER_TABLE + " WHERE "+
                Constantes.USER_NAME + "=?";

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.execute();
        preparedStatement.close();
    }

    // Ajout de membre
    /*public void ajoutMembre(String nom, String prenom, String mail,
                           String contact, String adresse){

        if ( !nom.equals("") && !prenom.equals("")
                && !mail.equals("") && !contact.equals("") && !adresse.equals("")){

            String insert = "INSERT INTO " + Constantes.MEMBRE_TABLE + "("
                    + Constantes.MEMBRE_NOM + ","
                    + Constantes.MEMBRE_PRENOM + "," + Constantes.MEMBRE_MAIL + ","
                    + Constantes.MEMBRE_CONTACT + "," + Constantes.MEMBRE_ADRESSE + ") " + "VALUES(?,?,?,?,?)";

            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
                preparedStatement.setString(1,nom);
                preparedStatement.setString(2,prenom);
                preparedStatement.setString(3,mail);
                preparedStatement.setString(4,contact);
                preparedStatement.setString(5,adresse);

                preparedStatement.executeUpdate();


            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }

    }

    // Methode de lecture dans la base
    // Visualisation de livre
    public  ResultSet visulisationLivre() throws SQLException, ClassNotFoundException {
        String requete = " SELECT * FROM "+Constantes.LIVRE_TABLE;
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(requete);
        ResultSet resultSet = preparedStatement.executeQuery();


        return resultSet;
    }

    // Visualisation de membre
    public  ResultSet visulisationMembre() throws SQLException, ClassNotFoundException {
        String requete = " SELECT * FROM "+Constantes.MEMBRE_TABLE;
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(requete);
        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet;
    }

    // Methode de recherche dans la base
    public ResultSet rechercheLivreISBN(String text) throws SQLException, ClassNotFoundException {
        String requete = "SELECT * FROM "+Constantes.LIVRE_TABLE + " WHERE isbn = " + "\""+ text + "\"";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(requete);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public ResultSet rechercheLivreTitre(String text) throws SQLException, ClassNotFoundException {
        String requete = "SELECT * FROM "+Constantes.LIVRE_TABLE + " WHERE titre = " + "\""+ text + "\"";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(requete);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public ResultSet rechercheLivreAuteur(String text) throws SQLException, ClassNotFoundException {
        String requete = "SELECT * FROM "+Constantes.LIVRE_TABLE + " WHERE auteur = " + "\""+ text + "\"";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(requete);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public ResultSet rechercheLivreGenre(String text) throws SQLException, ClassNotFoundException {
        String requete = "SELECT * FROM "+Constantes.LIVRE_TABLE + " WHERE genre = " + "\""+ text + "\"";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(requete);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }



    // Methode pour supprimer un élément de la base
    public void suppressionLivreISBN(String text) throws SQLException, ClassNotFoundException {
        String requete = "DELETE FROM " + Constantes.LIVRE_TABLE + " WHERE isbn = " + "\"" + text + "\"";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(requete);
        preparedStatement.executeUpdate();

    }

    public void suppressionLivreTitre(String text) throws SQLException, ClassNotFoundException {
        String requete = "DELETE FROM " + Constantes.LIVRE_TABLE + " WHERE titre = " + "\"" + text + "\"";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(requete);
        preparedStatement.executeUpdate();

    }

    public void suppressionLivreAuteur(String text) throws SQLException, ClassNotFoundException {
        String requete = "DELETE FROM " + Constantes.LIVRE_TABLE + " WHERE auteur = " + "\"" + text + "\"";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(requete);
        preparedStatement.executeUpdate();

    }

    public void suppressionLivreGenre(String text) throws SQLException, ClassNotFoundException {
        String requete = "DELETE FROM " + Constantes.LIVRE_TABLE + " WHERE genre = " + "\"" + text + "\"";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(requete);
        preparedStatement.executeUpdate();

    }


    // Ajout de livre
    public void ajoutEmprunt(String idLivre, String idMembre){

        if ( !idLivre.equals("") && !idMembre.equals("")){

            String insert = "INSERT INTO " + Constantes.EMPRUNT_TABLE + "("
                    + Constantes.EMPRUNT_IDLIVRE + ","
                    + Constantes.EMPRUNT_IDMEMBRE + "," + Constantes.EMPRUNT_DATEEMPRUNT + ","
                    + Constantes.EMPRUNT_DATEECHEANCE + ") " + "VALUES(?,?,CURDATE(), DATE_ADD(CURDATE(), INTERVAL 3 MONTH))";

            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
                preparedStatement.setString(1,idLivre);
                preparedStatement.setString(2,idMembre);

                preparedStatement.executeUpdate();


            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }

    }*/


    // Visualisation de emprunt
    /*public  ResultSet visulisationEmprunt() throws SQLException, ClassNotFoundException {
        String requete = "SELECT m.prenom AS borrower_firstname, m.nom AS borrower_lastname, " +
                "m.contact AS borrower_contact, l.titre AS book_title, e.dateEmprunt AS borrow_date, " +
                "e.dateEcheance AS due_date FROM emprunt e " +
                "JOIN membre m ON e.idmembre = m.idmembre " +
                "JOIN livre l ON e.idlivre = l.idlivre;";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(requete);
        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet;
    }*/

    /*public ResultSet visualisationEmprunt() throws SQLException, ClassNotFoundException {
        String requete = "SELECT * FROM biblioproj.emprunt";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(requete);
        return preparedStatement.executeQuery();
    }

    public ResultSet visualisationRetour() throws SQLException, ClassNotFoundException {
        String requete = "SELECT * FROM " + Constantes.RETOUR_TABLE;
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(requete);
        return preparedStatement.executeQuery();
    }

    // Ajout de retour
    public void ajoutRetour(String idLivre, String idMembre){

        if ( !idLivre.equals("") && !idMembre.equals("")){

            String insert = "INSERT INTO " + Constantes.RETOUR_TABLE + "("
                    + Constantes.RETOUR_IDLIVRE + ","
                    + Constantes.RETOUR_IDMEMBRE + "," + Constantes.RETOUR_DATERETOUR + ") " + "VALUES(?,?,CURDATE())";

            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
                preparedStatement.setString(1,idLivre);
                preparedStatement.setString(2,idMembre);

                preparedStatement.executeUpdate();


            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }

    }
*/




    // Methode pour compter le nombre d'hommes dans la base ( pour le graphique )

/*    public ResultSet compterH() throws SQLException, ClassNotFoundException {

        String requete = "SELECT * FROM " + Constantes.ETUDIANTS_TABLE + " WHERE sexe = " + "\"" + "M" + "\"";
        ResultSet a;
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(requete);
        a = preparedStatement.executeQuery();
        return a;
    }*/

    // Methode pour compter le nombre de femmes dans la base ( pour le graphique )

/*    public ResultSet compterF() throws SQLException, ClassNotFoundException {

        String requete = "SELECT * FROM " + Constantes.ETUDIANTS_TABLE + " WHERE sexe = " + "\"" + "F" + "\"";
        ResultSet a;
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(requete);
        a = preparedStatement.executeQuery();
        return a;
    }*/

}
