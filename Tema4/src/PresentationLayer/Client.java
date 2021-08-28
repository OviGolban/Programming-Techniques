package PresentationLayer;

import BussinesLayer.BaseProduct;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Client extends JFrame {
    JButton view;
    JTextArea area;

    JTextField titlu;
    JTextField rating;
    JTextField calories;
    JTextField protein;
    JTextField fat;
    JTextField sodium;
    JTextField price;
    JTextField idComanda;
    JTextField idClient;

    JButton search;

    JComboBox box1;
    JComboBox box2;
    JComboBox box3;

    JButton order;

    public Client() {
        JFrame frame = new JFrame("Client");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.view = new JButton("View");
        this.area = new JTextArea();
        this.area.setPreferredSize(new Dimension(400, 400));

        this.titlu = new JTextField("Title");
        this.rating = new JTextField("Rating");
        this.calories = new JTextField("Calories");
        this.protein = new JTextField("Protein");
        this.fat = new JTextField("Fat");
        this.sodium = new JTextField("Sodium");
        this.price = new JTextField("Price");
        this.idComanda = new JTextField("idComanda");
        this.idClient = new JTextField("idClient");


        this.box1 = new JComboBox(BaseProduct.readFile().toArray());
        this.box2 = new JComboBox(BaseProduct.readFile().toArray());
        this.box3 = new JComboBox(BaseProduct.readFile().toArray());
        this.order = new JButton("Order");
        this.search = new JButton("Search");


        JPanel panel = new JPanel();
        panel.add(view);
        panel.add(titlu);
        panel.add(rating);
        panel.add(calories);
        panel.add(protein);
        panel.add(fat);
        panel.add(sodium);
        panel.add(price);
        panel.add(idClient);
        panel.add(idComanda);
        panel.add(box1);
        panel.add(box2);
        panel.add(box3);
        panel.add(order);
        panel.add(search);
        panel.add(area);
        frame.add(panel);
        frame.setSize(500, 500);
        frame.setVisible(true);

    }

    public void viewListener(ActionListener mal) {
        view.addActionListener(mal);
    }

    public void searchListener(ActionListener mal) {
        search.addActionListener(mal);
    }

    public void orderListener(ActionListener mal) {
        order.addActionListener(mal);
    }

    public void adaugaLaAfisaj(String s) {
        this.area.setText(this.area.getText() + "\n" + s);
    }

    public JButton getView() {
        return view;
    }

    public void setView(JButton view) {
        this.view = view;
    }

    public JTextArea getArea() {
        return area;
    }

    public void setArea(JTextArea area) {
        this.area = area;
    }

    public JTextField getTitlu() {
        return titlu;
    }

    public void setTitlu(JTextField titlu) {
        this.titlu = titlu;
    }

    public JTextField getRating() {
        return rating;
    }

    public void setRating(JTextField rating) {
        this.rating = rating;
    }

    public JTextField getCalories() {
        return calories;
    }

    public void setCalories(JTextField calories) {
        this.calories = calories;
    }

    public JTextField getProtein() {
        return protein;
    }

    public void setProtein(JTextField protein) {
        this.protein = protein;
    }

    public JTextField getFat() {
        return fat;
    }

    public void setFat(JTextField fat) {
        this.fat = fat;
    }

    public JTextField getSodium() {
        return sodium;
    }

    public void setSodium(JTextField sodium) {
        this.sodium = sodium;
    }

    public JTextField getPrice() {
        return price;
    }

    public void setPrice(JTextField price) {
        this.price = price;
    }

    public JButton getSearch() {
        return search;
    }

    public void setSearch(JButton search) {
        this.search = search;
    }

    public JComboBox getBox1() {
        return box1;
    }

    public void setBox1(JComboBox box1) {
        this.box1 = box1;
    }

    public JComboBox getBox2() {
        return box2;
    }

    public void setBox2(JComboBox box2) {
        this.box2 = box2;
    }

    public JComboBox getBox3() {
        return box3;
    }

    public void setBox3(JComboBox box3) {
        this.box3 = box3;
    }

    public JButton getOrder() {
        return order;
    }

    public void setOrder(JButton order) {
        this.order = order;
    }

    public JTextField getIdComanda() {
        return idComanda;
    }

    public void setIdComanda(JTextField idComanda) {
        this.idComanda = idComanda;
    }

    public JTextField getIdClient() {
        return idClient;
    }

    public void setIdClient(JTextField idClient) {
        this.idClient = idClient;
    }
}
