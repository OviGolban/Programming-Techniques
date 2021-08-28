package PresentationLayer;

import BussinesLayer.Order;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Observer;

public class Employee extends JFrame implements Observer {
    JTextArea area;

    public Employee() {
        JFrame frame = new JFrame("Employee");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.area = new JTextArea();
        area.setPreferredSize(new Dimension(450, 450));

        JPanel panel = new JPanel();
        panel.add(area);
        frame.add(panel);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    public JTextArea getArea() {
        return area;
    }

    public void setArea(JTextArea area) {
        this.area = area;
    }

    public void adaugaLaAfisaj(String s) {
        this.area.setText(this.area.getText() + "\n" + s);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof Order) {
            System.out.println("S-a efectuat o comanda  " + (arg).toString());
            this.adaugaLaAfisaj("S-a efectuat o noua \n " + arg.toString());
        }
    }
}
