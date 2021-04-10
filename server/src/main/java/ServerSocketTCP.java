import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.LinkedList;


class ServerSocketTCP implements Runnable{
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
	static Socket socketClient;
	public ServerSocketTCP() {
	}
	public void analyseInputStream(Socket socket){
		try {
			logger.info("Connexion avec : " + socket.getInetAddress());
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			LinkedList<String> listMessage = new LinkedList<>();
			if (source.size() > 0) {
				ConnectionCrud C = new ConnectionCrud();
				C.setC(source.getConnection());
				logger.info("Connection available = " + source.size());
				addElementsToTable(in,C);
				listMessage.add(C.showElement("produit"));
				source.setConnection(C.getC());
			} else{
				listMessage.add("no more connection come back later");
			}
			constructOutputStream(socket,listMessage);
			socket.close();

		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}
	public void addElementsToTable(BufferedReader in, ConnectionCrud C) throws IOException, SQLException {
		logger.info("addElementsToTable");
		ObjectMapper mapper = new ObjectMapper();
		for (String recu = in.readLine(); !recu.equals("end"); recu = in.readLine()) {
			JsonNode jn = mapper.readTree(recu);
			C.addElement("produit", "nom", "prix", jn.get("nom").asText(), jn.get("prix").asInt());
		}
	}
	public void constructOutputStream(Socket socket, LinkedList<String> listMessage) throws IOException {
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
		while(!listMessage.isEmpty()){
			out.println(listMessage.poll());
		}
		logger.info("message envoyé");
	}
	public void run() {
		logger.info("new Client");
		Socket socket = socketClient;
		analyseInputStream(socket);
	}
	public static void main(String[] args) throws IOException {
		logger.info("Server is running");
		ServerSocket socketServer = new ServerSocket(sc.getPort());
		while (true){
			socketClient = socketServer.accept();
			new Thread(new ServerSocketTCP()).start();
		}
		}
}