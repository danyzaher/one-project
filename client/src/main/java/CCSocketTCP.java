
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

        int port=3333;
        String hostName = "localhost"; //IP
        Socket socket = new Socket(hostName, port);
        System.out.println("SOCKET = " + socket);

        BufferedReader plec = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter pred = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);

        String str = "Armageddon";
        for (int i = 0; i < 10; i++) {
            pred.println(str);          // envoi d'un message
        }
        try {
            String recu = null;
            clientLog.debug("debut lecture du message recu");
            while ((recu = plec.readLine()) != null) {
                System.out.println(recu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("END");     // message de terminaison
        pred.println("END") ;
        plec.close();
        pred.close();
        socket.close();
    }
}