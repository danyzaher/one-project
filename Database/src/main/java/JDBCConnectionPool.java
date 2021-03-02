import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;


public class JDBCConnectionPool {

    private ArrayList<Connection> collection;

    private Connectioninfo connectioninfo;

    public JDBCConnectionPool(int nboneco) {

        this.collection = new ArrayList<>();
        this.connectioninfo = new Connectioninfo();
        addConnections(nboneco);
    }


    public void addConnections(int nboneco)
    {

        Connection oneco;

        for (int i = 0; i < nboneco; i++) {

            try {Class.forName(connectioninfo.getDriver());

                oneco = DriverManager.getConnection(connectioninfo.getUrl(),connectioninfo.getUser(), connectioninfo.getPassword());

                collection.add(oneco);

            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public Connection getConnection()
    {
        Connection oneco = collection.get(0);
        collection.remove(0);
        return oneco;
    }


    public void endConnections()
    {

        for (int i=0; i <= collection.size(); i++) {
            try {
                getConnection().close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    public void setConnection(Connection oneco) {
        collection.add(oneco);
    }
}