import java.net.*;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.*;

import org.apache.commons.cli.*;
import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;



import static java.lang.Thread.sleep;

public class ServerSocketTCP {

	public static Model model = new Model();
	protected static Logger serverLog = Logger.getLogger("serverLog");
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
				DataOutputStream dos = new DataOutputStream(out);

				dos.writeBytes("No more connections");
				out.close();
			}
			else {
				Connection connex = source.getConnection();
				serverLog.info("");
				connectionManager.add(i, source.getConnection());
				//showElement(connectionManager.get(i), "produit", "id_produit", "nom");
				System.out.println("Connected");
				ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
				PrintWriter in = new PrintWriter(String.valueOf(new InputStreamReader(sock.getInputStream())));

				String recu;
				//in.readLine();

				if ((recu = in.toString()) != null){
					String methode = recu.trim();
					if (methode == "show"){
						String nom = recu.trim();
						String result = model.showElement(connectionManager.get(i),nom);
						out.writeBytes(result);
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
