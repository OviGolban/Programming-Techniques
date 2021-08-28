package PresentationLayer;

import BussinesLayer.*;
import DataLayer.Serializator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Controller {
    private Administrator admin;
    private Employee employee;
    private User user;
    private Client client;

    ArrayList<BaseProduct> produseBaza = BaseProduct.readFile();
    ArrayList<CompositeProduct> produseCompuse = new ArrayList<>();
    ArrayList<MenuItem> produseMeniu = new ArrayList<>();
    Map<Order, ArrayList<MenuItem>> mapare=new Hashtable<>();
    DeliveryService service = new DeliveryService(produseBaza, produseCompuse, produseMeniu, mapare);



    ArrayList<UserBL> listaDeVerificat = new ArrayList<>();

    JComboBox box1;
    JComboBox box2;
    JComboBox box3;


    public Controller(Administrator admin, Employee employee, Client client) {
        this.admin = admin;
        this.employee = employee;

    }

    public Controller(User user) {
        this.user = user;
        user.addSubmitListener(new SubmitListener());
        user.addUserListener(new AddUserListener());

    }

    public Controller(Administrator admin) {
        this.admin = admin;
        admin.addAddListener(new AddListener());
        admin.addImportListener(new ImportListener());
        admin.addDeleteListener(new DeleteListener());
        admin.editProductListener(new editProductListener());
        admin.viewListener(new viewProduse());
        admin.addCompositeProductListener(new addCompositeProduct());
        admin.generate1Listener(new generate1Report());
        admin.generate2Listener(new generate2Report());
        admin.generate3Listener(new generate3Report());
        admin.generate4Listener(new generate4Report());
    }

    public Controller(Client client) {
        this.client = client;
        client.viewListener(new viewListener());
        client.searchListener(new searchListener());
        client.orderListener(new orderListener());
    }

    class SubmitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = user.getUsername().getText();
            String password = user.getPassword().getText();
            String functii = user.getFunctii().getSelectedItem().toString();
            UserBL utilizator = new UserBL(username, password, functii);
            try {
                service.salvareInformatii();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

       /*     FileInputStream fileIn = null;
            try {
                fileIn = new FileInputStream("B:\\InteliJI_Idea\\Tema4\\src\\users.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                listaDeVerificat = (ArrayList<UserBL>) in.readObject();
                System.out.println(listaDeVerificat.toString());
                //listaUtilizatori =  in.readObject();
                //System.out.println(in.readObject());

                //System.out.println(in.readObject());
                //UserBL user2 =(UserBL) in.readObject();
                //System.out.println(user1);
                //System.out.println(user2);

                in.close();
                fileIn.close();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            } finally {
                if (fileIn != null) {
                    try {
                        fileIn.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }*/
            listaDeVerificat = DeliveryService.incarcareUtilizatori();
            boolean flag = false;
            for (UserBL user : listaDeVerificat) {
                if (user.getUsername().equals(utilizator.getUsername()) && user.getPassword().equals(utilizator.getPassword()) && user.getFunctie().equals(utilizator.getFunctie())) {
                    flag = true;
                    if (user.getFunctie().equals("Administrator")) {
                        admin = new Administrator();
                        Controller controller1 = new Controller(admin);
                    } else if (user.getFunctie().equals("Client")) {
                        Client client = new Client();
                        Controller controller2 = new Controller(client);
                    } else {
                        Employee employee = new Employee();
                    }
                }
            }
            System.out.println(listaDeVerificat.contains(user));
            if (flag == true) {
                System.out.println("Succes");
            } else {
                System.out.println("Utilizatorul NU Exista");
            }
        }

    }

    class DeleteListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String nume = admin.getTitlu().getText();
            service.deleteProduct(nume);


        }
    }

    class AddListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String nume = admin.getTitlu().getText();
            Float rating = Float.parseFloat(admin.getRating().getText());
            int calories = Integer.parseInt(admin.getCalories().getText());
            int protein = Integer.parseInt(admin.getProtein().getText());
            int fat = Integer.parseInt(admin.getFat().getText());
            int sodium = Integer.parseInt(admin.getSodium().getText());
            int price = Integer.parseInt(admin.getPrice().getText());

            BaseProduct deAdaugat = new BaseProduct(nume, rating, calories, protein, fat, sodium, price);
            service.addProduct(deAdaugat);


        }
    }

    class editProductListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String nume = admin.getTitlu().getText();
            float rating = Float.parseFloat(admin.getRating().getText());
            int calories = Integer.parseInt(admin.getCalories().getText());
            int protein = Integer.parseInt(admin.getProtein().getText());
            int fat = Integer.parseInt(admin.getFat().getText());
            int sodium = Integer.parseInt(admin.getSodium().getText());
            int price = Integer.parseInt(admin.getPrice().getText());

            BaseProduct deEditat = new BaseProduct(nume, rating, calories, protein, fat, sodium, price);
            service.modifyProduct(deEditat, nume);


        }
    }

    class ImportListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            service.importProducts();

        }
    }

    class AddUserListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = user.getUsername().getText();
            String password = user.getPassword().getText();
            String functii = user.getFunctii().getSelectedItem().toString();
            UserBL utilizator = new UserBL(username, password, functii);
            listaDeVerificat.add(utilizator);

            try {
                Serializator serializator = new Serializator(listaDeVerificat);
                System.out.println("Serialized data is saved!");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        }
    }

    class viewProduse implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            List<BaseProduct> produse = BaseProduct.readFile();
            box1 = new JComboBox(produse.toArray());
            box2 = new JComboBox(produse.toArray());
            box3 = new JComboBox(produse.toArray());

            admin.adaugareComboBox(box1);
            admin.adaugareComboBox(box2);
            admin.adaugareComboBox(box3);
        }
    }

    class addCompositeProduct implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String titluMeniu = admin.getTitlu().getText();
            BaseProduct bp1 = (BaseProduct) box1.getSelectedItem();
            BaseProduct bp2 = (BaseProduct) box2.getSelectedItem();
            BaseProduct bp3 = (BaseProduct) box3.getSelectedItem();
            List<BaseProduct> prodCompuse = new ArrayList<>();
            prodCompuse.add(bp1);
            prodCompuse.add(bp2);
            prodCompuse.add(bp3);

            CompositeProduct cp1 = new CompositeProduct(titluMeniu, prodCompuse);
            service.addCompositeProduct(cp1);


        }
    }

    class generate1Report implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<Order> listaAuxiliara = new ArrayList<>();
            listaAuxiliara = service.deserializare();
            //System.out.println(listaAuxiliara.toString());

            int startHour = Integer.parseInt(admin.getStartHour().getText());
            int finisHour = Integer.parseInt(admin.getFinishHour().getText());

            List<Order> listaCeruta = listaAuxiliara
                    .stream()
                    .filter(order -> order.getOrderDate().getHour() > startHour && order.getOrderDate().getHour() < finisHour)
                    .collect(Collectors.toList());

            System.out.println(listaCeruta.toArray());
            for (int i = 0; i < listaCeruta.size(); i++) {
                admin.adaugaLaAfisaj(listaCeruta.get(i).toString());
            }

        }
    }

    class generate2Report implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            String numeProdus = admin.getTitlu().getText();
            int nrTimes = Integer.parseInt(admin.getNrOrder().getText());
            ArrayList<Order> listaComanda = new ArrayList<>();
            listaComanda = service.deserializare();
            Map<MenuItem, Integer> collect = new HashMap<>();
            for (Order order : listaComanda) {
                for (int i = 0; i < order.getMeniuItem().size(); i++) {
                    if (collect.containsKey(order.getMeniuItem().get(i))) {

                        collect.put(order.getMeniuItem().get(i), collect.get(order.getMeniuItem().get(i)) + 1);

                    } else {
                        collect.put(order.getMeniuItem().get(i), 1);
                    }
                }

            }
            for (Map.Entry<MenuItem, Integer> entry : collect.entrySet()) {
                System.out.println(entry.getKey() + "" + entry.getValue());
            }

            List<MenuItem> listaProduse = collect.entrySet().stream()
                    .filter(c -> c.getValue() >= nrTimes)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

            admin.adaugaLaAfisaj(listaProduse.toString());
        }
    }

    class generate3Report implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int value = Integer.parseInt(admin.getValue().getText());
            int nrTimes = Integer.parseInt(admin.getNrOrder().getText());

            ArrayList<Order> listaComanda = new ArrayList<>();
            listaComanda = service.deserializare();

            Map<Integer, Integer> collect = new HashMap<>();
            for (Order order : listaComanda) {
                if (collect.containsKey(order.getClientID())) {
                    collect.put(order.getClientID(), collect.get(order.getClientID()) + 1);
                } else {
                    collect.put(order.getClientID(), 1);
                }

            }
            for (Map.Entry<Integer, Integer> entry : collect.entrySet()) {
                System.out.println(entry.getKey() + "  " + entry.getValue());
            }

            List<Integer> listaClienti = collect.entrySet().stream()
                    .filter(c -> c.getValue() >= nrTimes)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());


            boolean flag = true;

            for (int i = 0; i < listaClienti.size(); i++) {
                flag = true;
                for (Order order : listaComanda) {
                    if (order.getClientID() == listaClienti.get(i) && order.computePrice() > value) {
                        flag = true;
                    } else {
                        flag = false;
                    }
                }
                if (flag == true) {
                    admin.adaugaLaAfisaj("Clientul cu id-ul " + listaClienti.get(i) + "indeplineste conditiile");
                }
            }
        }


    }

    class generate4Report implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int ziua = Integer.parseInt(admin.getZiua().getText());
            Map<ArrayList<MenuItem>, Integer> collect = new HashMap<>();

            ArrayList<Order> listaComanda = new ArrayList<>();
            listaComanda = service.deserializare();
            for (Order order : listaComanda) {
                if (order.getOrderDate().getDayOfMonth() == ziua) {
                    if (collect.containsKey(order.getMeniuItem())) {
                        collect.put(order.getMeniuItem(), collect.get(order.getMeniuItem()) + 1);
                    } else {
                        collect.put(order.getMeniuItem(), 1);
                    }
                }

            }
            for (Map.Entry<ArrayList<MenuItem>, Integer> entry : collect.entrySet()) {
                System.out.println("Produsul " + entry.getKey().toString() + " a fost cumparat de:" + "" + entry.getValue());
                String s = new String("Produsul " + entry.getKey().toString() + " a fost cumparat de:" + "" + entry.getValue());
                admin.adaugaLaAfisaj(s);
            }

            List<ArrayList<MenuItem>> listaProduse = collect.entrySet().stream()
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

            //admin.adaugaLaAfisaj(listaProduse.toString());

        }
    }

    class viewListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            client.adaugaLaAfisaj("Produsele de Baza se afiseaza in consola");
            System.out.println("Produse de baza:");
            //client.adaugaLaAfisaj(produseBaza.toString());
            for (int i = 0; i < produseBaza.size(); i++) {
                System.out.println(produseBaza.get(i).toString());

            }
            client.adaugaLaAfisaj("Produsele Compuse se afiseaza in consola");

            List<CompositeProduct> listaAuxiliara = new ArrayList<>();
            FileInputStream fileIn = null;
            try {
                fileIn = new FileInputStream("B:\\InteliJI_Idea\\Tema4\\src\\composite.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                listaAuxiliara = (ArrayList<CompositeProduct>) in.readObject();

                in.close();
                fileIn.close();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            } finally {
                if (fileIn != null) {
                    try {
                        fileIn.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }

            System.out.println("Produse compuse:");
            //client.adaugaLaAfisaj(produseCompuse.toString());
            for (int i = 0; i < listaAuxiliara.size(); i++) {
                System.out.println(listaAuxiliara.get(i).toString());

            }
        }
    }

    class searchListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String nume = client.getTitlu().getText();
            if (nume.equals("Title")) {
                float rating = Float.parseFloat(client.getRating().getText());
                int calories = Integer.parseInt(client.getCalories().getText());
                int protein = Integer.parseInt(client.getProtein().getText());
                List<MenuItem> listaCeruta = service.searchProduct1(rating, calories, protein);

                client.adaugaLaAfisaj(listaCeruta.toString());

            } else {
                List<MenuItem> listaCeruta2 = service.searchProduct2(nume);
                client.adaugaLaAfisaj(listaCeruta2.toString());
            }


        }
    }

    class orderListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<MenuItem> produseNoi = new ArrayList<>();
            int suma = 0;
            //String titlu = client.getTitlu().getText();
            int idComanda = Integer.parseInt(client.getIdComanda().getText());
            int idClient = Integer.parseInt(client.getIdClient().getText());
            MenuItem bp1 = (MenuItem) client.getBox1().getSelectedItem();
            MenuItem bp2 = (MenuItem) client.getBox2().getSelectedItem();
            MenuItem bp3 = (MenuItem) client.getBox3().getSelectedItem();
            produseNoi.add(bp1);
            produseNoi.add(bp2);
            produseNoi.add(bp3);
            service.order(idComanda, idClient, produseNoi);
            employee = new Employee();
            service.addObserver(employee);
            Order order1 = new Order(idComanda,idClient, LocalDateTime.now(),produseNoi);
            service.setOrder(order1);


            /*for(int i=0; i<produseNoi.size();i++){
                suma += produseNoi.get(i).getPrice();
            }*/


        }
    }
}
