import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Coada implements Runnable{
    int timpServiceClientiExistenti = 0;
    List<Client> clienti;
    int clientiServiti = 0;
    int timpTotalServiceClienti = 0;
    String numeCoada;
    int timpSimulare;
    boolean ruleaza = true;

    Server server;

    Coada(Server server, String numeCoada, int timpSimulare) {
        timpServiceClientiExistenti = 0;
        clienti = Collections.synchronizedList(new ArrayList());
        this.server = server;
        this.numeCoada = numeCoada;
        this.timpSimulare = timpSimulare;
    }

    void adaugareClient(Client client) {
        client.setTimpAdaugareLaCoada(server.timpServer);
        this.timpServiceClientiExistenti += client.getTimpService();
        this.clienti.add(client);
        //System.out.println("Clientul " + client.getID() + " a fost adaugat la " + this.numeCoada + " <-");
        if (server.fisierLog) {
            //server.scrieInFisier("Clientul " + client.getID() + " a fost adaugat la " + this.numeCoada + " <-");
        }
    }

    void eliminaPrimulClientVenit() {
        if (clienti.size() > 0) {
            Client primulClient = clienti.get(0);
            if (primulClient.getTimpService() > 0) {
                primulClient.decrementeazaTimpService();
                if (server.fisierLog) {
                    server.scrieInFisier("Client in curs de procesare - " + this.numeCoada + ": " + primulClient.toString());
                }
            } else {
                //System.out.println("                                                    -> Clientul " + primulClient.getID() + " a fost procesat de " + this.numeCoada);
                if (server.fisierLog) {
                    //server.scrieInFisier("                                                    -> Clientul " + primulClient.getID() + " a fost procesat de " + this.numeCoada);
                }
                //Eliminam din lista curentă clientul
                this.timpServiceClientiExistenti -= primulClient.getTimpService();
                this.timpTotalServiceClienti += primulClient.getTimpService();
                clienti.remove(0);
                this.clientiServiti += 1;

                //Îl adăugăm la lista clienților serviți
                primulClient.setTimpTerminareServire(server.timpServer);
                primulClient.setServit(this.numeCoada);
                server.clientiServiti.add(primulClient);

                server.scrieInFisier("Client in curs de procesare - " + this.numeCoada + ": " + primulClient.toString());
            }
        }
    }

    void afisareClienti() {
        for (int i = 0; i < clienti.size(); i++) {
            clienti.get(i).afisare();
        }
    }

    @Override
    public void run() {
        while(ruleaza){
            try {
                this.eliminaPrimulClientVenit();
                Thread.sleep( this.timpSimulare *100);
            } catch (Exception e) {

            }
        }
    }
}
