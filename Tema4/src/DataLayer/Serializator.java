package DataLayer;

import BussinesLayer.CompositeProduct;
import BussinesLayer.UserBL;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Serializator {
    UserBL utilizator;
    ArrayList<UserBL> utilizatori;
    ArrayList<CompositeProduct> produseCompuse;

    public Serializator(UserBL utilizator) throws IOException {
        this.utilizator = utilizator;
        FileOutputStream fileOut = new FileOutputStream("B:\\InteliJI_Idea\\Tema4\\src\\users.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(utilizator);
        out.close();
        fileOut.close();
    }

    public Serializator(ArrayList<UserBL> utilizatori) throws IOException {
        this.utilizatori = utilizatori;
        FileOutputStream fileOut = new FileOutputStream("B:\\InteliJI_Idea\\Tema4\\src\\users.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(utilizatori);
        out.close();
        fileOut.close();


    }
}
