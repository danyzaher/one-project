import java.net.*;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.cli.*;
//import org.slf4j.ILoggerFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import java.util.logging.Logger;


import static java.lang.Thread.sleep;

public class ServerSocketTCP {

	public static Model model = new Model();
	protected static Logger serverLog  = Logger.getLogger("serverSocketTCP");
	static Datasource source = new Datasource(10);
	static ArrayList<Connection> connectionManager = new ArrayList<>();

    public static void main(String[] args) throws IOException,ParseException {
		final Options options = new Options();
		int i=0;
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
				PrintWriter out = new PrintWriter(sock.getOutputStream());
				out.println("No more connections");
				out.close();
			}
			else {
				serverLog.info("on est dans le else");
				Connection connex = source.getConnection();
				connectionManager.add(i, source.getConnection());
				serverLog.info("Connected");

				PrintWriter out = new PrintWriter(sock.getOutputStream());
				BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				String recu;
				serverLog.info("on est avant le if");
				if ((recu = in.readLine()) != null){
					serverLog.info("on est dans l'entre-if "+recu);
					recu = in.readLine();

					serverLog.info("on est dans le if 2 debut"+recu);
					String result = model.showElement(connectionManager.get(i),recu);
					serverLog.info(result+recu);
				System.out.print(result);

					serverLog.info("on est dans le if 2 fin");
					out.println(result);
				}
				else {
					serverLog.info("Rien recu");
				}
				serverLog.info("on a fini les if");

			//	in.close();
			//	serverLog.info("On a ferme le in");
			//	out.close();
			//	serverLog.info("On a ferme le out");
			}
		//sock.close();
		serverLog.info("On a ferme la socket");
	    }
	} catch(IOException | SQLException e) {
		e.printStackTrace();
		try {
		    server.close();
		} catch(IOException ee) {
			ee.printStackTrace();} 
	}
    }
} 

