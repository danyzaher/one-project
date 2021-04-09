import com.fasterxml.jackson.databind.JsonNode;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.ArrayList;


import static java.lang.Thread.sleep;

class ServerSocketTCP {

	private final static Logger logger = LoggerFactory.getLogger(ServerSocketTCP.class.getName());

	public static void main(String[] args) throws IOException {
		Reader reader = Files.newBufferedReader(Paths.get(System.getenv("EPISEN_SRV_CONF")));
		ObjectMapper om = new ObjectMapper(new YAMLFactory());
		ServerConfig sc = om.readValue(reader, ServerConfig.class);
		try {

			ConnectionCrud CC = new ConnectionCrud();
			ArrayList<Connection> connectionManager = new ArrayList<>();
			ServerSocket socketServer = new ServerSocket(sc.getPort());
			logger.info("Lancement du serveur");

			Datasource source = new Datasource(sc.getNboneco());
			int i = 0;
			while (true) {

				Socket socketClient = socketServer.accept();
				ObjectMapper mapper = new ObjectMapper();
				BufferedReader in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
				PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream())), true);
				logger.info("Connection available = " + source.size());
				if ( source.size() > 0 ) {
					connectionManager.add(i, source.getConnection());
					CC.setC(connectionManager.get(i));
					logger.info("Connexion avec : " + socketClient.getInetAddress());
					for (String recu = in.readLine(); !recu.equals("end"); recu = in.readLine()) {
						logger.info("receiving data from client");
						JsonNode jn = mapper.readTree(recu);
						CC.addElement("produit", "nom", "prix", jn.get("nom").asText(), jn.get("prix").asInt());
					}
					out.println(CC.showElement("produit"));

					socketClient.close();
					i++;
				}

				else{
					logger.info("no more connections");
					out.println("no more connections");
					for (int k = 0; k < connectionManager.size(); k++) {
						source.setConnection(connectionManager.get(k));
						logger.info("add connection " + k+1);
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