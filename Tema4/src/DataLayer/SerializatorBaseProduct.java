package DataLayer;

import BussinesLayer.BaseProduct;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class SerializatorBaseProduct {
    List<BaseProduct> produseBaza;


    public SerializatorBaseProduct(List<BaseProduct> produseBaza) throws IOException {
        this.produseBaza = produseBaza;
        FileOutputStream fileOut = new FileOutputStream("B:\\InteliJI_Idea\\Tema4\\src\\base.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(produseBaza);
        out.close();
        fileOut.close();


    }
}
