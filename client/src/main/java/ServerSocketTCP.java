import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.*;
import java.io.*;

public class ServerSocketTCP {

	public static Logger logger = LoggerFactory.getLogger("ServeurSocketTCP");
    public static void main(String[] args) {
	PU [] tab = {
	    new PU("dany", "POO", 18),
	    new PU("maxime", "math", 18),
	    new PU("julien", "anglais", 11)};

	ServerSocket server = null;
	try {
	    server = new ServerSocket(3333);
	    while (true) {
		Socket sock = server.accept();
		System.out.println("Connected");
		ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
		BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		String recu;
		while ((recu = in.readLine()) != null) {
		    System.out.println("recu : " + recu);
		    for(int i = 0; i < tab.length; i++)
			if (tab[i].getNom().equals(recu)) {
				out.writeObject(tab[i]);
				logger.info(recu +" "+ i);
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
