import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientSocketTCP {

    protected static Logger clientLog  = LoggerFactory.getLogger("clientSocketTCP");

    public static void main(String[] args) throws IOException, SQLException, ParseException, InterruptedException {

        final Options options = new Options();
        final Option show = Option.builder().longOpt("show").hasArg().build();
        options.addOption(show);

        final Option variable = Option.builder().longOpt("variable").hasArg().build();
        options.addOption(variable);

        final CommandLineParser parser = new DefaultParser();
        final CommandLine commandLine = parser.parse(options, args);

        String hostName = "172.31.254.86"; //IP
        //String nom = "julien";

        Socket sock = null;
        PrintWriter out = null;
        ObjectInputStream in = null;

        try {
            sock = new Socket(hostName, 3333); //A verifier pour port
            out = new  PrintWriter(sock.getOutputStream(), true); //envoie
            in = new ObjectInputStream(sock.getInputStream()); //reception

            if (commandLine.hasOption("show") && commandLine.hasOption("variable")) {
                out.println("show");
                out.println(commandLine.getOptionValue("variable"));
            }
            else{
                out.println("erreur de saisie");
            }


        } catch(UnknownHostException e) {
            System.err.println("Host injoignable : " + hostName);
            System.exit(1);

        } catch(IOException ee) {
            System.err.println("Connexion impossible avec : " + hostName);
            System.exit(1);
        }

        try {
            Object recu = in.readObject();
            if (recu == null) System.err.println("Erreur");
            else {
                PU pu = (PU)recu;
                System.out.println("Serveur->Client : " + pu);
            }
        } catch(ClassNotFoundException e) {
            System.err.println("Classe inconnue : " + hostName);
            System.exit(1);
        }
        out.close();
        in.close();
        sock.close();
    }

}
