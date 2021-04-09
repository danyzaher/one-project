import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.cli.*;
import org.apache.maven.lifecycle.internal.LifecycleDependencyResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


import static java.lang.Thread.sleep;

class ServerSocketTCP implements Runnable{
	static ConnectionCrud CC = new ConnectionCrud();
	static ArrayList<Connection> connectionManager = new ArrayList<>();
	int i = 0;
	private final static Logger logger = LoggerFactory.getLogger(ServerSocketTCP.class.getName());
	static Reader reader;
	static {
		try {
			reader = Files.newBufferedReader(Paths.get(System.getenv("EPISEN_SRV_CONF")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	static ObjectMapper om = new ObjectMapper(new YAMLFactory());
	static ServerConfig sc;
	static {
		try {
			sc = om.readValue(reader, ServerConfig.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	static Datasource source = new Datasource(sc.getNboneco());
	public ServerSocketTCP() throws IOException {
	}

	public void run() {
		try {
			ServerSocket socketServer = new ServerSocket(sc.getPort());
			Socket socketClient = socketServer.accept();
			ObjectMapper mapper = new ObjectMapper();
			BufferedReader in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream())), true);
			logger.info("Connection available = " + source.size());
			if (source.size() > 0) {
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
			} else {
				logger.info("no more connections");
				out.println("no more connections");
				for (int k = 0; k < connectionManager.size(); k++) {
					source.setConnection(connectionManager.get(k));
					logger.info("add connection " + k + 1);
					sleep(1000);
				}
				connectionManager.clear();
				socketClient.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
		public static void main(String[] args) throws IOException {
		try {
			logger.info("Lancement du serveur");


		} catch (Exception throwables) {throwables.printStackTrace();}
		logger.info("END");
	}
}