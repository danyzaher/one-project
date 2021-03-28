import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.*;

public class ClientSocketTCP {

    protected static Logger clientLog  = LoggerFactory.getLogger("clientSocketTCP");

    public static void main(String[] args) throws IOException {
        String hostName = "localhost";
        String nom = "julien";
        Socket sock = null;
        PrintWriter out = null;
        ObjectInputStream in = null;
        try {
            sock = new Socket(hostName, 3333);
            out = new  PrintWriter(sock.getOutputStream(), true);
            in = new ObjectInputStream(sock.getInputStream());
        } catch(UnknownHostException e) {
            System.err.println("Host injoignable : " + hostName);
            System.exit(1);
        } catch(IOException ee) {
            System.err.println("Connexion impossible avec : " + hostName);
            System.exit(1);
        }
        out.println(nom);
        out.println("dany");
        Object recu;
        try {
            while ((recu = in.readObject()) != null) {

            if (recu == null) System.err.println("Erreur");
            else {
                PU pu = (PU)recu;
                System.out.println("Serveur->Client : " + pu);
            }}
        } catch(ClassNotFoundException e) {
            System.err.println("Classe inconnue : " + hostName);
            System.exit(1);
        }
        out.close();
        in.close();
        sock.close();
    }

}
