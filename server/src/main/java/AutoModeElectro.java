

public class AutoModeElectro {

    Datasource dataSource;

    public AutoModeElectro(Datasource dataSource) {

        this.dataSource = dataSource;

    }

    public void BrainElectroChroma() {
        while (true) {
            if (dataSource.size() > 0) {
                ConnectionCrud C = new ConnectionCrud();
                C.setC(Datasource.getConnection());
            }
        }
    }
}
