package bll.validators;

import model.Order;

public class ComandaValidator implements Validator<Order>{
    @Override
    public void validate(Order order) {
        if(order.getCantitate()<0) {
            throw new IllegalArgumentException("Cantitate incorecta");
        }

    }
}
