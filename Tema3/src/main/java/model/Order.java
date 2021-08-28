package model;

public class Order {
    private int idOrder;
    private int idClient;
    private int idProdus;
    private int cantitate;

    /**
     * @param idOrder nr comenzii
     * @param idClient nrClientului
     * @param idProdus nrProdusului
     * @param cantitate cantitatea care se doreste a fi comandata
     */
    public Order(int idOrder,int idClient, int idProdus, int cantitate) {
        this.idOrder = idOrder;
        this.idClient = idClient;
        this.idProdus = idProdus;
        this.cantitate = cantitate;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdProdus() {
        return idProdus;
    }

    public void setIdProdus(int idProdus) {
        this.idProdus = idProdus;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }
}
