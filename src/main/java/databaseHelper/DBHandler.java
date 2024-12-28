package databaseHelper;
import com.example.projetmaintbionew.Equipement;
import com.example.projetmaintbionew.Panne;
import com.example.projetmaintbionew.PanneEquipmentData;
import com.example.projetmaintbionew.Utilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


        String insert = "INSERT INTO " + Constantes.EQUIPMENT_TABLE + " ( " + Constantes.EQUIPMENT_DESIGNATION + "," + Constantes.EQUIPMENT_MARQUE + "," + Constantes.EQUIPMENT_MODELE + "," +
                Constantes.EQUIPMENT_NUMSERIE + "," + Constantes.EQUIPMENT_ANNEACQUIS + "," + Constantes.EQUIPMENT_ANNESERV + "," +
                Constantes.EQUIPMENT_SOURCEACQUIS + "," + Constantes.EQUIPMENT_ETAT + "," + Constantes.EQUIPMENT_SERVICE + "," + Constantes.EQUIPMENT_SALLEAFF +")"
                + "VALUES(?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);

            preparedStatement.setString(1, equip.getDesignation());
            preparedStatement.setString(2, equip.getMarque());
            preparedStatement.setString(3, equip.getModel());
            preparedStatement.setString(4, equip.getNumserie());
            preparedStatement.setDate(5, equip.getAnneAcquis());
            preparedStatement.setInt(6, equip.getAnneMisServ());
            preparedStatement.setString(7, equip.getSourceAcquis());
            preparedStatement.setString(8, equip.getEtat());
            preparedStatement.setString(9, equip.getService());
            preparedStatement.setString(10, equip.getSalleAff());

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

        String query = "SELECT * FROM " + Constantes.EQUIPMENT_TABLE + " ORDER BY designation ASC, service ASC";

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

    /*public void updateEquipment(String designation, String model, String marque, String numSer, int equipId) throws SQLException, ClassNotFoundException {

        String query = "UPDATE equipment SET designation=?, modele=?, marque=?, num_serie=? WHERE idequipment=?";


        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setString(1, designation);
        preparedStatement.setString(2, model);
        preparedStatement.setString(3, marque);
        preparedStatement.setString(4, numSer);
        preparedStatement.setInt(5, equipId);
        preparedStatement.executeUpdate();
        preparedStatement.close();

    }*/


    public void updateEquipment(String designation, String model, String marque, String numSer, String salleAff, String servAff, String srcAcq, String etatAcq, int equipId) throws SQLException, ClassNotFoundException {
        // Base de la requête
        StringBuilder queryBuilder = new StringBuilder("UPDATE equipment SET ");
        List<Object> parameters = new ArrayList<>();

        // Ajout dynamique des colonnes modifiées
        if (designation != null) {
            queryBuilder.append("designation=?, ");
            parameters.add(designation);
        }
        if (model != null) {
            queryBuilder.append("modele=?, ");
            parameters.add(model);
        }
        if (marque != null) {
            queryBuilder.append("marque=?, ");
            parameters.add(marque);
        }
        if (numSer != null) {
            queryBuilder.append("num_serie=?, ");
            parameters.add(numSer);
        }

        if (salleAff != null) {
            queryBuilder.append("salle_aff=?, ");
            parameters.add(salleAff);
        }

        if (servAff != null) {
            queryBuilder.append("service=?, ");
            parameters.add(servAff);
        }

        if (srcAcq != null) {
            queryBuilder.append("source_acquis=?, ");
            parameters.add(srcAcq);
        }

        if (etatAcq != null) {
            queryBuilder.append("etat=?, ");
            parameters.add(etatAcq);
        }

        // Supprimez la dernière virgule et espace
        queryBuilder.setLength(queryBuilder.length() - 2);
        queryBuilder.append(" WHERE idequipment=?");
        parameters.add(equipId);

        // Préparation de la requête
        String query = queryBuilder.toString();
        try (PreparedStatement preparedStatement = getDbConnection().prepareStatement(query)) {
            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }

            preparedStatement.executeUpdate();
        }
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

    public static ResultSet getPanneAndEquipmentData() throws SQLException, ClassNotFoundException {
        ObservableList<PanneEquipmentData> data = FXCollections.observableArrayList();
        //ObservableList<PanneEquipmentData> etudiants = FXCollections.observableArrayList();


        String query = """
        SELECT 
        equipment.marque, 
        equipment.designation, 
        panne.description, 
        panne.type, 
        panne.statut, 
        panne.rappInterv, 
        user.name  
        FROM projmaintbio.panne 
        LEFT JOIN projmaintbio.equipment 
        ON panne.idequipment = equipment.idequipment 
        LEFT JOIN projmaintbio.intervention 
        ON panne.idpanne = intervention.idpanneI 
        LEFT JOIN projmaintbio.user 
        ON intervention.idintervenant = user.iduser
        """;

        Connection conn = getDbConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();


        return resultSet;
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

    public void addPanne(String panne, String numSerie, String marque, String type,String datePlanif) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement insertPanneStmt = null;
        PreparedStatement selectEquipmentStmt = null;
        PreparedStatement updateEquipmentStmt = null;
        PreparedStatement updatePanneQueryStmt = null;
        PreparedStatement insertInterventionStmt = null;
        ResultSet generatedKeys = null;
        ResultSet equipmentResult = null;

        try {
            conn = getDbConnection();
            conn.setAutoCommit(false); // Enable transaction management

            // Step 1: Insert into the panne table
            String insertPanneQuery = "INSERT INTO panne (description, type, datePlanif) VALUES (?, ?, ?)";
            insertPanneStmt = conn.prepareStatement(insertPanneQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            insertPanneStmt.setString(1, panne);
            insertPanneStmt.setString(2, type);

            if (datePlanif != null && !datePlanif.isEmpty()){
                insertPanneStmt.setString(3, datePlanif);
            } else {
                insertPanneStmt.setNull(3, Types.DATE);
            }


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

            // Step 5: Insert into the intervention table
            String insertInterventionQuery = "INSERT INTO intervention (idpanneI) VALUES (?)";
            insertInterventionStmt = conn.prepareStatement(insertInterventionQuery);
            insertInterventionStmt.setInt(1, idpanne);
            insertInterventionStmt.executeUpdate();

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
            if (insertInterventionStmt !=null) insertInterventionStmt.close();
            if (conn != null) conn.setAutoCommit(true); // Reset auto-commit
        }
    }



    public static ResultSet getPanne() {

        ResultSet resultPanne = null;

        String query = "SELECT * FROM panne WHERE statut = 'non traite'";

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

    public void transIntervention(int intId) throws SQLException, ClassNotFoundException {
        String query = "UPDATE intervention SET transmis = 'oui', transmitted = TRUE WHERE idintervention = ?";
        //System.out.println("Executing Query: " + query + " with ID: " + intId);
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setInt(1, intId);
        preparedStatement.execute();
        preparedStatement.close();

    }

    /*public void validateIntervention(int intId) throws SQLException, ClassNotFoundException {
        String query = "UPDATE intervention SET valide = 'oui' WHERE idintervention = ?";
        //System.out.println("Executing Query: " + query + " with ID: " + intId);
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setInt(1, intId);
        preparedStatement.execute();
        preparedStatement.close();

    }*/

    public void validateInterventionAndUpdatePanne(int intId) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement updateInterventionStmt = null;
        PreparedStatement updatePanneStmt = null;

        try {
            conn = getDbConnection();
            conn.setAutoCommit(false); // Begin transaction

            // Update the intervention table
            String updateInterventionQuery = "UPDATE intervention SET valide = 'oui' WHERE idintervention = ?";
            updateInterventionStmt = conn.prepareStatement(updateInterventionQuery);
            updateInterventionStmt.setInt(1, intId);
            updateInterventionStmt.executeUpdate();

            // Update the panne table
            String updatePanneQuery = """
            UPDATE panne 
            SET statut = 'traite' 
            WHERE idpanne = (SELECT idpanneI FROM intervention WHERE idintervention = ?)
        """;
            updatePanneStmt = conn.prepareStatement(updatePanneQuery);
            updatePanneStmt.setInt(1, intId);
            updatePanneStmt.executeUpdate();

            conn.commit(); // Commit transaction
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback(); // Rollback transaction in case of error
            }
            throw e; // Re-throw exception for higher-level handling
        } finally {
            // Close resources
            if (updateInterventionStmt != null) updateInterventionStmt.close();
            if (updatePanneStmt != null) updatePanneStmt.close();
            if (conn != null) conn.setAutoCommit(true); // Reset auto-commit
        }
    }


    /*public static ResultSet getEquipmentWithPanneNonTraite() {
        ResultSet resultEquipment = null;

        // The SQL query to retrieve the desired data
        String query = "SELECT " +
                "equipment.marque, " +
                "equipment.designation, " +
                "equipment.service, " +
                "panne.description " +
                "FROM projmaintbio.panne " +
                "LEFT JOIN projmaintbio.equipment " +
                "ON panne.idequipment = equipment.idequipment " +
                "WHERE panne.statut = 'non traite'";

        try {
            // Prepare the statement and execute the query
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            resultEquipment = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace(); // Log SQL exceptions
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // Log class not found exceptions
        }

        return resultEquipment;
    }*/

    public static ResultSet getEquipmentWithPanneNonTraite() {
        ResultSet resultEquipment = null;

        String query = "SELECT " +
                "equipment.marque, " +
                "equipment.designation, " +
                "equipment.service, " +
                "panne.description, " +
                "intervention.idintervention, " +
                "intervention.transmitted " +
                "FROM projmaintbio.panne " +
                "LEFT JOIN projmaintbio.equipment " +
                "ON panne.idequipment = equipment.idequipment " +
                "LEFT JOIN projmaintbio.intervention " +
                "ON panne.idpanne = intervention.idpanneI " +
                "WHERE panne.statut = 'non traite'";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            resultEquipment = preparedStatement.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resultEquipment;
    }

    public static ResultSet getEquipmentWithPanneNonTraite2() {
        ResultSet resultEquipment = null;

        String query = "SELECT " +
                "equipment.marque, " +
                "equipment.designation, " +
                "equipment.service, " +
                "panne.description, " +
                "intervention.idintervention, " +
                "intervention.transmitted, " +
                 "panne.datePlanif " +
                "FROM projmaintbio.panne " +
                "LEFT JOIN projmaintbio.equipment " +
                "ON panne.idequipment = equipment.idequipment " +
                "LEFT JOIN projmaintbio.intervention " +
                "ON panne.idpanne = intervention.idpanneI " +
                "WHERE panne.statut = 'non traite'";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            resultEquipment = preparedStatement.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resultEquipment;
    }

    public Map<String, Integer> getPanneCountByService() throws SQLException, ClassNotFoundException {
        String query = """
        SELECT e.service, COUNT(p.idpanne) AS panne_count
        FROM panne p
        INNER JOIN equipment e ON p.idequipment = e.idequipment
        GROUP BY e.service
    """;

        Map<String, Integer> panneCountByService = new HashMap<>();

        try (Connection conn = getDbConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String service = resultSet.getString("service");
                int panneCount = resultSet.getInt("panne_count");
                panneCountByService.put(service, panneCount);
            }
        }

        return panneCountByService;
    }

    public Map<String, Integer> getInterventionCountByService() throws SQLException, ClassNotFoundException {
        String query = """
        SELECT e.service, COUNT(i.idintervention) AS intervention_count
        FROM intervention i
        INNER JOIN panne p ON i.idpanneI = p.idpanne
        INNER JOIN equipment e ON p.idequipment = e.idequipment 
        WHERE valide = 'oui' 
        GROUP BY e.service
    """;

        Map<String, Integer> interventionCountByService = new HashMap<>();

        try (Connection conn = DBHandler.getDbConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String service = resultSet.getString("service");
                int interventionCount = resultSet.getInt("intervention_count");
                interventionCountByService.put(service, interventionCount);
            }
        }

        return interventionCountByService;
    }

    public void validateInterventionAndUpdatePanneWithComment(int intId, String comment, int usrID) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement updateInterventionStmt = null;
        PreparedStatement updatePanneStmt = null;

        try {
            conn = getDbConnection();
            conn.setAutoCommit(false); // Begin transaction

            // Update the intervention table
            String updateInterventionQuery = "UPDATE intervention SET valide = 'oui', idintervenant = ? WHERE idintervention = ?";
            updateInterventionStmt = conn.prepareStatement(updateInterventionQuery);
            updateInterventionStmt.setInt(1, usrID);
            updateInterventionStmt.setInt(2, intId);
            updateInterventionStmt.executeUpdate();

            // Update the panne table with the status and comment
            String updatePanneQuery = """
            UPDATE panne 
            SET statut = 'traite', rappInterv = ? 
            WHERE idpanne = (SELECT idpanneI FROM intervention WHERE idintervention = ?)
        """;
            updatePanneStmt = conn.prepareStatement(updatePanneQuery);
            updatePanneStmt.setString(1, comment);
            updatePanneStmt.setInt(2, intId);
            updatePanneStmt.executeUpdate();

            conn.commit(); // Commit transaction
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback(); // Rollback transaction in case of error
            }
            throw e; // Re-throw exception for higher-level handling
        } finally {
            // Close resources
            if (updateInterventionStmt != null) updateInterventionStmt.close();
            if (updatePanneStmt != null) updatePanneStmt.close();
            if (conn != null) conn.setAutoCommit(true); // Reset auto-commit
        }
    }

    public static ResultSet getUserAccreditation(Utilisateur user) {
        String query = "SELECT * FROM user WHERE name = ? AND password = ?";
        try {
            Connection conn = getDbConnection();
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, user.getNom());
            statement.setString(2, user.getCode());
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getUserProfile(String username, String password) {
        String query = "SELECT profile FROM user WHERE name = ? AND password = ?";
        try {
            Connection conn = getDbConnection();
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("profile");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null; // Return null if no profile is found or an error occurs
    }

    public static void updateCredit(String pwd, String usrname, String prof) throws SQLException, ClassNotFoundException {
        String query = "UPDATE user SET password=? WHERE name=? AND profile=?";


        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setString(1, pwd);
        preparedStatement.setString(2, usrname);
        preparedStatement.setString(3, prof);
        // preparedStatement.setInt(4, userId);
        //preparedStatement.setInt(4, taskId);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public static ResultSet getEquipmentWithPanneNonTraite1() {
        ResultSet resultEquipment = null;

        String query = "SELECT " +
                "equipment.marque, " +
                "equipment.designation, " +
                "equipment.service, " +
                "panne.description, " +
                "intervention.idintervention, " +
                "intervention.transmitted, " +
                "panne.datePlanif " +
                "FROM projmaintbio.panne " +
                "LEFT JOIN projmaintbio.equipment " +
                "ON panne.idequipment = equipment.idequipment " +
                "LEFT JOIN projmaintbio.intervention " +
                "ON panne.idpanne = intervention.idpanneI " +
                "WHERE panne.statut = 'non traite' AND intervention.transmis = 'oui'";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            resultEquipment = preparedStatement.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resultEquipment;
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
