package socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CCSocketTCPbis {
    protected static Logger clientLog  = LoggerFactory.getLogger("CCSocketTCP2");
    public ArrayList<String> result = new ArrayList<>();


    public CCSocketTCPbis(ArrayList<String> strings){
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
            assert sc != null;
            socket = new Socket(sc.getIp(), sc.getPort());
        } catch (IOException e) {
            e.printStackTrace();
        }
//        clientLog.info("SOCKET = " + socket);

        BufferedReader plec = null;
        try {
            assert socket != null;
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

        for (String string : strings) {
            assert pred != null;
            pred.println(string);
        }
        assert pred != null;
        pred.println("end");
        try {
            String received;
            clientLog.info("Starting receiving data");
            while ((received = plec.readLine()) != null) {
                if(received.equals("no more connection come back later")){
                    JOptionPane.showMessageDialog(new JPanel(), "Le serveur est momentanément indisponible. Veuillez relancer l’application. ", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                clientLog.info(received);
                result.add(received);
            }
//            result.remove(result.size()-1);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        clientLog.info("END");
    }



    }
