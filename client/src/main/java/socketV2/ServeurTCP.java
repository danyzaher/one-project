package socketV2;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.net.ServerSocket;
import java.net.Socket;


public class ServeurTCP extends Thread{
    static Logger logger = LoggerFactory.getLogger("serveur TCP");
    final static int PORT = 3333;
    private Socket socket;
    public static Datasource datasource = new Datasource(10);
    
    public static void main(String[] args) {
        try {
            ServerSocket socketServeur = new ServerSocket(PORT);
            logger.info("Lancement du serveur");
            while (true) {
                Socket socketClient = socketServeur.accept();
                ServeurTCP t = new ServeurTCP(socketClient);
                logger.info("un nouveau client recu");
                t.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ServeurTCP(Socket socket) {
        this.socket = socket;
    }
    public void run() {
        try{
            String message = "";
            logger.info("debut operation" + socket.getInetAddress());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
