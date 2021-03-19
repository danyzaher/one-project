import java.net.*;
import java.io.*;

import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

public class ServerSocketTCP {
	DataSource source = new DataSource(10);


    public static void main(String[] args) throws IOException,ParseException {

		final Options options = new Options();
		final Option show = Option.builder().longOpt("show").hasArg().build();
		options.addOption(show);

		ServerSocket server = null;

	try {
	    server = new ServerSocket(3333);
	    while (true) {
		Socket sock = server.accept();
			if (source.isEmpty()) {

			}
		System.out.println("Connected");
		ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
		BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		String recu;
		while ((recu = in.readLine()) != null) {
		    System.out.println("recu : " + recu);
		    String nom = recu.trim();
		    for(int i = 0; i < tab.length; i++)
			if (tab[i].getNom().equals(nom)) {
				out.writeObject(tab[i]);
				break;
				}	
		}
		out.close();
		sock.close();
		in.close();
	    }
	} catch(IOException e) {
		try {
		    server.close();
		} catch(IOException ee) {} 
	}
    }
} 
