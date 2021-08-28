package presentation;

import connection.ConnectionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * clasa de View pentru aplicatia mea
 */
public class View extends JFrame {

    protected static final Logger LOGGER = Logger.getLogger(View.class.getName());
    JTextField clientID;
    JTextField nume;
    JTextField email;
    JTextField varsta;

    JTextField idProdus;
    JTextField numeProdus;
    JTextField cantProdus;

    JTextField cantitateProdus;

    JTextField idOrder;

    JComboBox cbClienti;
    JComboBox cbProduse;


    JButton submit = new JButton("Submit");
    JButton update = new JButton("Update");
    JButton delete = new JButton("Delete");
    JButton view = new JButton("View");

    JButton addProduct = new JButton("Add");
    JButton editProduct = new JButton("Edit");
    JButton viewProduct = new JButton("View");
    JButton deleteProduct = new JButton("Delete");

    JButton comanda = new JButton("Comanda");
    public View() {
        JFrame frame = new JFrame("Meniu");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(300, 300);


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 1));

        this.clientID = new JTextField("ClientId");
        this.nume = new JTextField("nume");
        this.email = new JTextField("email");
        this.varsta = new JTextField("varsta");

        this.idProdus = new JTextField("id Produs");
        this.numeProdus = new JTextField("nume produs");
        this.cantProdus = new JTextField("adaugati cantitatea produsului:");

        this.cantitateProdus = new JTextField("cantitate produs");

        this.idOrder= new JTextField("Id Order");


        panel.add(clientID);
        panel.add(nume);
        panel.add(email);
        panel.add(varsta);
        panel.add(submit);
        panel.add(update);
        panel.add(delete);
        panel.add(view);



        frame.setContentPane(panel);
        frame.setVisible(true);


        JFrame frame1 = new JFrame("Adaugare Produse");
        frame1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame1.setSize(300, 300);
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(10, 1));

        panel1.add(idProdus);
        panel1.add(numeProdus);
        panel1.add(cantProdus);
        panel1.add(addProduct);
        panel1.add(editProduct);
        panel1.add(viewProduct);
        panel1.add(deleteProduct);

        frame1.setContentPane(panel1);
        frame1.setVisible(true);

        JFrame frame2 = new JFrame("Order");
        frame2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame2.setSize(300,300);
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(10,1));

        ArrayList<Integer> clientiId = new ArrayList<Integer>();
        final String findClientIdStatement = "SELECT idClient FROM client";
        final String findProductIdStatement ="SELECT id FROM product";
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatment = null;
        ResultSet rs = null;
        try {
            findStatment = dbConnection.prepareStatement(findClientIdStatement);
            rs = findStatment.executeQuery();
            while(rs.next()) {
                int viewId = rs.getInt("idClient");
                clientiId.add((Integer)viewId);



            }

        } catch (SQLException exp) {
            LOGGER.log(Level.WARNING, "Client comboBox " + exp.getMessage());
        } finally {
            ConnectionFactory.close(findStatment);
            //ConnectionFactory.close(dbConnection);
        }

        ArrayList<Integer> produseID = new ArrayList<>();
        PreparedStatement productStatment = null;
        ResultSet result = null;
        try{
            productStatment=dbConnection.prepareStatement(findProductIdStatement);
            result = productStatment.executeQuery();
            while(result.next()) {
                int viewId=result.getInt("id");
                produseID.add((Integer)viewId);
            }
        }catch(SQLException excp) {
            LOGGER.log(Level.WARNING, "Product comboBox " + excp.getMessage());
        } finally {
            ConnectionFactory.close(productStatment);
            ConnectionFactory.close(dbConnection);

        }

        this.cbClienti = new JComboBox(clientiId.toArray());
        this.cbProduse = new JComboBox(produseID.toArray());

        panel2.add(idOrder);
        panel2.add(cbClienti);
        panel2.add(cbProduse);
        panel2.add(cantitateProdus);
        panel2.add(comanda);

        frame2.setContentPane(panel2);
        frame2.setVisible(true);


    }

    public JTextField getClientID() {
        return clientID;
    }

    public void setClientID(JTextField clientID) {
        this.clientID = clientID;
    }

    public JTextField getNume() {
        return nume;
    }

    public void setNume(JTextField nume) {
        this.nume = nume;
    }

    public JTextField getEmail() {
        return email;
    }

    public void setEmail(JTextField email) {
        this.email = email;
    }

    public JTextField getVarsta() {
        return varsta;
    }

    public void setVarsta(JTextField varsta) {
        this.varsta = varsta;
    }

    public JTextField getIdProdus() {
        return idProdus;
    }

    public void setIdProdus(JTextField idProdus) {
        this.idProdus = idProdus;
    }

    public JTextField getNumeProdus() {
        return numeProdus;
    }

    public void setNumeProdus(JTextField numeProdus) {
        this.numeProdus = numeProdus;
    }

    public JTextField getCantitateProdus() {
        return cantitateProdus;
    }

    public void setCantitateProdus(JTextField cantitateProdus) {
        this.cantitateProdus = cantitateProdus;
    }

    public JTextField getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(JTextField idOrder) {
        this.idOrder = idOrder;
    }

    public JTextField getCantProdus() {
        return cantProdus;
    }

    public void setCantProdus(JTextField cantProdus) {
        this.cantProdus = cantProdus;
    }

    void addSubmitListener(ActionListener mal) {
        submit.addActionListener(mal);
    }
    void addUpdateListener(ActionListener mal) {
        update.addActionListener(mal);
    }
    void addDeleteListener(ActionListener mal) {
        delete.addActionListener(mal);
    }
    void addViewListener(ActionListener mal) {
        view.addActionListener(mal);
    }
    void addProductListener(ActionListener mal) { addProduct.addActionListener(mal);}
    void editProductListener(ActionListener mal) { editProduct.addActionListener(mal);}
    void viewProductListener(ActionListener mal) {viewProduct.addActionListener(mal);}
    void deleteProductListener(ActionListener mal) { deleteProduct.addActionListener(mal);}
    void comadaListener(ActionListener mal) {comanda.addActionListener(mal);}

}
