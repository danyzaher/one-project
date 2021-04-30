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
				C.setC(Datasource.getConnection());
				logger.info("Connection available = " + source.size());
				String recu = in.readLine();
				logger.info("recu = "+recu);
				if (recu.equals("show")){
					recu = in.readLine();
					if (recu.equals("company")){
						recu = in.readLine();
						if (recu.equals("name")){
							logger.info("last if");
							listMessage.add(C.getCompanyName());
						}
						if (recu.equals("id")) {
							listMessage.add(C.getCompanyId(in.readLine()));
						}
					}
					if (recu.equals("size")){
						listMessage.add(C.getSizeRoom(in.readLine()));
					}
					if (recu.equals("menu")){
						listMessage.add(C.getMenu(in.readLine()));
					}
					if (recu.equals("equipement")){
						String s = in.readLine();
						if (s.equals("available")){
							listMessage.add(C.getEquipementAvailable(in.readLine()));
						}
						if(s.equals("etat")){
							listMessage.add(C.getEtatEquipement(in.readLine()));
						}
						if(s.equals("dansSalle")){
						listMessage.add(C.getEquipement(in.readLine()));}
					}
					if(recu.equals("sensor")){
						String s = in.readLine();
						if (s.equals("available")){
							listMessage.add(C.getSensorAvailable(in.readLine()));
						}
						if(s.equals("etat")){
							listMessage.add(C.getEtatSensor(in.readLine()));
						}
						if(s.equals("dansSalle")){
							listMessage.add(C.getSensor(in.readLine()));}
					}
					if(recu.equals("opacity")){
						listMessage.add(C.getOpacityValue(in.readLine()));
					}
					if(recu.equals("strhigh")){
						listMessage.add(C.getStoreHighValue(in.readLine()));
					}
					if(recu.equals("temperatureext")){
						listMessage.add(C.getTempExt(in.readLine()));
					}
					if(recu.equals("lightint")){
						listMessage.add(C.getLightInt(in.readLine()));
					}
					if(recu.equals("temperatureint")){
						listMessage.add(C.getTempInt(in.readLine()));
					}
					if(recu.equals("temperatureGint")){
						listMessage.add(C.getGeneralTempInt(in.readLine()));
					}
					if(recu.equals("emplacement")){
						recu = in.readLine();
						String s = in.readLine();
						if (recu.equals("equipement")){
						listMessage.add(C.getPlaceEquip(in.readLine(),s));}
						if (recu.equals("sensor")){
							listMessage.add(C.getPlaceSensor(in.readLine(),s));
						}
					}
					if(recu.equals("room")) {
						recu = in.readLine();
						if(recu.equals("id")) {
							listMessage.add(C.getRoomInOrder(in.readLine(),in.readLine()));
						}
						if(recu.equals("capacity")) {
							listMessage.add(C.getCapacityInOrder(in.readLine(),in.readLine()));
						}
						if(recu.equals("name")) {
							listMessage.add(C.getRoomName());
						}
						if(recu.equals("price")) {
							listMessage.add(C.getPrice(in.readLine(),in.readLine()));
						}
					}
				}
				if(recu.equals("update")){
					update(in,C);
					listMessage.add("update element");
				}
				if(recu.equals("delete")){
					delete(in,C);
					listMessage.add("element deleted");
				}
				if(recu.equals("insert")){
					insert(in,C);
					listMessage.add("element added");
				}
				Datasource.setConnection(C.getC());
			} else{
				listMessage.add("no more connection come back later");
			}
			constructOutputStream(socket,listMessage);
			socket.close();

		} catch (IOException | SQLException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	private synchronized void insert(BufferedReader in, ConnectionCrud c) throws IOException, SQLException {
		logger.info("insert");
		String recu = in.readLine();
		if(recu.equals("be_present")){
			String s = in.readLine();
			if(s.equals("equipement")){
				s = in.readLine();
			c.insertBePresentEquipement(s,in.readLine());}
			if(s.equals("sensor")){
				s = in.readLine();
				c.insertBePresentSensor(s,in.readLine());
			}
		}
	}

	public synchronized void delete(BufferedReader in, ConnectionCrud C) throws IOException, SQLException {
		logger.info("delete");
		String recu = in.readLine();
		if(recu.equals("be_present")){
			recu = in.readLine();
			if(recu.equals("equipement")){
			    C.deleteBePresentEquipement(in.readLine());
			}
			if(recu.equals("sensor")){
				C.deleteBePresentSensor(in.readLine());
			}
		}

	}

	public synchronized void update(BufferedReader in, ConnectionCrud C) throws IOException, SQLException {
		logger.info("update");
		String recu = in.readLine();

		if(recu.equals("opacity")){
			String s = in.readLine(); //id
			C.updateOpacity(s,in.readLine());//in.readLine() --> opacity by the connection crud
		}
		if(recu.equals("strhigh")){
			String s = in.readLine(); //id
			C.updateStoreHigh(s,in.readLine());//in.readLine() --> strhigh by the connection crud
		}
		if(recu.equals("location")) {
			C.setTaken(in.readLine(),in.readLine(),in.readLine());
		}
		if(recu.equals("be_present")){
			String s = in.readLine();
			if(s.equals("equipement")){
				C.updateBePresentEquip(in.readLine(),in.readLine());
			}
			if(s.equals("sensor")){
				C.updateBePresentSensor(in.readLine(),in.readLine());
			}
		}

	}

	public void constructOutputStream(Socket socket, LinkedList<String> listMessage) throws IOException, InterruptedException {
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
		while(!listMessage.isEmpty()){
			logger.info("+1");
			logger.info(listMessage.get(0));
			out.println(listMessage.poll());
		}
		logger.info("message envoyé");
	}
	public void run() {
		logger.info("new Client");
		Socket socket = socketClient;
		analyseInputStream(socket);
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		logger.info("Server is running");
		ServerSocket socketServer = new ServerSocket(sc.getPort());
		while (true){

			socketClient = socketServer.accept();
			Thread thread = new Thread(new ServerSocketTCP());
			thread.start();

		}
	}
}