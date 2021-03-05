import java.sql.*;
import java.util.ArrayList;


public class JDBCConnectionPool {

    private ArrayList<Connection> collection;

    private Connection connectioninfo;

    public JDBCConnectionPool(int nboneco) {

        this.collection = new ArrayList<>();
        addConnections(nboneco);
    }


    public void addConnections(int nboneco)
    {

        Connection oneco;

        for (int i = 0; i < nboneco; i++) {

            try {Class.forName("org.postgresql.Driver");

                oneco = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testone","dany","123");

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