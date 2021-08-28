package BussinesLayer;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MenuItem  {
    String titluMeniu;
    int price;

    public MenuItem() {
        this.titluMeniu = titluMeniu;
        this.price = price;
    }
    public MenuItem(String titluMeniu,int price) {

        this.titluMeniu = titluMeniu;
        this.price = price;
    }

    public String getTitluMeniu() {
        return titluMeniu;
    }

    public void setTitluMeniu(String titluMeniu) {
        this.titluMeniu = titluMeniu;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
