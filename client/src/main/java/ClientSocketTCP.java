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

        //ArrayList<Connection> connectionManager = new ArrayList<>();
        final Options options = new Options();
        //final Option maxConnection = Option.builder().longOpt("maxConnection").hasArg().build();
        //options.addOption(maxConnection);
        //final Option namecolomn = Option.builder().longOpt("namecolumn").hasArg().build();
        //options.addOption(namecolomn);
        //final Option tableName = Option.builder().longOpt("tableName").hasArg().build();
        //options.addOption(tableName);
        //final Option idvalue = Option.builder().longOpt("idvalue").hasArg().build();
        //options.addOption(idvalue);
        final Option show = Option.builder().longOpt("show").hasArg().build();
        options.addOption(show);
        //final Option create = Option.builder().longOpt("create").hasArg().build();
        //options.addOption(create);

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
            if(commandLine.hasOption("show")){
                out.println(show);
            }
            else{
                out.println("erreur de saisi");
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
