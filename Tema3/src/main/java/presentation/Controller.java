package presentation;

import bll.ClientBLL;
import bll.validators.OrderBLL;
import bll.validators.ProductBLL;
import connection.ConnectionFactory;
import dao.ClientDAO;
import dao.ProductDAO;
import model.Client;
import model.Order;
import model.Product;
import start.ReflectionExample;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Controller {
    private View view;
    protected static final Logger LOGGER = Logger.getLogger(Controller.class.getName());
    FileWriter factura;

    public Controller(View view) {
        this.view = view;
        view.addSubmitListener(new SubmitListener());
        view.addUpdateListener(new UpdateListener());
        view.addDeleteListener(new DeleteListener());
        view.addViewListener(new ViewListener());
        view.addProductListener(new addProduct());
        view.editProductListener(new editProduct());
        view.deleteProductListener(new deleteProduct());
        view.viewProductListener(new viewProduct());
        view.comadaListener(new comandaProdus());
    }

    /**
     * listener pentru adaugarea unui client
     */
    class SubmitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int clientID = Integer.parseInt(view.getClientID().getText());
            String nume = view.getNume().getText();
            String email = view.getEmail().getText();
            int varsta = Integer.parseInt(view.getVarsta().getText());
            Client client = new Client(clientID, nume, email, varsta);

            ClientBLL clientBll = new ClientBLL();
            int id = clientBll.insertClient(client);
            if (id > 0) {
                clientBll.findClientById(id);
            }


            // Generate error
            try {
                clientBll.findClientById(0);
            } catch (Exception ex) {
                LOGGER.log(Level.INFO, ex.getMessage());
            }
            ReflectionExample.retrieveProperties(client);

        }
    }

    /**
     * listener pentru editarea unui client
     */
    class UpdateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int clientID = Integer.parseInt(view.getClientID().getText());
            String nume = view.getNume().getText();
            String email = view.getEmail().getText();
            int varsta = Integer.parseInt(view.getVarsta().getText());
            Client client = new Client(clientID, nume, email, varsta);

            ClientBLL clientBll = new ClientBLL();
            clientBll.updateClient(client);

            ReflectionExample.retrieveProperties(client);

        }
    }

    /**
     * listener pentru stergerea unui client
     */
    class DeleteListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int clientID = Integer.parseInt(view.getClientID().getText());
            Client client = new Client(clientID);

            ClientBLL clientBll = new ClientBLL();
            clientBll.deleteClient(client);

            ReflectionExample.retrieveProperties(client);

        }
    }

    /**
     * listener pentru afisarea unui JTable cu toti clientii
     */
    class ViewListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           /* JFrame tableFrame = new JFrame("Tabel");
            String[] columns = new String[] {"idClient","nume","email","varsta"};


            final String viewStatementString = "SELECT * FROM client";
            Object[][] data = new Object[][] {};*/

            DefaultTableModel model = new DefaultTableModel();

            model.addColumn("idClient");
            model.addColumn("nume");
            model.addColumn("email");
            model.addColumn("varsta");

            ClientBLL client = new ClientBLL();

            ReflectionExample.creareTabel(client.view());

            /*Connection dbConnection = ConnectionFactory.getConnection();
            PreparedStatement viewStatement = null;
            ResultSet rs = null;
            try {
                viewStatement = dbConnection.prepareStatement(viewStatementString);
                rs = viewStatement.executeQuery();
                while(rs.next()) {
                    int viewId = rs.getInt("idClient");
                    String viewNume = rs.getString("nume");
                    String viewEmail = rs.getString("email");
                    int viewVarsta = rs.getInt("varsta");
                    model.addRow(new Object[] {viewId, viewNume, viewEmail, viewVarsta});
                }

            } catch (SQLException exp) {
                LOGGER.log(Level.WARNING, "ClientDAO:view " + exp.getMessage());
            } finally {
                ConnectionFactory.close(viewStatement);
                ConnectionFactory.close(dbConnection);
            }

            JTable table = new JTable(model);


            tableFrame.add(table);
            tableFrame.pack();
            tableFrame.setVisible(true);*/


        }

    }

    /**
     * listener pentru adaugarea unui produs
     */
    class addProduct implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int productID = Integer.parseInt(view.getIdProdus().getText());
            String nume = view.getNumeProdus().getText();
            int cantitate = Integer.parseInt(view.getCantProdus().getText());
            Product produs = new Product(productID, nume, cantitate);

            ProductBLL produsBll = new ProductBLL();
            int id = produsBll.insertProdus(produs);
            if (id > 0) {
                produsBll.findProductById(id);
            }


            // Generate error
            try {
                produsBll.findProductById(1);
            } catch (Exception ex) {
                LOGGER.log(Level.INFO, ex.getMessage());
            }
            ReflectionExample.retrieveProperties(produs);

         }
        }

    /**
     * listener pentru editarea unui produs
     */
        class editProduct implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(view.getIdProdus().getText());
                String nume = view.getNumeProdus().getText();
                int cantitate = Integer.parseInt(view.getCantProdus().getText());
                Product produs = new Product(id, nume, cantitate);

                ProductBLL productBll = new ProductBLL();
                productBll.updateProdus(produs);

                ReflectionExample.retrieveProperties(produs);

            }
        }

    /**
     * listener pentru stergerea unui produs
     */
        class deleteProduct implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(view.getIdProdus().getText());
                Product produs = new Product(id);

                ProductBLL produsBll = new ProductBLL();
                produsBll.deleteProdus(produs);

                ReflectionExample.retrieveProperties(produs);

            }
        }

    /**
     * listener pentru afisarea tuturor produselor intr-un JTable
     */
        class viewProduct implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
              /*  JFrame tableFrame = new JFrame("Tabel");
                String[] columns = new String[] {"id","nume","cantitate"};*/

                ProductBLL product = new ProductBLL();



               /* final String viewStatementString = "SELECT * FROM product";
                Object[][] data = new Object[][] {};*/

                DefaultTableModel model = new DefaultTableModel();

                model.addColumn("id");
                model.addColumn("nume");
                model.addColumn("cantitate");

                ReflectionExample.creareTabel(product.view());
/*
                JTable table = new JTable(model);


                tableFrame.add(table);
                tableFrame.pack();
                tableFrame.setVisible(true);*/

            }
        }

    /**
     * metoda pt crearea unei facturi sub forma de fisier text
     */
        void creeazaFisier() {
            try {
                factura = new FileWriter("Factura.txt");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

    /**
     * @param s String-ul care va fi scris in fisier
     */
        void scrieInFisier(String s) {
            try {
                this.factura.write(s + "\n");
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    /**
     * metoda pentru inchiderea fisierului
     */
        void salveazaFisier() {
            try {
                factura.close();
            } catch (Exception e) {
                System.out.println("Eroare la salvare fisier: " + e);
            }

        }

    /**
     * listener pentru crearea unei comenzi
     */
        class comandaProdus implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                int idProdus = Integer.parseInt(view.cbProduse.getSelectedItem().toString());
                System.out.println(idProdus);
                int cantitate = Integer.parseInt(view.getCantitateProdus().getText());

                ProductBLL productBll = new ProductBLL();
                OrderBLL orderBll = new OrderBLL();
                Product produs =productBll.findProductById(idProdus);
                if(cantitate>produs.getCantitate()) {
                    view.getCantitateProdus().setText("Cantitate prea mare");
                }else {
                    Product produsNou = new Product(idProdus, produs.getNume(), produs.getCantitate()-cantitate);
                    productBll.updateProdus(produsNou);

                int idOrder = Integer.parseInt(view.getIdOrder().getText());
                int idClient = Integer.parseInt(view.cbClienti.getSelectedItem().toString());
                int nouaCantitate = cantitate;
                orderBll.insertOrder(new Order(idOrder,idClient,idProdus,nouaCantitate));

                creeazaFisier();
                scrieInFisier("--------------FACTURA--------------");
                scrieInFisier("Clientul cu id-ul: "+ idClient +" a efectuat o comanda");
                scrieInFisier("Numarul comenzii: "+idOrder);
                scrieInFisier("S-a cumparat produsul: "+ produs.getNume() +" in cantitate de: "+nouaCantitate);
                salveazaFisier();




                }


            }
        }
    }

