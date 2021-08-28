import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OperationTest {

    @Test

    public void OperationTest(){
        Monom m1 = new Monom(2,3);
        Monom m2 = new Monom (3,1);
        ArrayList<Monom> lista1 = new ArrayList();//creez lista pentru primul polinom
        lista1.add(m1);
        lista1.add(m2);

        Monom m3 = new Monom(3,3);
        Monom m4 = new Monom (2,1);
        ArrayList<Monom> lista2 = new ArrayList();//creez lista pentru al doilea polinom
        lista2.add(m3);
        lista2.add(m4);
        Polinom p1 = new Polinom(lista1);
        Polinom p2 = new Polinom(lista2);

        Monom m5 = new Monom(-1,2);
        Monom m6 = new Monom(3,2);
        ArrayList<Monom> lista3 = new ArrayList();//creez lista pentru al treilea polinom
        ArrayList<Monom> lista4 = new ArrayList();//creez lista pentru al patrulea polinom
        lista3.add(m5);
        lista4.add(m6);
        Polinom p3 = new Polinom(lista3);
        Polinom p4 = new Polinom(lista4);
        //efectuez teste pentru a verifica diferite operatii pe diverse polinoame
        assertEquals("+2.0X^2",p3.adunare(p4));
        assertEquals("+5.0X^3+5.0X^1",p1.adunare(p2));
        assertEquals("-1.0X^3+1.0X^1",p1.scadere(p2));
        assertEquals("+6.0X^6+13.0X^4+6.0X^2",p1.inmultire(p2));
        assertEquals("+6.0X^2+3.0",p1.derivare());
        assertEquals("+9.0X^2+2.0",p2.derivare());
        assertEquals("+0.5X^4+1.5X^2",p1.integrare());
        assertEquals("+0.75X^4+1.0X^2",p2.integrare());
        assertEquals("+2.0X^2",p3.adunare(p4));
        assertEquals("-4.0X^2", p3.scadere(p4));

    }
}
