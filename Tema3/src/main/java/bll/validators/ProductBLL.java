package bll.validators;

import dao.ClientDAO;
import dao.ProductDAO;
import model.Client;
import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductBLL {
    private List<Validator<Product>> validators;

    /**
     * constructorul clasei ProductBLL
     */
    public ProductBLL() {
        validators = new ArrayList<Validator<Product>>();
        validators.add(new CantitateValidator());
    }

    /**
     * @param id id-ul de produs pe care vrem sa il cautam
     * @return produsul cu id-ul cautat
     */
    public Product findProductById(int id) {
        Product st = ProductDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return st;
    }

    /**
     * @param produs produsul care va fi inserat
     * @return returneaza id-ul produsului daca produsul a fost inserat cu succes
     */
    public int insertProdus(Product produs) {
        for (Validator<Product> v : validators) {
            v.validate(produs);
        }
        return ProductDAO.insert(produs);
    }

    /**
     * @param produs produsul care va fi editat
     */
    public void updateProdus(Product produs) {
        for(Validator<Product> v : validators) {
            v.validate(produs);
        }
        ProductDAO.update(produs);
    }

    /**
     * @param produs stergem produsul
     */
    public void deleteProdus(Product produs) {
        ProductDAO.delete(produs);
    }

    /**
     * vedem in tabel toate produsele din baza de date
     */
    public ArrayList<Object> view() {
       return  ProductDAO.view();
    }
}
