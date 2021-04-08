import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Thread.sleep;

class ServerSocketTCP {
	final static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	static int port;
	static int nboneco;
	private final static Logger logger = LoggerFactory.getLogger(ServerSocketTCP.class.getName());
	static Datasource source;
	public static void main(String[] args) throws IOException {
		Reader reader = Files.newBufferedReader(Paths.get(System.getenv("EPISEN_SRV_CONF")));
		HashMap<String, Integer> map = gson.fromJson(reader, HashMap.class);
		port = map.get(port);
		nboneco = map.get(nboneco);
		try {

			final Options options = new Options();
			final Option nombre = Option.builder().longOpt("nboneco").hasArg().build();
			final CommandLineParser parser = new DefaultParser();
			final CommandLine commandLine = parser.parse(options, args);
			options.addOption(nombre);
			ConnectionCrud CC = new ConnectionCrud();
			ArrayList<Connection> connectionManager = new ArrayList<>();
			ServerSocket socketServer = new ServerSocket(port);
			logger.info("Lancement du serveur");
			if (commandLine.hasOption("nboneco"))
				source = new Datasource(Integer.parseInt(commandLine.getOptionValue("nboneco")));
			else
				source = new Datasource((nboneco));
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

					if (message.equals("add")) {
						message = in.readLine();
						CC.addElement("produit", "nom", message);
						out.println(CC.showElement("produit"));

					} else if (message.equals("delete")) {
						CC.deleteElement("produit", "nom");
						out.println(CC.showElement("produit"));

					} else if (message.equals("show")) {

						out.println(CC.showElement("produit"));
					} else {
						out.println("Error the message was " + message + " it is different from add or delete");
					}
					socketClient.close();
					i++;
				}

				else{
					logger.info("no more connections");
					out.println("no more connections");
					for (int k = 0; k < connectionManager.size(); k++) {
						source.setConnection(connectionManager.get(k));
						logger.info("add connection " + k);
						sleep(1000);
					}
					connectionManager.clear();
					socketClient.close();
					break;
				}
			}

		} catch (Exception throwables) {throwables.printStackTrace();}

		logger.info("END");
	}
}