
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CCSocketTCP {
    protected static Logger clientLog  = LoggerFactory.getLogger("CCSocketTCP");
    public static void main(String[] args) throws Exception {
        Reader reader = Files.newBufferedReader(Paths.get("client\\socketConnection.yaml"));
        ObjectMapper om = new ObjectMapper(new YAMLFactory());
        SocketConfig sc = om.readValue(reader, SocketConfig.class);

        Socket socket = new Socket(sc.getHost(), sc.getPort());
        System.out.println("SOCKET = " + socket);

        BufferedReader plec = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter pred = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);

        String str = "Armageddon";
        for (int i = 0; i < 10; i++) {
            pred.println(str);          // envoi d'un message
        }
        try {
            String recu = null;
            clientLog.debug("debut lecture du message recu");
            while ((recu = plec.readLine()) != null) {
                System.out.println(recu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("END");     // message de terminaison
        plec.close();
        pred.close();
        socket.close();
    }
}