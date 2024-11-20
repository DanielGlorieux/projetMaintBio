package databaseHelper;
import java.sql.*;

public class DBHandler extends Configuration{
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://"+dbHost + ":"
                + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString,dbUser,dbPassword);
        return dbConnection;
    }

    // Methode d'écriture dans la base
    // Ajout de livre
    public void addUser(String nom, String mdp, String profile){

        if ( !nom.equals("") && !mdp.equals("")
                && !profile.equals("") ){

            String insert = "INSERT INTO " + Constantes.USER_TABLE + "("
                    + Constantes.USER_NAME + ","
                    + Constantes.USER_PASSWORD + "," + Constantes.USER_PROFILE + ") " + "VALUES(?,?,?)";

            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
                preparedStatement.setString(1,nom);
                preparedStatement.setString(2,mdp);
                preparedStatement.setString(3,profile);


                preparedStatement.executeUpdate();


            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }

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
