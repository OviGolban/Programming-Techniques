package bll.validators;

import dao.OrderDAO;
import dao.ProductDAO;
import model.Order;
import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class OrderBLL {
    private List<Validator<Order>> validators;
    public OrderBLL() {
        validators = new ArrayList<Validator<Order>>();
        validators.add(new ComandaValidator());
    }

    public Order findOrderById(int idOrder) {
        Order st = OrderDAO.findById(idOrder);
        if (st == null) {
            throw new NoSuchElementException("The product with id =" + idOrder + " was not found!");
        }
        return st;
    }

    public int insertOrder(Order order) {
        for (Validator<Order> v : validators) {
            v.validate(order);
        }
        return OrderDAO.insert(order);
    }
}
