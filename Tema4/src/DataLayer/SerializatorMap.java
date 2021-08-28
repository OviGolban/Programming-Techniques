package DataLayer;

import BussinesLayer.MenuItem;
import BussinesLayer.Order;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class SerializatorMap {
    Map<Order, ArrayList<MenuItem>> map=new Hashtable<>();

    public SerializatorMap(Map<Order, ArrayList<MenuItem>> map) throws IOException {
        this.map = map;
        FileOutputStream fileOut = new FileOutputStream("B:\\InteliJI_Idea\\Tema4\\src\\file.txt");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(map);
        out.close();
        fileOut.close();
    }
}
