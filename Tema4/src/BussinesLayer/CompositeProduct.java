package BussinesLayer;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CompositeProduct extends MenuItem implements Serializable {
    private List<BaseProduct> productList = new ArrayList<>();

    String titluProdCompus;

    public CompositeProduct(String titluProdCompus,List<BaseProduct> productList) {
        super();
        this.titluProdCompus = titluProdCompus;
        this.productList = productList;
    }

    public void add(BaseProduct product) {
        this.productList.add(product);
    }
    public void remove(BaseProduct product){
        this.productList.remove(product);
    }

    public void importProducts(){
        setProductList(BaseProduct.readFile());
    }

    public int computePrice(){
        int pret = 0;
        for(BaseProduct product:productList){
            pret +=product.getPrice();
        }
        return pret;
    }

    public List<BaseProduct> getProductList() {
        return productList;
    }

    public void setProductList(List<BaseProduct> productList) {
        this.productList = productList;
    }

    public String toString() {
        return titluProdCompus + " " + productList;
    }

}
