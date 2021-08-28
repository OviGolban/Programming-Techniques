package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import model.Client;


/**
 * clasa Client for data access object(DAO pattern)
 */
public class ClientDAO {

    protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO client (idClient,nume,email,varsta)"
            + " VALUES (?,?,?,?)";
    private static final String updateStatementString = "UPDATE client SET nume = ?,email = ?, varsta = ? where idClient=?";
    private static final String deleteStatementString = "DELETE from client where idClient = ?";
    private static final String viewStatementString = "SELECT * FROM schooldb.client";
    private final static String findStatementString = "SELECT * FROM client where idClient = ?";

    /**
     * @param clientId id-ul clientului pe care il cautam
     * @return returneaza clientul daca a fost gasit
     */
    public static Client findById(int clientId) {
        Client toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, clientId);
            rs = findStatement.executeQuery();
            rs.next();

            String nume = rs.getString("nume");
            String email = rs.getString("email");
            int varsta = rs.getInt("varsta");
            toReturn = new Client(clientId, nume, email, varsta);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "StudentDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }

    /**
     * @param client clientul pe care vrem sa il inseram
     * @return returneaza id-ul clientului care a fost inserat, daca s-a facut su succes
     */
    public static int insert(Client client) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, client.getIdClient());
            insertStatement.setString(2, client.getNume());
            insertStatement.setString(3, client.getEmail());
            insertStatement.setInt(4, client.getVarsta());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    /**
     * @param client clientul care va fi editat
     */
    public static void update(Client client) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;

        int updateId = -1;
        try {
            updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setString(1, client.getNume());
            updateStatement.setString(2, client.getEmail());
            updateStatement.setInt(3, client.getVarsta());
            updateStatement.setInt(4, client.getIdClient());
            updateStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    /**
     * @param client clientul care va fi sters
     */
    public static void delete(Client client) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement deleteStatement = null;

        try {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setInt(1, client.getIdClient());
            deleteStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    /**
     * se va afisa tabelul cu toti clientii din baza de date
     */
    public static ArrayList<Object> view() {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement viewStatement = null;
        ResultSet rs = null;
        ArrayList<Object> listaClienti = new ArrayList<>();
        try {
            viewStatement = dbConnection.prepareStatement(viewStatementString);
            rs = viewStatement.executeQuery();
            while(rs.next()) {
                int viewId = rs.getInt("idClient");
                String viewNume = rs.getString("nume");
                String viewEmail = rs.getString("email");
                int viewVarsta = rs.getInt("varsta");
                Client client = new Client(viewId, viewNume, viewEmail, viewVarsta);
                listaClienti.add(client);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:view " + e.getMessage());
        } finally {
            ConnectionFactory.close(viewStatement);
            ConnectionFactory.close(dbConnection);
        }
        return listaClienti;
    }
}

