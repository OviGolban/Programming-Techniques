package BussinesLayer;

import DataLayer.*;
import PresentationLayer.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @method isWellFormed()
 */

public class DeliveryService extends Observable implements IDServiceProcessing {
    ArrayList<Order> listaComanda = new ArrayList<>();
    FileInputStream fileIn = null;
    private Order order;
    private List<Employee> observatori = new ArrayList<>();


    ArrayList<BaseProduct> produseBaza = BaseProduct.readFile();
    ArrayList<CompositeProduct> produseCompuse = new ArrayList<>();
    ArrayList<MenuItem> produseMeniu = BaseProduct.readFile1();
    Map<Order, ArrayList<MenuItem>> mapare = new Hashtable<>();
   /* ArrayList<MenuItem> listaMeniu = new ArrayList<>();
    //Meniuri meniuri = new Meniuri(produseBaza, produseCompuse);
    ArrayList<UserBL> listaDeVerificat = new ArrayList<>();
    ArrayList<UserBL> listaUtilizatori = new ArrayList<>();*/

    //private UserBL user;
    //JComboBox box = new JComboBox(produseAfisat.toArray());


    LocalDateTime now = LocalDateTime.now();
    LocalDateTime date1 = LocalDateTime.of(2017, 2, 14, 15, 56);
    LocalDateTime date2 = LocalDateTime.of(2019, 3, 20, 20, 20);
    LocalDateTime date3 = LocalDateTime.of(2020, 3, 17, 17, 30);
    LocalDateTime date4 = LocalDateTime.of(2021, 4, 10, 10, 30);
    LocalDateTime date5 = LocalDateTime.of(2021, 4, 19, 11, 20);


    ArrayList<Order> listaComenzi = new ArrayList<>();

    public DeliveryService(){
        this.produseBaza = produseBaza;
        this.produseCompuse = produseCompuse;
        this.produseMeniu = produseMeniu;
        this.mapare = mapare;
    }

    public DeliveryService(ArrayList<BaseProduct> produseBaza, ArrayList<CompositeProduct> produseCompuse,ArrayList<MenuItem> produseMeniu ,Map<Order, ArrayList<MenuItem>> mapare) {
        this.produseBaza = produseBaza;
        this.produseCompuse = produseCompuse;
        this.produseMeniu = produseMeniu;
        this.mapare = mapare;
    }

    public ArrayList<Order> deserializare() {
        try {
            fileIn = new FileInputStream("B:\\InteliJI_Idea\\Tema4\\src\\orders.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            listaComanda = (ArrayList<Order>) in.readObject();
        } catch (ClassNotFoundException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            if (fileIn != null) {
                try {
                    fileIn.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        return listaComanda;
    }

    public void salvareInformatii() throws IOException {
        ArrayList<Order> listaAuxiliara = new ArrayList<>();

        listaAuxiliara = deserializare();
        Map<Order, ArrayList<MenuItem>> collect = new Hashtable<>();
        for (Order order : listaAuxiliara) {

            collect.put(order, order.getMeniuItem());

        }
        for (Map.Entry<Order, ArrayList<MenuItem>> entry : collect.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        SerializatorMap serializatorMap = new SerializatorMap(collect);
    }

    public static ArrayList<UserBL> incarcareUtilizatori() {
        ArrayList<UserBL> listaDeVerificat = new ArrayList<>();
        FileInputStream fileIn = null;
        try {
            fileIn = new FileInputStream("B:\\InteliJI_Idea\\Tema4\\src\\users.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            listaDeVerificat = (ArrayList<UserBL>) in.readObject();


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
        return listaDeVerificat;
    }



    public void setOrder(Order order){
        this.order = order;
        setChanged();
        notifyObservers(order);


    }
/*
*@pre produseBaza.size()!=0
* @post produseBaza.size() = produseBaza.size()@pre-1
 */
    public void deleteProduct(String nume) {
        assert produseBaza.size()!=0;
        int marime = produseBaza.size();
        boolean flag = false;
        int indexDeSters = 0;
        for (BaseProduct p : produseBaza) {
            if (p.getTitle().equals(nume)) {
                flag = true;
            }
            if (flag == false) {
                indexDeSters++;
            }
        }

        if (flag == false) {
            System.out.println("Produsul e care doriti sa il stergeti nu exista!");
        } else {
            produseBaza.remove(indexDeSters);
            produseMeniu.remove(indexDeSters);
            for (int i = 0; i < produseBaza.size(); i++)
                System.out.println(produseBaza.get(i));

            assert produseBaza.size() == marime-1;
        }

        try {
            SerializatorBaseProduct ser = new SerializatorBaseProduct(produseBaza);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    /*
     *@pre produseBaza.size()!=0
     * @post produseBaza.size() = produseBaza.size()@pre+1
     */

    public void addProduct(BaseProduct bp) {
        int marime = produseBaza.size();
        assert produseBaza.size() != 0;
        produseBaza.add(bp);
        produseMeniu.add(bp);
        for (int i = 0; i < produseBaza.size(); i++)
            System.out.println(produseBaza.get(i));
        assert produseBaza.size() == marime+1;
        try {
            SerializatorBaseProduct ser = new SerializatorBaseProduct((ArrayList<BaseProduct>) produseBaza);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void modifyProduct(BaseProduct bp, String nume) {
        boolean flag = false;
        int indexDeCautat = 0;
        for (BaseProduct p : produseBaza) {
            if (p.getTitle().equals(nume)) {
                flag = true;
            }
            if (flag == false) {
                indexDeCautat++;
            }
        }

        if (flag == false) {
            System.out.println("Produsul e care doriti sa il cautati nu exista!");
        } else {
            produseBaza.set(indexDeCautat, bp);
            produseMeniu.set(indexDeCautat,bp);
            for (int i = 0; i < produseBaza.size(); i++)
                System.out.println(produseBaza.get(i));
        }
        try {
            SerializatorBaseProduct ser = new SerializatorBaseProduct(produseBaza);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void importProducts() {
        produseBaza = BaseProduct.readFile();
        produseMeniu = BaseProduct.readFile1();

        ArrayList<MenuItem> listaMeniu = new ArrayList<>();
        listaMeniu.add(produseBaza.get(0));
        listaMeniu.add(produseBaza.get(1));
        listaMeniu.add(produseBaza.get(2));
        Order order1 = new Order(1, 1, date1, listaMeniu);
        Order order2 = new Order(2, 1, date2, listaMeniu);
        Order order3 = new Order(3, 1, date3, listaMeniu);
        Order order4 = new Order(4, 1, date4, listaMeniu);
        Order order5 = new Order(5, 2, date5, listaMeniu);
        Order order6 = new Order(6, 3, date5, listaMeniu);
        listaComanda.add(order1);
        listaComanda.add(order2);
        listaComanda.add(order3);
        listaComanda.add(order4);
        listaComanda.add(order5);
        listaComanda.add(order6);

        try {
            SerializatorOrder ser = new SerializatorOrder(listaComanda);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    /*
     *@pre produseCompuse.size()>=0
     * @post produseCompuse.size() = produseCompuse.size()@pre+1
     */

    public void addCompositeProduct(CompositeProduct cp) {
        assert produseCompuse.size()>=0;
        int marime = produseCompuse.size();
        produseCompuse.add(cp);
        produseMeniu.add(cp);
        System.out.println("Produsele adaugate sunt:\n");
        for (int i = 0; i < produseCompuse.size(); i++)
            System.out.println(produseCompuse.get(i));
        assert produseCompuse.size() == marime + 1;
        try {
            SerializatorCompositeProduct ser = new SerializatorCompositeProduct(produseCompuse);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public List<MenuItem> searchProduct1(float rating, int calories, int protein) {
        List<MenuItem> listaCeruta = produseBaza.stream()
                .filter(produs -> produs.getRating() == rating && produs.getCalories() == calories && produs.getProtein() == protein)
                .collect(Collectors.toList());
        return listaCeruta;
    }

    public List<MenuItem> searchProduct2(String nume) {
        List<MenuItem> listaCeruta = produseBaza.stream()
                .filter(produs -> produs.getTitle().equals(nume))
                .collect(Collectors.toList());
        return listaCeruta;
    }
    /*
    @pre idComanda >=0
    @pre idClient >=0
    @pre produseNoi != null
    @post listaComanda.size() == listaComanda.size()@pre+1
     */

    public void order(int idComanda, int idClient, ArrayList<MenuItem> produseNoi) {
        int marime=listaComanda.size();
        assert idComanda >=0;
        assert idClient >=0;
        assert produseNoi != null;
        LocalDateTime time = LocalDateTime.now();
        //MenuObserver co = new MenuObserver();
        Order order1 = new Order();
        //order1.addObserver(co);
        order1.setOrderID(idComanda);
        order1.setClientID(idClient);
        order1.setOrderDate(time);
        order1.setMeniuItem(produseNoi);
        mapare.put(order1,produseNoi);

        FisierWriter fisier = new FisierWriter("B:\\InteliJI_Idea\\Tema4\\src\\bll.txt");
        fisier.scrieInFisier("---FACTURA COMANDA---");
        fisier.scrieInFisier(order1.toString());
        System.out.println();
        fisier.scrieInFisier("TOTAL DE ACHITAT:" + order1.computePrice());
        fisier.salveazaFisier();


        listaComanda.add(order1);
        assert listaComanda.size() == marime + 1;
        try {
            SerializatorOrder ser = new SerializatorOrder(listaComenzi);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        System.out.println("S-a plasat comanda");
    }
    public boolean isWellFormed() {
        if(produseBaza.size() == 0) {
            return false;
        }
        if(produseMeniu.size()==0) {
            return false;
        }
        return true;
    }

}

