import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;


public class JDBCConnectionPool {
    public static Logger logger = LoggerFactory.getLogger("JDBCConnectionPool");

        private final ArrayList<Connection> collection;
        private Connectioninfo cf;

    public JDBCConnectionPool(int nboneco) {
        cf = new Connectioninfo();
        this.collection = new ArrayList<>();
        addConnections(nboneco);
    }

    public void addConnections(int nboneco) {

        Connection oneco;

        for (int i = 0; i < nboneco; i++) {

                try {
                    Class.forName(cf.getDriver());

                    oneco = DriverManager.getConnection(cf.getUrl(), cf.getUser(), cf.getPassword());
                    collection.add(oneco);
                    logger.info("Connection available = " + collection.size());

                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        public Connection getConnection () {

            if (collection.isEmpty()) {
                logger.info("No more connection !!!");
                return null;
            } else {
                Connection oneco = collection.get(0);
                collection.remove(0);
                logger.info("Connection available = " + collection.size());
                return oneco;
            }
        }


        public void endConnections ()
        {

            for (int i = 0; i < collection.size(); i++) {
                try {
                    getConnection().close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        public void setConnection (Connection oneco){
            collection.add(oneco);
        }
        public boolean isEmpty () {
            return collection.isEmpty();
        }
        public int size () {
            return collection.size();
        }
    }
