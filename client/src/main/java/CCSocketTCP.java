

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.cli.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CCSocketTCP {
    protected static Logger clientLog  = LoggerFactory.getLogger("CCSocketTCP");
    public static void main(String[] args) throws Exception {

        Reader reader = Files.newBufferedReader(Paths.get(System.getenv("EPISEN_CLIENT_CONF")));
        ObjectMapper om = new ObjectMapper(new YAMLFactory());
        SocketConfig sc = om.readValue(reader, SocketConfig.class);
        final Options options = new Options();
        Socket socket = new Socket(sc.getIp(), sc.getPort());
        clientLog.info("SOCKET = " + socket);

        BufferedReader plec = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter pred = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
        final Option variable = Option.builder().longOpt("create").hasArg().build();
        final Option methode = Option.builder().longOpt("methode").hasArg().build();
        options.addOption(variable);
        options.addOption(methode);
        boolean iCreate;
        boolean iMethode;
        final CommandLineParser parser = new DefaultParser();
        final CommandLine commandLine = parser.parse(options, args);
        iCreate = commandLine.hasOption("create");
        iMethode = commandLine.hasOption("methode");
        String str;
        if (iCreate){
            str = commandLine.getOptionValue("create");}
        else {
            str = "Ananas";}
        if (iMethode){ pred.println(commandLine.getOptionValue("methode"));}
        pred.println(str);          // envoi d'un message

        try {
            String recu = null;
            clientLog.debug("debut lecture du message recu");
            while ((recu = plec.readLine()) != null) {
                clientLog.info(recu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        clientLog.info("END");     // message de terminaison

        plec.close();
        pred.close();
        socket.close();
    }
}
