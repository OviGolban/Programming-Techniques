package DataLayer;

import java.io.FileWriter;
import java.io.IOException;

public class FisierWriter {
    FileWriter fisier;

    public FisierWriter(String s) {
        try {
            this.fisier = new FileWriter(s);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void scrieInFisier(String s) {
        try {
            this.fisier.write(s + "\n");
        } catch (IOException exception) {
            System.out.println("Exception!");
        }
    }

    public void salveazaFisier() {
        try {
            fisier.close();
        } catch (IOException e) {
            System.out.println("Eroare la salvare fisier: " + e);
        }
    }
}
