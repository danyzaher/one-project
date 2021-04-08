import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.*;
import java.io.*;
import java.sql.Connection;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

class ServerSocketTCP {
	final static int port = 60500;
	private final static Logger logger = LoggerFactory.getLogger(ServerSocketTCP.class.getName());

	public static void main(String[] args) {
		try {
			ConnectionCrud CC = new ConnectionCrud();
			ArrayList<Connection> connectionManager = new ArrayList<>();
			ServerSocket socketServer = new ServerSocket(port);
			logger.info("Lancement du serveur");
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
					logger.info("Connexion avec : " + socketClient.getInetAddress());
					message = in.readLine();
					if (message.equals("add")){
						message =in.readLine();
					    CC.addElement("produit", "nom", message);
						out.println(CC.showElement("produit"));}
					else if (message.equals("delete")){
						message =in.readLine();
						CC.deleteElement("produit","nom");
						out.println(CC.showElement("produit"));}
					else {out.println("Error the message was "+message+" it is different from add or delete");}
					socketClient.close();
					i++;
				} else {
					logger.info("no more connections");
					out.println("no more connections");
					for (int k = 0; k<connectionManager.size(); k++) {
						source.setConnection(connectionManager.get(k));
						logger.info("add connection " + k);
						sleep(1000);
					}
					connectionManager.clear();
					socketClient.close();
					break;
					}
				}
			} catch (Exception throwables) {
			throwables.printStackTrace();
		}
		logger.info("END");
	}
}