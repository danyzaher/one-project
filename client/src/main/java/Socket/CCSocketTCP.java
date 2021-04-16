package Socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CCSocketTCP {
    protected static Logger clientLog  = LoggerFactory.getLogger("Socket.CCSocketTCP");
    public CCSocketTCP(){
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get(System.getenv("EPISEN_CLIENT_CONF")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObjectMapper om = new ObjectMapper(new YAMLFactory());
        SocketConfig sc = null;
        try {
            sc = om.readValue(reader, SocketConfig.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Socket socket = null;
        try {
            socket = new Socket(sc.getIp(), sc.getPort());
        } catch (IOException e) {
            e.printStackTrace();
        }
        clientLog.info("SOCKET = " + socket);

        BufferedReader plec = null;
        try {
            plec = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pred = null;
        try {
            pred = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        File file = new File(String.valueOf(Paths.get("client//jsonformatter.json")));
        ObjectMapper obm = new ObjectMapper();

        ArrayNode nodes = null;
        try {
            nodes = (ArrayNode) obm.readTree(file).get("data");
        } catch (IOException e) {
            e.printStackTrace();
        }


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
    }


    public static void main(String[] args) {
        CCSocketTCP ccSocketTCP = new CCSocketTCP();
    }
}

