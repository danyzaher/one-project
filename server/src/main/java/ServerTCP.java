import java.net.*;
import java.io.*;
import java.sql.Connection;
import java.util.ArrayList;

class ServerTCP {
    final static int port = 3333;

    public static void main(String[] args) {
        try {
            testConnectionPool tCP = new testConnectionPool();
            ArrayList<Connection> connectionManager = new ArrayList<>();
            ServerSocket socketServer = new ServerSocket(port);
            System.out.println("Lancement du serveur");
            Datasource source = new Datasource(5);
            int i = 0;
            while (true) {
                Socket socketClient = socketServer.accept();
                String message = "";
                connectionManager.add(i,source.getConnection());
                System.out.println("Connexion avec : "+socketClient.getInetAddress());

                // InputStream in = socketClient.getInputStream();
                // OutputStream out = socketClient.getOutputStream();

                BufferedReader in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
                PrintStream out = new PrintStream(socketClient.getOutputStream());
                message = in.readLine();
                out.println(message);
                tCP.addElement(connectionManager.get(i), "produit", "nom", message);
                tCP.showElement(connectionManager.get(i),"produit","nom");
                socketClient.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}