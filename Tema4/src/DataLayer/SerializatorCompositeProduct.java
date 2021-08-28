package DataLayer;

import BussinesLayer.CompositeProduct;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class SerializatorCompositeProduct {
    List<CompositeProduct> produseCompuse;


    public SerializatorCompositeProduct(List<CompositeProduct> produseCompuse) throws IOException {
        this.produseCompuse = produseCompuse;
        FileOutputStream fileOut = new FileOutputStream("B:\\InteliJI_Idea\\Tema4\\src\\composite.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(produseCompuse);
        out.close();
        fileOut.close();


    }
}
