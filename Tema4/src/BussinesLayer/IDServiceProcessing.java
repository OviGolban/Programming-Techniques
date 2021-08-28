package BussinesLayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface IDServiceProcessing {
    public ArrayList<Order> deserializare();
    public void salvareInformatii() throws IOException;
    /*
     *@pre produseBaza.size()!=0
     * @post produseBaza.size() = produseBaza.size()@pre-1
     */
    public void deleteProduct(String nume);
    /*
     *@pre produseBaza.size()!=0
     * @post produseBaza.size() = produseBaza.size()@pre+1
     */
    public void addProduct(BaseProduct bp);
    public void modifyProduct(BaseProduct bp, String nume);
    public void importProducts();
    /*
     *@pre produseCompuse.size()>=0
     * @post produseCompuse.size() = produseCompuse.size()@pre+1
     */
    public void addCompositeProduct(CompositeProduct cp);
    public List<MenuItem> searchProduct1(float rating, int calories, int price);
    public List<MenuItem> searchProduct2(String nume);
    /*
   @pre idComanda >=0
   @pre idClient >=0
   @pre produseNoi != null
   @post listaComanda.size() == listaComanda.size()@pre+1
    */
    public void order(int idComanda, int idClient, ArrayList<MenuItem> produseNoi);
}
