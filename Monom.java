import java.util.ArrayList;

public class Monom { //atributele monomului sunt grad si coeficient
    private int grad;
    private double coeficient;

    public Monom() {
        this.grad = grad;
        this.coeficient = coeficient;
    }

    public Monom(double coeficient, int grad) {
        this.grad = grad;
        this.coeficient = coeficient;
    }

    public int getGrad() {
        return grad;
    }

    public void setGrad(int grad) {
        this.grad = grad;
    }

    public double getCoeficient() {
        return coeficient;
    }

    public void setCoeficient(int coeficient) {
        this.coeficient = coeficient;
    }

    /*public Monom divide(Monom m2) {
        Monom c = new Monom();
        if(this.getGrad()>m2.getGrad())
            c = new Monom(this.getCoeficient()/m2.getCoeficient(), this.getGrad() - m2.getGrad());
        System.out.println(c);
        return c;
    }
    public Monom inmultireMonoame(Monom m2) {
        Monom rez = new Monom(this.getCoeficient() * m2.getCoeficient(), this.getGrad() + m2.getGrad());
        return rez;
    }*/

    public String toString() { //metoda toString suprascrisa a.i. atunci cand coeficientul e 0 sa nu apara nimic, cand gradul e 0 sa apara doar coeficientul(cu semnul + sau -), iar in rest sa apara coeficientul cu semn + X^ la putere
        if (coeficient == 0) {
            return ""+"";
        } else if (grad == 0) {
            if(coeficient>0) {
                return "+" + coeficient;
            }else {
                return coeficient + "";
            }
        } else if(coeficient >0){
            return "+"+coeficient + "X^" + grad+"";
        }else{
            return coeficient + "X^" + grad+"";
        }
    }


}
