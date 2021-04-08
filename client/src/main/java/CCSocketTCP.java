

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.cli.*;

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

        Reader reader = Files.newBufferedReader(Paths.get(System.getenv("EPISEN_CLIENT_CONF")));
        ObjectMapper om = new ObjectMapper(new YAMLFactory());
        SocketConfig sc = om.readValue(reader, SocketConfig.class);
        Socket socket = new Socket(sc.getIp(), sc.getPort());
        clientLog.info("SOCKET = " + socket);

        BufferedReader plec = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter pred = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);

        File file = new File(String.valueOf(Paths.get("client\\jsonformatter.json")));
        ObjectMapper obm = new ObjectMapper();

        ArrayNode nodes = (ArrayNode) obm.readTree(file).get("data");


        for (int i = 0; i < nodes.size(); i++) {
            pred.println(nodes.get(i));
        }
       pred.println("end");
        try {
            String recu = null;
            clientLog.info("Starting receiving data");
            while ((recu = plec.readLine()) != null) {
                clientLog.info(recu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        clientLog.info("END");

        plec.close();
        pred.close();
        socket.close();
    }
}
