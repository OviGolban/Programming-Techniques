public class Client implements Comparable<Client>{
    private int ID;
    private int timpSosire;
    private int timpService;
    private int timpOriginalService;
    private int timpAdaugareLaCoada;
    private int timpTerminareServire;
    private String servitDe = "";

    public Client(int ID, int tArrival, int tService){
        this.ID = ID;
        this.timpSosire = tArrival;
        this.timpService = tService;
        this.timpOriginalService = timpService;
    }

    public int getTimpOriginalService(){
        return this.timpOriginalService;
    }

    public void setServit(String servitDe){
        this.servitDe = servitDe;
    }

    public String cineLaServit(){
        return this.servitDe;
    }

    public int getTimpAdaugareLaCoada() {
        return timpAdaugareLaCoada;
    }

    public void setTimpAdaugareLaCoada(int timpAdaugareLaCoada) {
        this.timpAdaugareLaCoada = timpAdaugareLaCoada;
    }

    public int getTimpTerminareServire() {
        return timpTerminareServire;
    }

    public void setTimpTerminareServire(int timpTerminareServire) {
        this.timpTerminareServire = timpTerminareServire;
    }

    public void decrementeazaTimpService(){
        this.timpService -= 1;
    }

    public int getTimpSosire() {
        return timpSosire;
    }

    public void setTimpSosire(int timpSosire) {
        this.timpSosire = timpSosire;
    }

    public int getTimpService() {
        return timpService;
    }

    public void setTimpService(int timpService) {
        this.timpService = timpService;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Client " + ID + " " + timpSosire + " " + timpService;
    }

    String afisare(){
        return "Client " + ID + " " + timpSosire + " " + timpService;
    }

    public String clientDetalii(){
        return  "Client: " + ID + " cu timp de sosire " + this.timpSosire + " si timp de service " + this.timpService;
    }

    public String clientDetaliiServit(){
        return  "Client: " + ID + " cu timp de sosire " + this.timpSosire + " timp necesar service " + this.timpOriginalService + " - timp total service coada " + (this.timpTerminareServire - this.timpAdaugareLaCoada) + " a fost servit la tick-ul " + this.timpTerminareServire + " de coada " + this.servitDe;
    }

    public int getTimpMediuAsteptare(){
        return this.timpTerminareServire - this.timpAdaugareLaCoada;
    }

    @Override
    public int compareTo(Client client) {
        if(this.timpSosire > client.timpSosire){
            return 1;
        }else if(this.timpSosire < client.timpSosire){
            return -1;
        }else{
            if(this.timpService > client.timpService){
                return 1;
            }else if(this.timpService < client.timpService){
                return -1;
            }else{
                return 0;
            }
        }
    }
}
