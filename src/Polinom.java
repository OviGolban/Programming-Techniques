import java.util.*;

public class Polinom { //clasa Polinom, Polinomul este alcatuit dintr-un ArrayList de monoame
    private ArrayList<Monom> monoame;

    public Polinom() {
        this.monoame = monoame;
    }

    public Polinom(ArrayList<Monom> monoame) {
        this.monoame = monoame;
    }

    public ArrayList<Monom> getMonoame() {
        return monoame;
    }

    public void setMonoame(ArrayList<Monom> monoame) {
        this.monoame = monoame;
    }

    public String adunare(Polinom p2) {//operatia de adunare p1.adunare(p2)

        ArrayList<Monom> listaRezultat = new ArrayList<Monom>(); //lista unde voi tine polinomul rezultat
        listaRezultat.addAll(this.monoame);
        listaRezultat.addAll(p2.getMonoame());//adaug cele 2 ArrayListe ale polinoamelor primite ca parametrii
        double rezultatCoeficient;
        for (Monom m1 : this.monoame) { //parcurg listele de polinoame, iar daca se gasesc monoame de grad egal se face adunare intre ele
            for (Monom m2 : p2.getMonoame()) {
                if (m1.getGrad() == m2.getGrad()) {//am gasit monoame de grad egal
                    rezultatCoeficient = m1.getCoeficient() + m2.getCoeficient();
                    Monom monomRezultat = new Monom(rezultatCoeficient, m1.getGrad());
                    listaRezultat.add(monomRezultat);
                    listaRezultat.remove(m1);//elimin monoamele care mi-au constituit obiectul adunarii
                    listaRezultat.remove(m2);
                }
            }
        }
        listaRezultat.sort(new Comparare());//sortez lista in functie de putere descrescator
        Polinom rezultat = new Polinom(listaRezultat);//creez polinomul rezultat
        return rezultat.toString();
    }

    public String scadere(Polinom p2) {
        ArrayList<Monom> listaRezultat = new ArrayList<Monom>();//lista pentru polinomul rezultat
        ArrayList<Monom> monomNou2 = new ArrayList<Monom>();
        for (Monom m : p2.getMonoame())
            monomNou2.add(new Monom(-m.getCoeficient(), m.getGrad()));//la al doilea polinom inmultesc cu -1 toti coeficientii
        listaRezultat.addAll(this.monoame);
        listaRezultat.addAll(monomNou2);
        double rezultatCoeficient;

        for (Monom m1 : this.monoame) {//adunare ca mai sus
            for (Monom m2 : monomNou2) {
                if (m1.getGrad() == m2.getGrad()) {
                    rezultatCoeficient = m1.getCoeficient() + m2.getCoeficient();
                    Monom monomRezultat = new Monom(rezultatCoeficient, m1.getGrad());
                    listaRezultat.add(monomRezultat);
                    listaRezultat.remove(m1);
                    listaRezultat.remove(m2);
                }
            }
        }
        listaRezultat.sort(new Comparare());
        Polinom rezultat = new Polinom(listaRezultat);//creez polinmul rezultat
        return rezultat.toString();
    }

    public String inmultire(Polinom p2) {
        ArrayList<Monom> listaRezultat = new ArrayList<Monom>();//lista intermediara in care tin polinomul fara a reduce termenii
        ArrayList<Monom> listaFinala = new ArrayList<Monom>();//lista cu termenii redusi
        listaRezultat.addAll(this.monoame);listaRezultat.addAll(p2.getMonoame());
        double rezultatCoeficient = 0;int rezultatPutere;
        for (Monom m1 : this.monoame) {//inmultesc membrii fiecare cu fiecare
            for (Monom m2 : p2.getMonoame()) {
                    rezultatCoeficient = m1.getCoeficient() * m2.getCoeficient();
                    rezultatPutere = m1.getGrad() + m2.getGrad();
                    Monom monomRezultat = new Monom(rezultatCoeficient, rezultatPutere);
                    listaRezultat.add(monomRezultat);
                    listaRezultat.remove(m1);
                    listaRezultat.remove(m2);
                }
        }
        listaRezultat.sort(new Comparare());
        listaFinala.addAll(listaRezultat);//pun in lista finala, lista intermediara, dupa care fac operatiile intre termenii asemenea
        for (Monom m1 : listaRezultat) {
            for (Monom m2 : listaRezultat)
                if ((m1.getGrad() == m2.getGrad()) && ((listaRezultat.indexOf(m1) > listaRezultat.indexOf(m2)))) {//verific ca termenii sa nu se afle pe aceeasi pozitie,adica sa nu fie chiar ei insusi
                    rezultatCoeficient = m1.getCoeficient() + m2.getCoeficient();
                    Monom monomRezultat = new Monom(rezultatCoeficient, m1.getGrad());
                    listaFinala.add(monomRezultat);
                    listaFinala.remove(m1);
                    listaFinala.remove(m2);
                }
        }
        listaFinala.sort(new Comparare());
        Polinom rezultat = new Polinom(listaFinala);
        return rezultat.toString();
    }

    public String derivare() {//operatia de derivare
        ArrayList<Monom> rezultat = new ArrayList<Monom>();
        for (Monom m1 : this.getMonoame()) {
            if (m1.getGrad() != 0) {//daca gradul e diferit de 0 voi scadea -1
                if (m1.getCoeficient() != 0) {//daca coeficientul e diferit de 0, atunci o sa il inmultesc cu gradul
                    Monom derivat = new Monom(m1.getCoeficient() * m1.getGrad(), m1.getGrad() - 1);
                    rezultat.add(derivat);
                } else {
                    Monom derivat = new Monom(0, 0);//daca coeficientul e 0 atunci polinomul e nul
                    rezultat.add(derivat);
                }
            } else {
                if (m1.getGrad() == 0) ;//daca gradul e 0, inseama ca am un termen liber care la derivare va da 0
                Monom derivat = new Monom(0, 0);
                rezultat.add(derivat);
            }
        }
        Polinom rez = new Polinom(rezultat);//creez polinomul rezultat
        return rez.toString();
    }

    public String integrare() {//operatia de integrare
        ArrayList<Monom> rezultat = new ArrayList<Monom>();
        for (Monom m1 : this.getMonoame()) {//parcurg lista de monoame a polinomului
            if (m1.getCoeficient() == 0) {//daca coeficientul e 0 si dupa integrare va ramane tot 0
                Monom integrat = new Monom(0, 0);
                rezultat.add(integrat);
            }
            if (m1.getGrad() == 0) {//daca gradul e 0 si coeficientul nenul, obtin un monom de grad 1
                Monom integrat = new Monom(m1.getCoeficient(), 1);
                rezultat.add(integrat);//adaug rezultatul la lista
            } else {//altfel am un polinom cu coeficient si grad nenul pe care il integraz clasic, coeficient/(grad+1) si gradNou = grad+1
                Monom integrat = new Monom(m1.getCoeficient() / (m1.getGrad() + 1), m1.getGrad() + 1);
                rezultat.add(integrat);
            }


        }
        Polinom rez = new Polinom(rezultat);
        return rez.toString();
    }
    /*public Monom getMonomMax(){
        Monom monomMax = new Monom();
        int gradMax=0;
        for(Monom m:monoame)
            if(m.getGrad()>gradMax) {
                gradMax=m.getGrad();
                monomMax = m;
            }
        return monomMax;
    }
    public Polinom multiply(Monom monom){
        for(Monom m:monoame)
            this.monoame.add(m.inmultireMonoame(monom));
        return this;
    }
    public String impartire(Polinom p2) {
        Polinom impartitor = new Polinom();
        Polinom rest = new Polinom();
        ArrayList<Monom> cat = new ArrayList<>();
        while(this.getMonomMax().getGrad()>=p2.getMonomMax().getGrad()){
            Monom a = this.getMonomMax();
            Monom b = p2.getMonomMax();
            Monom c =a.divide(b);
            cat.add(c);
            Polinom aux = impartitor.multiply(c);
            rest = rest.scadere(aux);

        }
    return cat.toString();
    }*/

    public String toString() {//suprascriu metoda toString pentru a avea varianta clasica de afisare a unui polinom
        String rezultat = new String();
        for(Monom m:monoame)
            rezultat +=m.toString();

        return rezultat;
    }
}