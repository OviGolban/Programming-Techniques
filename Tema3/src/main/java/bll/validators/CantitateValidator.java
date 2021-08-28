package bll.validators;

import model.Product;

public class CantitateValidator implements Validator<Product>{
    @Override
    public void validate(Product product) {
        if(product.getCantitate()<0 || product.getCantitate()>20000) {
            throw new IllegalArgumentException("Cantitate incorecta");
        }

    }
}
