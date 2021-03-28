package socketV2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ClientTCP {
    public static Logger logger = LoggerFactory.getLogger("ClientTCP");
    final static int PORT = 3333;
    static String host = "localhost";
    public static void main(String[] args){
        logger.info("begin main");
        try{
            Socket socket = new Socket(host,PORT);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream out = new PrintStream(socket.getOutputStream());
            logger.info("flux entrant et sortant créer");

            out.println("le message à envoyer");
            logger.info("message envoyé");

            String recu;
            while ((recu = in.readLine())!=null){
                logger.info(recu);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


}
