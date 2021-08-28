import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DerivListener implements ActionListener {
    private JTextField tf1;
    private JTextField tf2;
    private JTextField res1;
    private JTextField res2;

    public DerivListener(JTextField tf1, JTextField tf2, JTextField res1, JTextField res2) {
        this.tf1 = tf1;
        this.tf2 = tf2;
        this.res1 = res1;
        this.res2 = res2;
    }


    public void actionPerformed(ActionEvent e) {
        String tokens = "[+]";
        ArrayList<Monom> monoame1 = new ArrayList<>();
        String a = tf1.getText();//preiau continutul primului field
        String a0 = a.replace(" ", "");//elimin spatiile goale
        String a1 = a0.replace("x", "X");//inlocuiesc x cu X
        String a2 = a1.replace("-", "+-");//inlocuiesc - cu +-
        String a3 = a2.replace("X+", "X^1+");//inlocuiesc  X+ cu X^1+
        String[] a4 = a3.split(tokens);//fac split
        try {
            for (String part : a4) {//parcurg array-ul de monoame
                if (!part.equals("")) {
                    if (part.contains("X^")) {//verific daca am X la putere
                        String coef1 = part.substring(0, part.indexOf("X"));//preiau ceea ce este in fata lui X, adica coeficientul
                        String exp1 = part.substring(part.indexOf("^") + 1);//preiau ceea ce este dupa X la putere
                        if (coef1.equals("")) {//daca coef1 e egal cu "" inseamna ca am facut split dupa + si monomul a avut coef 1, ceva de forma +X^....
                            Monom monom1 = new Monom(1, Integer.parseInt(exp1));
                            monoame1.add(monom1);
                        } else if (coef1.equals("-")) {//inseamna ca am avut un monom de forma -X^...,deci coeficientul e -1
                            Monom monom2 = new Monom(-1, Integer.parseInt(exp1));
                            monoame1.add(monom2);
                        } else {//altfel am un monom clasic
                            Monom monom = new Monom(Integer.parseInt(coef1), Integer.parseInt(exp1));
                            monoame1.add(monom);
                        }
                    }
                    if (!part.contains("X^")) {//daca nu am X^
                        if (!part.contains("X")) {//daca nu am nici X inseamna ca am termen liber
                            Monom monom = new Monom(Integer.parseInt(part), 0);
                            monoame1.add(monom);
                        } else {
                            String coef1 = part.substring(0, part.indexOf("X"));
                            if (coef1.equals("")) {
                                Monom monom1 = new Monom(1, 1);
                                monoame1.add(monom1);
                            } else if (coef1.equals("-")) {
                                Monom monom2 = new Monom(-1, 1);
                                monoame1.add(monom2);
                            } else {
                                Monom monom = new Monom(Integer.parseInt(coef1), 1);
                                monoame1.add(monom);
                            }

                        }
                    }
                }
            }
        } catch (Exception ex) {
            tf2.setText("Polinom incorect! Atentie:rezultat final incorect");

        }
        ArrayList<Monom> monoame2 = new ArrayList<>();
        String b = tf2.getText();
        String b0 = b.replace(" ", "");
        String b1 = b0.replace("x", "X");
        String b2 = b1.replace("-", "+-");
        String b3 = b2.replace("X+", "X^1+");
        String[] b4 = b3.split(tokens);

        try {
            for (String part : b4) {//aplicam aceeasi pasi ca mai sus
                if (!part.equals("")) {
                    if (part.contains("X^")) {
                        String coef2 = part.substring(0, part.indexOf("X"));
                        String exp2 = part.substring(part.indexOf("^") + 1);
                        if (coef2.equals("")) {
                            Monom monom1 = new Monom(1, Integer.parseInt(exp2));
                            monoame2.add(monom1);
                        } else if (coef2.equals("-")) {
                            Monom monom2 = new Monom(-1, Integer.parseInt(exp2));
                            monoame2.add(monom2);
                        } else {
                            Monom monom = new Monom(Integer.parseInt(coef2), Integer.parseInt(exp2));
                            monoame2.add(monom);
                        }
                    }
                    if (!part.contains("X^")) {
                        if (!part.contains("X")) {
                            Monom monom = new Monom(Integer.parseInt(part), 0);
                            monoame2.add(monom);
                        } else {
                            String coef2 = part.substring(0, part.indexOf("X"));
                            if (coef2.equals("")) {
                                Monom monom1 = new Monom(1, 1);
                                monoame2.add(monom1);
                            } else if (coef2.equals("-")) {
                                Monom monom2 = new Monom(-1, 1);
                                monoame2.add(monom2);
                            } else {
                                Monom monom = new Monom(Integer.parseInt(coef2), 1);
                                monoame2.add(monom);
                            }

                        }
                    }
                }
            }
        } catch (Exception exp) {
            tf2.setText("Polinom incorect! Atentie:rezultat final incorect");
        }
        Polinom p1 = new Polinom(monoame1);
        Polinom p2 = new Polinom(monoame2);

        res1.setText(p1.derivare());
        res2.setText(p2.derivare());
    }
}
