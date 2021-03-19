import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;


public class JDBCConnectionPool {
    public static Logger logger = LoggerFactory.getLogger("JDBCConnectionPool");

    public ArrayList<Connection> collection;
    public boolean isEmpty(){

        return collection.isEmpty();
    }
    public JDBCConnectionPool(int nboneco) {

        this.collection = new ArrayList<>();
        addConnections(nboneco);
    }


    public void addConnections(int nboneco)
    {

        Connection oneco;

        for (int i = 0; i < nboneco; i++) {

            try {Class.forName("org.postgresql.Driver");

                oneco = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testone","maxime","admin");

                collection.add(oneco);
                logger.info("Connection available = "+collection.size());

            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public Connection getConnection(){

        if (collection.isEmpty()){
        logger.info("No more connection !!!");
        return null;
    } else {
        Connection oneco = collection.get(0);
        collection.remove(0);
        logger.info("Connection available = "+collection.size());
        return oneco;
    }}


    public void endConnections()
    {

        for (int i=0; i < collection.size(); i++) {
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