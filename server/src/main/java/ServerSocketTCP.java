import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServerSocketTCP {

	public static Model model = new Model();
	protected static Logger serverLog  = LoggerFactory.getLogger("serverSocketTCP");
	static Datasource source = new Datasource(10);
	static ArrayList<Connection> connectionManager = new ArrayList<>();

    public static void main(String[] args) throws IOException,ParseException {
		int i = 0;
		final Options options = new Options();
		final Option show = Option.builder().longOpt("show").hasArg().build();
		options.addOption(show);

		ServerSocket server = null;

	try {
	    server = new ServerSocket(3333);
	    while (true) {
		Socket sock = server.accept();

			if (source.isEmpty()) {
				serverLog.info("on est dans le if du source is empty");
				System.out.println("No more connections, please come later dude :) ");
				ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
				out.writeBytes("No more connections");
				out.close();
			}
			else {
				serverLog.info("on est dans le else");
				Connection connex = source.getConnection();
				serverLog.info("");
				connectionManager.add(i, source.getConnection());
				//showElement(connectionManager.get(i), "produit", "id_produit", "nom");
				System.out.println("Connected");

				ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
				BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				String recu;
				//in.readLine();
				serverLog.info("on est juste avant le if");
				if ((recu = in.readLine()) != null){
					String methode = recu.trim();
					serverLog.info("on est dans l'entre-if");
					if (methode == "show"){
						serverLog.info("on est dans le if 2 debut");
						String nom = recu.trim();
						String result = model.showElement(connectionManager.get(i),nom);
						out.writeBytes(result);
						serverLog.info("on est dans le if 2 fin");
					}
				}
				serverLog.info("on a fini les if");
				in.close();
				out.close();
			}
			sock.close();
	    }
	} catch(IOException | SQLException e) {
		try {
		    server.close();
		} catch(IOException ee) {
			ee.printStackTrace();
		}
	}
    }
} 
