package BussinesLayer;

public class ClientBL {
    private int ClientID;
    private String nume;

    public ClientBL(int ClientID, String nume) {
        this.ClientID = ClientID;
        this.nume = nume;
    }

    public int getClientID() {
        return ClientID;
    }

    public void setClientID(int clientID) {
        ClientID = clientID;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }
}
