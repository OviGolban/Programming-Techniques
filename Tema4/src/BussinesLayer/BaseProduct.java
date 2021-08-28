package BussinesLayer;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BaseProduct extends MenuItem implements Serializable {

    String title;
    float rating;
    int calories;
    int protein;
    int fat;
    int sodium;
    int price;

    public BaseProduct() {
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;

    }

    public BaseProduct(String title, float rating, int calories, int protein, int fat, int sodium, int price){
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
    }

    public static ArrayList<BaseProduct> readFile() {
        String fileName = "B:\\InteliJI_Idea\\Tema4\\src\\products.csv";
        List<String> lista = new ArrayList<>();
        ArrayList<BaseProduct> listaProduse = new ArrayList<>();
        try(Stream<String> stream = Files.lines(Paths.get(fileName))) {
            lista = stream.distinct().collect(Collectors.toList());
            lista.remove(lista.get(0));
            for(String s:lista){
                System.out.println(s);
                String[] obiectDeCreat = s.split(",");
                BaseProduct product = new BaseProduct(obiectDeCreat[0],Float.parseFloat(obiectDeCreat[1]),Integer.parseInt(obiectDeCreat[2]),Integer.parseInt(obiectDeCreat[3]),Integer.parseInt(obiectDeCreat[4]),Integer.parseInt(obiectDeCreat[5]),Integer.parseInt(obiectDeCreat[6]));
                listaProduse.add(product);
            }

        }catch(IOException e){
            e.printStackTrace();
        }
        return listaProduse;
    }
    public static ArrayList<MenuItem> readFile1() {
        String fileName = "B:\\InteliJI_Idea\\Tema4\\src\\products.csv";
        List<String> lista = new ArrayList<>();
        ArrayList<MenuItem> listaProduse = new ArrayList<>();
        try(Stream<String> stream = Files.lines(Paths.get(fileName))) {
            lista = stream.distinct().collect(Collectors.toList());
            lista.remove(lista.get(0));
            for(String s:lista){
                System.out.println(s);
                String[] obiectDeCreat = s.split(",");
                BaseProduct product = new BaseProduct(obiectDeCreat[0],Float.parseFloat(obiectDeCreat[1]),Integer.parseInt(obiectDeCreat[2]),Integer.parseInt(obiectDeCreat[3]),Integer.parseInt(obiectDeCreat[4]),Integer.parseInt(obiectDeCreat[5]),Integer.parseInt(obiectDeCreat[6]));
                listaProduse.add(product);
            }

        }catch(IOException e){
            e.printStackTrace();
        }
        return listaProduse;
    }
    public int computePrice(){
        int price = 0;
        for(BaseProduct product: readFile()){
            price += product.price;
        }
        return price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getSodium() {
        return sodium;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String toString() {
        return "MenuItem "+ title + " "+ rating + " "+ calories + " " + protein + " " + fat + " " + sodium + " " + price;
    }
}
