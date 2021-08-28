package Start;

import BussinesLayer.*;
import PresentationLayer.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Start {
    public static void main(String[] args) throws IOException {
        BaseProduct.readFile();

        User user = new User();
        Controller controller = new Controller(user);
        //DeliveryService controller = new DeliveryService(user);


        /*LocalDateTime time = LocalDateTime.now();
        List<BaseProduct> lista = BaseProduct.readFile();
        List<BaseProduct> listaAux = new ArrayList<>();
        listaAux.add(lista.get(0));
        listaAux.add(lista.get(1));
        CompositeProduct compositeProduct = new CompositeProduct("Fel1",listaAux);
        Order order4 = new Order();
        CompositeObserver co = new CompositeObserver();
        order4.addObserver(co);
        order4.setOrderID(1);
        order4.setClientID(3);
        order4.setOrderDate(time);
        order4.setCompositeProduct(compositeProduct);*/

        //DeliveryService ds = new DeliveryService();
        //ds.deserializare();
        //ds.salvareInformatii();




    }
}
