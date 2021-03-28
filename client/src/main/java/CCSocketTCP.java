
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.*;

public class CCSocketTCP {
    protected static Logger clientLog  = LoggerFactory.getLogger("CCSocketTCP");
    public static void main(String[] args) throws Exception {
        String hostName = "127.0.0.1"; //IP
        Socket socket = new Socket(hostName, 3333);
            System.out.println("SOCKET = " + socket);

        BufferedReader plec = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter pred = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);

        String str = "bonjour";
            for (int i = 0; i < 10; i++) {
            pred.println(str);          // envoi d'un message
            str = plec.readLine();      // lecture de l'écho
        }
        System.out.println("END");     // message de terminaison
        pred.println("END") ;
        plec.close();
        pred.close();
        socket.close();
    }
}

