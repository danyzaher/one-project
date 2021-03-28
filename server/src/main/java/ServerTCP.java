import java.net.*;
import java.io.*;

class ServerTCP {
    final static int port = 3333;

    public static void main(String[] args) {
        try {
            ServerSocket socketServer = new ServerSocket(port);
            System.out.println("Lancement du serveur");

            while (true) {
                Socket socketClient = socketServer.accept();
                String message = "";

                System.out.println("Connexion avec : "+socketClient.getInetAddress());

                // InputStream in = socketClient.getInputStream();
                // OutputStream out = socketClient.getOutputStream();

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socketClient.getInputStream()));
                PrintStream out = new PrintStream(socketClient.getOutputStream());
                message = in.readLine();
                out.println(message);

                socketClient.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}