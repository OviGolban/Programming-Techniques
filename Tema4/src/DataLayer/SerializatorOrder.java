package DataLayer;

import BussinesLayer.Order;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class SerializatorOrder {
    List<Order> orders;


    public SerializatorOrder(List<Order> orders) throws IOException {
        this.orders = orders;
        FileOutputStream fileOut = new FileOutputStream("B:\\InteliJI_Idea\\Tema4\\src\\orders.txt");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(orders);
        out.close();
        fileOut.close();


    }

}
