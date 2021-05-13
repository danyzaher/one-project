import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;


public class AutoModeElectro {

    private final static Logger logger = LoggerFactory.getLogger(ServerSocketTCP.class.getName());

    public void brainElectroChroma(ConnectionCrud C) throws SQLException {
        while (true) {

           manualButtonFunction(C);

            try {
                Thread.sleep(1000 * 60);
                logger.info("---- UPDATE ELECTROCHROMA AUTO ----");

            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }

        }

    }
    public void manualButtonFunction(ConnectionCrud C) throws SQLException {

        ArrayList<String> id_window = C.getIdWindow();
        ArrayList<String> id_store = C.getIdStore();

        for (String id_w : id_window) {

            String T0 = "0";
            String T = "0";
            String L0 = "0";
            String L = "0";
            String A = "0";

            ArrayList<String> general = C.getGeneralTempLigInt(id_w);
            ArrayList<String> requestT = C.getLastTempInRoom(id_w);
            ArrayList<String> requestL = C.getLastLightInRoom(id_w);
            A = C.getOpacityValue(id_w);

            if (!general.isEmpty()) {
                T0 = general.get(0);
                L0 = general.get(1);
            }
            if (!requestT.isEmpty()) {
                T = requestT.get(0);
            } else {
                T = T0;
            }
            if (!requestL.isEmpty()) {
                L = requestL.get(0);
            } else {
                L = L0;
            }

            int t = Integer.parseInt(T);
            int t0 = Integer.parseInt(T0);
            int l = Integer.parseInt(L);
            int l0 = Integer.parseInt(L0);
            int a = Integer.parseInt(A);

            int value = a + (l - l0) + ((t - t0)/100);

            if (value <0) {
                value = 0;
            }
            if (value >5){
                value = 5;
            }

            C.updateOpacity(id_w, value + "");


        }

        for (String id_s : id_store) {

            String T0 = "0";
            String T = "0";
            String L0 = "0";
            String L = "0";
            String A = "0";

            ArrayList<String> general = C.getGeneralTempLigInt(id_s);
            ArrayList<String> requestT = C.getLastTempInRoom(id_s);
            ArrayList<String> requestL = C.getLastLightInRoom(id_s);
            A = C.getOpacityValue(id_s);

            if (!general.isEmpty()) {
                T0 = general.get(0);
                L0 = general.get(1);
            }

            if (!requestT.isEmpty()) {
                T = requestT.get(0);
            } else {
                T = T0;
            }
            if (!requestL.isEmpty()) {
                L = requestL.get(0);
            } else {
                L = L0;
            }

            int t = Integer.parseInt(T);
            int t0 = Integer.parseInt(T0);
            int l = Integer.parseInt(L);
            int l0 = Integer.parseInt(L0);
            int a = Integer.parseInt(A);

            int value = a - (l - l0) - ((t - t0)/100);

            if (value <0) {
                value = 0;
            }
            if (value >5){
                value = 5;
            }

            C.updateStoreHigh(id_s, value + "");


        }
    }
}