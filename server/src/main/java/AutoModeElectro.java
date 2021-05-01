import java.sql.SQLException;
import java.util.ArrayList;

public class AutoModeElectro {

    Datasource dataSource;

    public AutoModeElectro(Datasource dataSource) {

        this.dataSource = dataSource;

    }

    public void BrainElectroChroma() throws SQLException {
        while (true) {
            if (dataSource.size() > 0) {
                ConnectionCrud C = new ConnectionCrud();
                C.setC(Datasource.getConnection());
                ArrayList<String> id_fenetre = C.getIdFenetre();
                ArrayList<String> id_store = C.getIdStore();

                for(String id_f :id_fenetre){

                    String T0 = "0";
                    String T = "0";
                    String L0 = "0";
                    String L = "0";
                    ArrayList<String> general = C.getGeneralTempLigInt(id_f);
                    ArrayList<String> requeteT = C.getLastTempInRoom(id_f);
                    ArrayList<String> requeteL = C.getLastLightInRoom(id_f);
                    if (!general.isEmpty()){
                        T0=general.get(0);
                        L0=general.get(1);
                    }
                    if (!requeteT.isEmpty()){
                        T=requeteT.get(0);
                    }
                    else{
                        T=T0;
                    }
                    if (!requeteL.isEmpty()){
                        L=requeteL.get(0);
                    }
                    else{
                        L=L0;
                    }

                    int t =Integer.parseInt(T);
                    int t0 = Integer.parseInt(T0);
                    int l =Integer.parseInt(L);
                    int l0 = Integer.parseInt(L0);

                }
            }
        }
    }
}
