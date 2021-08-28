package model;

public class Product {
    private int id;
    private String nume;
    private int cantitate;

    /**
     * @param id id-ul produsului
     * @param nume numele produsului
     * @param cantitate cantitea produsului
     */
    public Product(int id, String nume,int cantitate) {
        super();
        this.id = id;
        this.nume = nume;
        this.cantitate = cantitate;
    }

    public Product(String nume, int cantitate) {
        super();
        this.nume = nume;
        this.cantitate = cantitate;
    }
    public Product(int id) {
        super();
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    /**
     * @return suprascrierea metodei toString pentru o afisare prietenoasa
     */
    public String toString() {
        return "Produs "+ nume + " cantitate "+cantitate;
    }
}
