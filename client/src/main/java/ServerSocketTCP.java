import java.net.*;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.cli.*;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import static java.lang.Thread.sleep;

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
				System.out.println("No more connections, please come later dude :) ");
				ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
				out.writeBytes("No more connections");
				out.close();
			}
			else {
				Connection connex = source.getConnection();
				serverLog.info("");
				connectionManager.add(i, source.getConnection());
				//showElement(connectionManager.get(i), "produit", "id_produit", "nom");
				System.out.println("Connected");
				ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
				BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				String recu;
				//in.readLine();

				if ((recu = in.readLine()) != null){
					String methode = recu.trim();
					if (methode == "show"){
						String nom = recu.trim();
						String result = model.showElement(connectionManager.get(i),nom);
					}

				}
				while ((recu = in.readLine()) != null) {
					System.out.println("recu : " + recu);
					String methode = recu.trim();
					for (int i = 0; i < tab.length; i++)
						if (tab[i].getNom().equals(nom)) {
							out.writeObject(tab[i]);
							break;
						}
				}
				in.close();
				out.close();
			}
			sock.close();
	    }
	} catch(IOException | SQLException e) {
		try {
		    server.close();
		} catch(IOException ee) {} 
	}
    }
} 
