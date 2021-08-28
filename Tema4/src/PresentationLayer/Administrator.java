package PresentationLayer;

import BussinesLayer.BaseProduct;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class Administrator extends JFrame {
    JPanel panel;
    JFrame frame;
    JButton imports;
    JButton delete;
    JButton modify;
    JButton adaugare;
    JButton view;
    JButton compositeProduct;
    JTextField titlu;
    JTextField rating;
    JTextField calories;
    JTextField protein;
    JTextField fat;
    JTextField sodium;
    JTextField price;
    JComboBox produse;
    JTextField startHour;
    JTextField finishHour;
    JTextField nrOrder;
    JTextField value;
    JTextField ziua;
    JButton generate1;
    JButton generate2;
    JButton generate3;
    JButton generate4;
    JTextArea afisaj;


    public Administrator() {
        this.frame = new JFrame("Administrator");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.panel = new JPanel();
        panel.setLayout(new GridLayout(10, 1));

        this.imports = new JButton("Imports Product");
        this.delete = new JButton("Delete");
        this.modify = new JButton("Modify");
        this.adaugare = new JButton("Add");
        this.view = new JButton("View");
        this.compositeProduct = new JButton("Composite Product");
        List<BaseProduct> lista = BaseProduct.readFile();
        this.produse = new JComboBox(lista.toArray());
        produse.setSize(50, 50);

        this.titlu = new JTextField("Title");
        this.rating = new JTextField("Rating");
        this.calories = new JTextField("Calories");
        this.protein = new JTextField("Protein");
        this.fat = new JTextField("Fat");
        this.sodium = new JTextField("Sodium");
        this.price = new JTextField("Price");

        this.startHour = new JTextField("StartHour");
        this.finishHour = new JTextField("FinishHour");
        this.nrOrder = new JTextField("NrOrder");
        this.value = new JTextField("Value");
        this.ziua = new JTextField("Ziua");

        this.generate1 = new JButton("Generate1");
        this.generate2 = new JButton("Generate2");
        this.generate3 = new JButton("Generate3");
        this.generate4 = new JButton("Generate4");
        this.afisaj = new JTextArea();
        this.afisaj.setText("");

        panel.add(imports);
        panel.add(delete);
        panel.add(modify);
        panel.add(produse);
        panel.add(adaugare);
        panel.add(view);
        panel.add(compositeProduct);

        panel.add(titlu);
        panel.add(rating);
        panel.add(calories);
        panel.add(protein);
        panel.add(fat);
        panel.add(sodium);
        panel.add(price);

        panel.add(startHour);
        panel.add(finishHour);
        panel.add(nrOrder);
        panel.add(value);
        panel.add(ziua);
        panel.add(generate1);
        panel.add(generate2);
        panel.add(generate3);
        panel.add(generate4);
        panel.add(afisaj);

        frame.add(panel);
        frame.setSize(400, 400);
        frame.setVisible(true);

    }

    public void adaugaLaAfisaj(String s) {
        this.afisaj.setText(this.afisaj.getText() + "\n" + s);
    }

    public JButton getImports() {
        return imports;
    }

    public void setImports(JButton imports) {
        this.imports = imports;
    }

    public JButton getDelete() {
        return delete;
    }

    public void setDelete(JButton delete) {
        this.delete = delete;
    }

    public JButton getModify() {
        return modify;
    }

    public void setModify(JButton modify) {
        this.modify = modify;
    }

    public JButton getAdaugare() {
        return adaugare;
    }

    public void setAdaugare(JButton adaugare) {
        this.adaugare = adaugare;
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

    public JTextField getTitlu() {
        return titlu;
    }

    public void setTitlu(JTextField titlu) {
        this.titlu = titlu;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }


    public JComboBox getProduse() {
        return produse;
    }

    public void setProduse(JComboBox produse) {
        this.produse = produse;
    }

    public JTextField getStartHour() {
        return startHour;
    }

    public void setStartHour(JTextField startHour) {
        this.startHour = startHour;
    }

    public JTextField getFinishHour() {
        return finishHour;
    }

    public void setFinishHour(JTextField finishHour) {
        this.finishHour = finishHour;
    }

    public JTextField getNrOrder() {
        return nrOrder;
    }

    public void setNrOrder(JTextField nrOrder) {
        this.nrOrder = nrOrder;
    }

    public JTextField getValue() {
        return value;
    }

    public void setValue(JTextField value) {
        this.value = value;
    }

    public JTextField getZiua() {
        return ziua;
    }

    public void setZiua(JTextField ziua) {
        this.ziua = ziua;
    }

    public void addAddListener(ActionListener mal) {
        adaugare.addActionListener(mal);
    }

    public void addImportListener(ActionListener mal) {
        imports.addActionListener(mal);
    }

    public void addDeleteListener(ActionListener mal) {
        delete.addActionListener(mal);
    }

    public void editProductListener(ActionListener mal) {
        modify.addActionListener(mal);
    }

    public void viewListener(ActionListener mal) {
        view.addActionListener(mal);
    }

    public void addCompositeProductListener(ActionListener mal) {
        compositeProduct.addActionListener(mal);
    }

    public void generate1Listener(ActionListener mal) {
        generate1.addActionListener(mal);
    }

    public void generate2Listener(ActionListener mal) {
        generate2.addActionListener(mal);
    }

    public void generate3Listener(ActionListener mal) {
        generate3.addActionListener(mal);
    }

    public void generate4Listener(ActionListener mal) {
        generate4.addActionListener(mal);
    }

    public void adaugareComboBox(JComboBox box) {
        panel.add(box);
    }

}
