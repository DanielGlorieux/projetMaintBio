package databaseHelper;
import com.example.projetmaintbionew.Equipement;
import com.example.projetmaintbionew.Utilisateur;

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
