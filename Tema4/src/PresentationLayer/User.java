package PresentationLayer;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class User extends JFrame {
    JTextField username;
    JTextField password;
    JComboBox functii;
    JButton submit;
    JButton addUser;

    public User() {
        JFrame frame = new JFrame("Log In");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        this.username = new JTextField("alin");
        this.password = new JTextField("popescu");
        List<String> lista = new ArrayList<>();
        lista.add("Administrator");
        lista.add("Client");
        lista.add("Employee");
        this.functii = new JComboBox(lista.toArray());
        this.submit = new JButton("Submit");
        this.addUser = new JButton("AddUser");


        JPanel panel = new JPanel();
        panel.add(username);
        panel.add(password);
        panel.add(functii);
        panel.add(submit);
        panel.add(addUser);

        frame.add(panel);
        frame.setVisible(true);
    }

    public JTextField getUsername() {
        return username;
    }

    public void setUsername(JTextField username) {
        this.username = username;
    }

    public JTextField getPassword() {
        return password;
    }

    public void setPassword(JTextField password) {
        this.password = password;
    }

    public JComboBox getFunctii() {
        return functii;
    }

    public void setFunctii(JComboBox functii) {
        this.functii = functii;
    }

    public void addSubmitListener(ActionListener mal) {
        submit.addActionListener(mal);
    }

    public void addUserListener(ActionListener mal) {
        addUser.addActionListener(mal);
    }


}
