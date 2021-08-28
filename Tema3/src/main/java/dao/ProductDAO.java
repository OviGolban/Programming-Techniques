package dao;

import connection.ConnectionFactory;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * clasa Product for data acces object(DAO pattern)
 */
public class ProductDAO {
    protected static final Logger LOGGER = Logger.getLogger(ProductDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO product (id,nume,cantitate)"
            + " VALUES (?,?,?)";
    private static final String updateStatementString = "UPDATE product SET nume = ?,cantitate = ? where id=?";
    private static final String deleteStatementString = "DELETE from product where id = ?";
    private static final String viewStatementString = "SELECT * FROM schooldb.product";
    private final static String findStatementString = "SELECT * FROM product where id = ?";

    /**
     * @param id id-ul produsului pe care il cautam
     * @return returneaza produsul daca a fost gasit
     */
    public static Product findById(int id) {
        Product toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, id);
            rs = findStatement.executeQuery();
            rs.next();

            String nume = rs.getString("nume");
            int cantitate = rs.getInt("cantitate");
            toReturn = new Product(id, nume, cantitate);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }

    /**
     * @param produs produsul care va fi inserat in tabelul de produse
     * @return
     */
    public static int insert(Product produs) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, produs.getId());
            insertStatement.setString(2, produs.getNume());
            insertStatement.setInt(3, produs.getCantitate());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    /**
     * @param produs produsul care va fi editat
     */
    public static void update(Product produs) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;

        int updateId = -1;
        try {
            updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setString(1, produs.getNume());
            updateStatement.setInt(2, produs.getCantitate());
            updateStatement.setInt(3, produs.getId());
            updateStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    /**
     * @param produs produsul care va fi sters
     */
    public static void delete(Product produs) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement deleteStatement = null;

        try {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setInt(1, produs.getId());
            deleteStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    /**
     * va afisa tabelul cu toate produsele
     */
    public static ArrayList<Object> view() {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement viewStatement = null;
        ResultSet rs = null;
        ArrayList<Object> listaProduse = new ArrayList<>();
        try {
            viewStatement = dbConnection.prepareStatement(viewStatementString);
            rs = viewStatement.executeQuery();
            while(rs.next()) {
                int viewId = rs.getInt("id");
                String viewNume = rs.getString("nume");
                int viewVarsta = rs.getInt("cantitate");
                Product  produs= new Product(viewId, viewNume, viewVarsta);
                listaProduse.add(produs);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:view " + e.getMessage());
        } finally {
            ConnectionFactory.close(viewStatement);
            ConnectionFactory.close(dbConnection);
        }
        return listaProduse;
    }

}
