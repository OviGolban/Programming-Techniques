import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainClass {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Meniu");//creez frame-ul meniu
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();  //creez panel-ul//creez un panel pentru a pune obiectele pe el
        panel.setLayout(new GridLayout(15, 1));
        JLabel lPoli1 = new JLabel("Polinom1");
        JLabel lPoli2 = new JLabel("Polinom2");
        JTextField poli1 = new JTextField("Poli1");//field pt introducerea primului polinom
        JTextField poli2 = new JTextField("Poli2:");//field pt introducerea celui de-al doilea polinom
        JTextField rezultat1 = new JTextField("Rezultat1");//field pt afisarea rezultatului1
        JTextField rezultat2 = new JTextField("Rezultat2");//field pt afisarea rezultatului2

        JButton add = new JButton("+");//creez butoane cu nume sugestive
        JButton subtract = new JButton("-");
        JButton multiplication = new JButton("*");
        JButton divison = new JButton("/");
        JButton derivation = new JButton("Derivation");
        JButton integration = new JButton("Integration");

        panel.add(lPoli1);//adaug butoanele pe panel
        panel.add(poli1);
        panel.add(lPoli2);
        panel.add(poli2);
        panel.add(add);
        panel.add(subtract);
        panel.add(multiplication);
        panel.add(divison);
        panel.add(derivation);
        panel.add(integration);
        panel.add(rezultat1);
        panel.add(rezultat2);


        add.addActionListener(new AdListener(poli1, poli2, rezultat1));//apelez ascultatorii pentru fiecare buton in parte
        subtract.addActionListener(new SubListener(poli1, poli2, rezultat1));
        multiplication.addActionListener(new MulListener(poli1, poli2, rezultat1));
        divison.addActionListener(new DivListener(poli1, poli2, rezultat1));
        derivation.addActionListener(new DerivListener(poli1, poli2, rezultat1, rezultat2));
        integration.addActionListener(new IntegListener(poli1, poli2, rezultat1, rezultat2));


        frame.setContentPane(panel);//setez continutul
        frame.setVisible(true);//setez vizibilitate pe true

    }
}
