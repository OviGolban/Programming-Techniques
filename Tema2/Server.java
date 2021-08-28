import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server implements Runnable{
    Random rand;
    List<Client> clienti;
    List<Client> clientiServiti;
    List<Coada> cozi;
    List<Thread> fireExecutie;
    int timpServer = 0;
    boolean fisierLog = false;
    FileWriter fisierLoguri;
    boolean debugConsola = false;
    FereastraConfig fereastra;
    int oraDeVarf = 0;
    int ceiMaiMultiClienti = 0;

    int numClienti, numCozi, timpSimulare, timpMin, timpMax, servMin, servMax;

    Server(int numClienti, int numCozi, int timpSimulare, int timpMin, int timpMax, int servMin, int servMax, boolean fisierLog, FereastraConfig fereastra) {
        rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        clienti = Collections.synchronizedList(new ArrayList());
        clientiServiti = Collections.synchronizedList(new ArrayList());
        cozi = Collections.synchronizedList(new ArrayList());
        fireExecutie = Collections.synchronizedList(new ArrayList());

        this.numClienti = numClienti;
        this.numCozi = numCozi;
        this.timpSimulare = timpSimulare;
        this.timpMin = timpMin;
        this.timpMax = timpMax;
        this.servMin = servMin;
        this.servMax = servMax;
        this.fisierLog = fisierLog;
        this.fereastra = fereastra;

        if (fisierLog) {
            creazaFisier();
        }

        creareClienti();

        Thread serverThread = new Thread(this);
        for (int i = 0; i < numCozi; i++) {
            Coada coada = new Coada(this, "Coada " + (i + 1), this.timpSimulare);
            cozi.add(coada);

            Thread thread = new Thread(coada);
            fireExecutie.add(thread);
            fireExecutie.get(i).start();
        }

        serverThread.start();
    }

    void creazaFisier() {
        try {
            fisierLoguri = new FileWriter("fisierLoguri.txt");
        } catch (Exception e) {
            System.out.println("Eroare creare fisier: " + e);
        }
    }

    void scrieInFisier(String s) {
        try {
            this.fisierLoguri.write(s + "\n");
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void salveazaFisier() {
        try {
            fisierLoguri.close();
        } catch (Exception e) {
            System.out.println("Eroare la salvare fisier: " + e);
        }
    }

    int numarAleatorIntreAsiB(int a, int b) {
        return rand.nextInt(b - a) + a;
    }

    void creareClienti() {
        if (this.fisierLog) {
            this.scrieInFisier("Clienti creati: ---------------------------------------");
        }
        for (int i = 0; i < numClienti; i++) {
            Client client = new Client(i + 1, this.numarAleatorIntreAsiB(timpMin, timpMax), this.numarAleatorIntreAsiB(servMin, servMax));
            if (this.fisierLog) {
                this.scrieInFisier(client.clientDetalii());
            }
            clienti.add(client);
        }

        if (this.fisierLog) {
            this.scrieInFisier("---------------------------------------------------------");
        }

        //Ordonăm clienții în funcție de timpul de sosire
        //Daca acesta este egal, sortam in functie de timpul de servire
        Collections.sort(clienti);
        afisareClienti();
    }

    void afisareClienti() {
        for (int i = 0; i < clienti.size(); i++) {
            fereastra.adaugaLaAfisaj(clienti.get(i).afisare());
        }
    }

    void adaugaClientiLaCozi() {
        for (int i = 0; i < clienti.size(); i++) {
            if (timpServer >= clienti.get(i).getTimpSosire()) {
                Client client = clienti.get(i);
                clienti.remove(i);
                //Atunci când scoatem un client din lista, pe locul (indexul lui se va fi urmatorul client)
                //Decrementam variabila i pentru a il evalua si pe acesta, astfel, nu omitem pe nimeni
                i -= 1;

                //Aici facem greedy
                int indexCoadaCuCelMaiMicInterval = 0;
                for (int j = 0; j < cozi.size(); j++) {
                    if ((cozi.get(j).timpServiceClientiExistenti + client.getTimpService()) < (cozi.get(indexCoadaCuCelMaiMicInterval).timpServiceClientiExistenti + client.getTimpService())) {
                        indexCoadaCuCelMaiMicInterval = j;
                    }
                }
                //punem clientul in coada in care are cel mai putin timp de asteptat
                cozi.get(indexCoadaCuCelMaiMicInterval).adaugareClient(client);
            }
        }
    }

    @Override
    public void run() {
        /*
            - Pentru amplasarea clienților, vom folosi metoda Greedy
            - Verificăm toate cozile, și timpul curent de așteptare
            și plasăm clientul in coada in care timpul total de așteptare
            (incluzând adăugarea clientului curent) va fi cel mai mic.
         */
        while (clientiServiti.size() < this.numClienti  && this.timpServer < this.timpSimulare) {
            try {
                this.timpServer += 1;
                fereastra.adaugaLaAfisaj("\n------- Time" + this.timpServer + " -------");
                if (this.fisierLog) {
                    this.scrieInFisier("\n------- Time" + this.timpServer + " -------");
                }
                adaugaClientiLaCozi();

                int numarClientiLaCozi = 0;
                for (int i = 0; i < fireExecutie.size(); i++) {
                    numarClientiLaCozi += cozi.get(i).clienti.size();
                    fereastra.adaugaLaAfisaj(cozi.get(i).numeCoada + " " + cozi.get(i).clienti.toString());
                    if (this.fisierLog) {
                        this.scrieInFisier("Clienti in coada " + i + ": " + cozi.get(i).clienti.toString());
                    }

                }
                //Ora de varf si numarul de clienti
                if (numarClientiLaCozi > ceiMaiMultiClienti) {
                    ceiMaiMultiClienti = numarClientiLaCozi;
                    oraDeVarf = timpServer;
                }

                Thread.sleep(this.timpSimulare * 100);
            } catch (Exception e) {

            }
        }

        for (int i = 0; i < this.cozi.size(); i++) {
            cozi.get(i).ruleaza = false;
        }
        //Calculul timpului mediu de asteptare in coada
        int sumaAsteptare = 0;
        int sumaServiceNecesar = 0;
        for (int i = 0; i < this.clientiServiti.size(); i++) {
            sumaAsteptare += clientiServiti.get(i).getTimpMediuAsteptare();
            sumaServiceNecesar += clientiServiti.get(i).getTimpOriginalService();
        }
        fereastra.adaugaLaAfisaj("Timpul mediu de asteptare al unui client este de " + (sumaAsteptare / clientiServiti.size()));
        fereastra.adaugaLaAfisaj("Timpul mediu de service necesar " + (sumaServiceNecesar / clientiServiti.size()));
        fereastra.adaugaLaAfisaj("Ora de varf a fost: " + this.oraDeVarf);
        fereastra.adaugaLaAfisaj("Numarul de clienti la aceasta ora a fost: " + this.ceiMaiMultiClienti);

        //Scriem in fisier la inchiderea programului detalii despre clientii serviti
        //fereastra.adaugaLaAfisaj("Serverul a procesat toti clientii!");
        if (fisierLog) {
            //this.scrieInFisier("Serverul si cozile au servit toti clientii");
            for (int i = 0; i < this.clientiServiti.size(); i++) {
                this.scrieInFisier("Clientul " + clientiServiti.get(i).clientDetaliiServit());
            }
            this.scrieInFisier("-------------------------- Date despre simulare -------------------------------");
            this.scrieInFisier("Timpul mediu de asteptare al unui client este de " + (sumaAsteptare / clientiServiti.size()));
            this.scrieInFisier("Timpul mediu de service necesar " + (sumaServiceNecesar / clientiServiti.size()));
            this.scrieInFisier("Ora de varf a fost: " + this.oraDeVarf);
            this.scrieInFisier("Numarul de clienti la aceasta ora a fost: " + this.ceiMaiMultiClienti);
            salveazaFisier();
        }
        this.fereastra.reactiveazaButonStart();
    }
}
