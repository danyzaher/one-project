import java.sql.SQLException;
import java.util.ArrayList;


public class AutoModeElectro {

    public void BrainElectroChroma(ConnectionCrud C) throws SQLException {
        while (true) {

           ManualButtonFunction(C);

            try {
                Thread.sleep(1000 * 60);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }

        }

    }
    public void ManualButtonFunction(ConnectionCrud C) throws SQLException {
        ArrayList<String> id_fenetre = C.getIdFenetre();
        ArrayList<String> id_store = C.getIdStore();

        for (String id_f : id_fenetre) {

            String T0 = "0";
            String T = "0";
            String L0 = "0";
            String L = "0";
            String A = "0";

            ArrayList<String> general = C.getGeneralTempLigInt(id_f);
            ArrayList<String> requeteT = C.getLastTempInRoom(id_f);
            ArrayList<String> requeteL = C.getLastLightInRoom(id_f);
            A = C.getOpacityValue(id_f);

            if (!general.isEmpty()) {
                T0 = general.get(0);
                L0 = general.get(1);
            }
            if (!requeteT.isEmpty()) {
                T = requeteT.get(0);
            } else {
                T = T0;
            }
            if (!requeteL.isEmpty()) {
                L = requeteL.get(0);
            } else {
                L = L0;
            }

            int t = Integer.parseInt(T);
            int t0 = Integer.parseInt(T0);
            int l = Integer.parseInt(L);
            int l0 = Integer.parseInt(L0);
            int a = Integer.parseInt(A);

            int value = a + (l - l0) + (t - t0); //to retake
            if (value <0) {
                value = 0;
            }
            if (value >5){
                value = 5;
            }
            C.updateOpacity(id_f, value + "");

        }

        for (String id_s : id_store) {

            String T0 = "0";
            String T = "0";
            String L0 = "0";
            String L = "0";
            String A = "0";

            ArrayList<String> general = C.getGeneralTempLigInt(id_s);
            ArrayList<String> requeteT = C.getLastTempInRoom(id_s);
            ArrayList<String> requeteL = C.getLastLightInRoom(id_s);
            A = C.getOpacityValue(id_s);

            if (!general.isEmpty()) {
                T0 = general.get(0);
                L0 = general.get(1);
            }

            if (!requeteT.isEmpty()) {
                T = requeteT.get(0);
            } else {
                T = T0;
            }
            if (!requeteL.isEmpty()) {
                L = requeteL.get(0);
            } else {
                L = L0;
            }

            int t = Integer.parseInt(T);
            int t0 = Integer.parseInt(T0);
            int l = Integer.parseInt(L);
            int l0 = Integer.parseInt(L0);
            int a = Integer.parseInt(A);

            int value = a + (l - l0) + (t - t0);
            if (value <0){
                value = 0;
            if (value >5){
                value = 5;
            }
            }//to retake
            C.updateStoreHigh(id_s, value + "");

        }
    }
}