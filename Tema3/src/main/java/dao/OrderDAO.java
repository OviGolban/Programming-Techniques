package dao;

import connection.ConnectionFactory;
import model.Order;
import model.Product;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clasa Order for data acces object(DAO pattern)
 */
public class OrderDAO {
    protected static final Logger LOGGER = Logger.getLogger(OrderDAO.class.getName());
    private final static String findStatementString = "SELECT * FROM order where idOrder = ?";
    private static final String insertStatementString = "INSERT INTO schooldb.order (idOrder,idClient,idProdus,cantitate)"
            + " VALUES (?,?,?,?)";

    /**
     * @param idOrder id-ul comenzii de cautat
     * @return returneaza un obiect de tip comanda daca a gasit id-ul corespunzator
     */
    public static Order findById(int idOrder) {
        Order toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, idOrder);
            rs = findStatement.executeQuery();
            rs.next();

            int idClient = rs.getInt("idClient");
            int idProdus = rs.getInt("idProdus");
            int cantitate = rs.getInt("cantitate");
            toReturn = new Order(idOrder, idClient, idProdus, cantitate);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }

    /**
     * @param order comanda pe care vrem sa o inseram
     * @return returneaza id-ul comenzii inserate daca s-a efectuat cu succes
     */
    public static int insert(Order order) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, order.getIdOrder());
            insertStatement.setInt(2, order.getIdClient());
            insertStatement.setInt(3, order.getIdProdus());
            insertStatement.setInt(4, order.getCantitate());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

}
