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


class ServerSocketTCP implements Runnable {
	private final static Logger logger = LoggerFactory.getLogger(ServerSocketTCP.class.getName());
	private static Reader reader;

	static {
		try {
			reader = Files.newBufferedReader(Paths.get(System.getenv("EPISEN_SRV_CONF")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static ObjectMapper om = new ObjectMapper(new YAMLFactory());
	private static ServerConfig sc;

	static {
		try {
			sc = om.readValue(reader, ServerConfig.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Datasource source;

	static {
		source = new Datasource(sc.getNboneco());
	}

	private static Socket socketClient;
	private static AutoModeElectro autoModeElectro = new AutoModeElectro();
	private static int compt = 1; //Automatic button

	public ServerSocketTCP() {
	}
	private void analyseInputStream(Socket socket){
		try {
			logger.info("Connexion avec : " + socket.getInetAddress());
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			LinkedList<String> listMessage = new LinkedList<>();
			if (Datasource.size() > 0) {
				ConnectionCrud C = new ConnectionCrud();
				C.setC(Datasource.getConnection());
				logger.info("Connection available = " + Datasource.size());
				String received = in.readLine();
				logger.info("received = "+received);
				if (received.equals("automatic") && compt==1){
					compt+=1;
					autoModeElectro.brainElectroChroma(C);
				}
				if (received.equals("show")) {
					received = in.readLine();
					if (received.equals("room number")) {
						logger.info("room number");
						listMessage.add(C.getRoomNumber());
					}
					if(received.equals("water Max consumption rate")){
						logger.info("W consumption rate");
						listMessage.add(C.getMax_W_Consumption());
					}
					if(received.equals("water Min consumption rate")) {
						logger.info("W consumption rate");
						listMessage.add(C.getMin_w_Consumption());
					}
					if(received.equals("electricity Min consumption rate")) {
						logger.info("E consumption rate");
						listMessage.add(C.getMin_E_Consumption());
					}
					if (received.equals("company")){
						received = in.readLine();
						if (received.equals("name")){
							logger.info("last if");
							listMessage.add(C.getCompanyName());
						}
						if(received.equals("id")) {
							listMessage.add(C.getCompanyId(in.readLine()));
						}
						if(received.equals("type")) {
							listMessage.add(C.getRoomType(in.readLine()));
						}
					}
					if (received.equals("size")){
						listMessage.add(C.getSizeRoom(in.readLine()));
					}
					if (received.equals("menu")){
						listMessage.add(C.getMenu(in.readLine()));
					}
					if (received.equals("equipment")){
						String s = in.readLine();
						if (s.equals("available")){
							listMessage.add(C.getEquipmentAvailable(in.readLine()));
						}
						if(s.equals("animated")){
							listMessage.add(C.getAvailableEquipment(in.readLine()));
						}
						if(s.equals("inRoom")){
						listMessage.add(C.getEquipment(in.readLine()));}
					}
					if(received.equals("sensor")){
						String s = in.readLine();
						if (s.equals("available")){
							listMessage.add(C.getSensorAvailable(in.readLine()));
						}
						if(s.equals("animated")){
							listMessage.add(C.getAvailableSensor(in.readLine()));
						}
						if(s.equals("inRoom")){
							listMessage.add(C.getSensor(in.readLine()));}
					}
					if(received.equals("opacity")){
						listMessage.add(C.getOpacityValue(in.readLine()));
					}
					if(received.equals("strhigh")){
						listMessage.add(C.getStoreHighValue(in.readLine()));
					}
					if(received.equals("temperatureext")){
						listMessage.add(C.getTempExt());
					}
					if(received.equals("lightint")){
						listMessage.add(C.getLightInt(in.readLine()));
					}
					if(received.equals("temperatureint")){
						listMessage.add(C.getTempInt(in.readLine()));
					}
					if(received.equals("ligautoparams")){
						listMessage.add(C.getAutoLigParameters(in.readLine()));
					}
					if(received.equals("tempautoparams")){
						listMessage.add(C.getAutoTempParameters(in.readLine()));
						logger.info("IN TEMPAUTOPARAMS");
					}
					if(received.equals("emplacement")){
						received = in.readLine();
						String s = in.readLine();
						if (received.equals("equipment")){
						listMessage.add(C.getPlaceEquip(in.readLine(),s));}
						if (received.equals("sensor")){
							listMessage.add(C.getPlaceSensor(in.readLine(),s));
						}
					}
					if(received.equals("room")) {
						received = in.readLine();
						if(received.equals("name")) {
							listMessage.add(C.getRoomName(in.readLine()));
						}
						if(received.equals("capacity")) {
							listMessage.add(C.getCapacityInOrder());
						}
						if(received.equals("id")) {
							listMessage.add(C.getRoomInOrder());

						}
						if(received.equals("price")) {
							listMessage.add(C.getPrice(in.readLine(), in.readLine()));
						}
					}
				}
				if(received.equals("update")){
					update(in,C);
					listMessage.add("update element");
				}
				if(received.equals("delete")){
					delete(in,C);
					listMessage.add("element deleted");
				}
				if(received.equals("insert")){
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
		String received = in.readLine();
		if(received.equals("be_present")){
			String s = in.readLine();
			if(s.equals("equipment")){
				s = in.readLine();
			c.insertBePresentEquipment(s,in.readLine());}
			if(s.equals("sensor")){
				s = in.readLine();
				c.insertBePresentSensor(s,in.readLine());
			}
		}
		if(received.equals("location")) {
			c.insertLocation(in.readLine(),in.readLine(),in.readLine());
		}
	}

	private synchronized void delete(BufferedReader in, ConnectionCrud C) throws IOException, SQLException {
		logger.info("delete");
		String recu = in.readLine();
		if(recu.equals("be_present")){
			recu = in.readLine();
			if(recu.equals("equipment")){
			    C.deleteBePresentEquipment(in.readLine());
			}
			if(recu.equals("sensor")){
				C.deleteBePresentSensor(in.readLine());
			}
		}

	}

	private synchronized void update(BufferedReader in, ConnectionCrud C) throws IOException, SQLException {
		logger.info(" in update");
		String received = in.readLine();

		if(received.equals("manualmode")){
			C.PassToManualMode(in.readLine());
		}
		if(received.equals("opacity")){
			String s = in.readLine(); //id
			C.updateOpacity(s,in.readLine());//in.readLine() --> opacity by the connection crud
		}
		if(received.equals("strhigh")){
			String s = in.readLine(); //id
			C.updateStoreHigh(s,in.readLine());//in.readLine() --> strhigh by the connection crud
		}
		if(received.equals("location")) {
			C.setTaken(in.readLine(),in.readLine(),in.readLine());
		}
		if(received.equals("parameters")) {
			String s1 = in.readLine();
			String s2 = in.readLine();
			String s3 = in.readLine();

			C.updateGeneralTempInt(s1,s3);
			C.updateGeneralLigInt(s2,s3);
		}
		if(received.equals("be_present")){
			String s = in.readLine();
			if(s.equals("equipment")){
				C.updateBePresentEquip(in.readLine(),in.readLine());
			}
			if(s.equals("sensor")){
				C.updateBePresentSensor(in.readLine(),in.readLine());
			}
		}

	}

	private void constructOutputStream(Socket socket, LinkedList<String> listMessage) throws IOException, InterruptedException {
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
		while(!listMessage.isEmpty()){
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
		logger.info("Server is running...");
		ServerSocket socketServer = new ServerSocket(sc.getPort());
		while (true){

			socketClient = socketServer.accept();
			Thread thread = new Thread(new ServerSocketTCP());
			thread.start();

		}
	}
}