import java.net.*;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

class ServerSocketTCP {
	final static int port = 60500;

	public static void main(String[] args) {
		try {
			ConnectionCrud CC = new ConnectionCrud();
			ArrayList<Connection> connectionManager = new ArrayList<>();
			ServerSocket socketServer = new ServerSocket(port);
			System.out.println("Lancement du serveur");
			Datasource source = new Datasource(20);
			int i = 0;
			while (true) {
				Socket socketClient = socketServer.accept();
				String message = "";
				BufferedReader in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
				PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream())), true);
				if (i <= source.size()) {
					connectionManager.add(i, source.getConnection());
					CC.setC(connectionManager.get(i));
					System.out.println("Connexion avec : " + socketClient.getInetAddress());
					message = in.readLine();

					CC.addElement("produit", "nom", message);
					out.println(CC.showElement("produit"));
					socketClient.close();
					i++;
				} else {
					System.out.println("no more connections");
					out.println("no more connections");
					socketClient.close();
					break;
					}
				}
			} catch (SQLException throwables) {
			throwables.printStackTrace();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
		System.out.println("END");
	}
}