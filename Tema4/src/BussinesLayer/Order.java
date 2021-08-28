package BussinesLayer;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

public class Order extends Observable implements Serializable {
    int orderID;
    int clientID;
    LocalDateTime orderDate;
    ArrayList<MenuItem> meniuItem;

    public Order() {
        this.orderID = orderID;
        this.clientID = clientID;
        this.orderDate = orderDate;
        this.meniuItem = meniuItem;
    }


    public Order(int orderID, int clientID, LocalDateTime orderDate, ArrayList<MenuItem> meniuItem) {
        this.orderID = orderID;
        this.clientID = clientID;
        this.orderDate = orderDate;
        this.meniuItem = meniuItem;
    }

    public String toString() {

            return "orderID " + orderID + " clientID " + clientID + " orderDate " + orderDate + " produse de baza " + meniuItem.toString() ;

        }


    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
        setChanged();
        notifyObservers(orderDate);
    }
    public ArrayList<MenuItem> getMeniuItem() {
        return meniuItem;
    }

    public void setMeniuItem(ArrayList<MenuItem> meniuItem) {
        this.meniuItem = meniuItem;
    }

    public void setMenuItem(ArrayList<MenuItem> meniuItem) {

        this.meniuItem = meniuItem;

    }
    public int computePrice() {
        int pret=0;
        for(int i=0; i<meniuItem.size();i++) {
            pret += meniuItem.get(i).getPrice();
        }
        return pret;
    }


    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if(!(o instanceof Order))
            return false;
        Order order = (Order) o;
        return ((Order) order).clientID == clientID && order.orderID == orderID &&
                order.orderDate.equals(orderDate) &&
                order.meniuItem.equals(meniuItem);
    }
    @Override
    public int hashCode() {
        Order order = new Order();
        int result = 17;
        result = 31 * result + order.getClientID();
        result = 31 * result + order.getOrderID();
        return result;
    }


}
