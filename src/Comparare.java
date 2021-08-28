import java.util.Comparator;

public class Comparare implements Comparator<Monom> {
    public int compare(Monom m1, Monom m2) {
        return m2.getGrad() - m1.getGrad();
    }
}
