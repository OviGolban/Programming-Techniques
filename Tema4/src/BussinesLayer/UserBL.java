package BussinesLayer;

import java.io.Serializable;

public class UserBL implements Serializable {
    String username;
    String password;
    String functie;

    public UserBL(String username, String password, String functie) {
        this.username = username;
        this.password = password;
        this.functie = functie;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFunctie() {
        return functie;
    }

    public void setFunctie(String functie) {
        this.functie = functie;
    }

    public String toString() {
        return "UserBL " + username + " " + password + " " + functie;
    }
}
