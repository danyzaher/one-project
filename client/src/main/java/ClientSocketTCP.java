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
        BufferedReader br = null;
        PrintWriter pw = null;
        try {
            clientLog.debug("On rentre dans le premier try");
            sock = new Socket(hostName, 3333); //A verifier pour port
            br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            out = new  PrintWriter(sock.getOutputStream(), true); //envoie
            in = new ObjectInputStream(sock.getInputStream()); //reception
            pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sock.getOutputStream())),true);
            if (commandLine.hasOption("show") && commandLine.hasOption("variable")) {
                clientLog.debug("on est dans le premier if");
                out.println("show");
                out.println(commandLine.getOptionValue("variable"));
            }
            else{
                clientLog.debug("on est dans le premier else");
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
            String recu = null;
            clientLog.debug("on rentre dans le deuxieme try");
            while ((recu = in.readLine()) != null) {
                clientLog.info(recu);
            }
        } finally {
            out.close();
            in.close();
            sock.close();
        }
    }
}
