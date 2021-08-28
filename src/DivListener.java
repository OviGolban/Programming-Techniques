import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DivListener implements ActionListener {
    private JTextField tf1;
    private JTextField tf2;
    private JTextField res;

    public DivListener(JTextField tf1, JTextField tf2, JTextField res) {
        this.tf1 = tf1;
        this.tf2 = tf2;
        this.res = res;
    }


    public void actionPerformed(ActionEvent e) {
        int a = Integer.parseInt(tf1.getText());
        int b = Integer.parseInt(tf2.getText());
        res.setText((float) a / b + " ");


    }
}
