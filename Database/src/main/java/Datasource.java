import java.sql.Connection;

public class Datasource {
    private static JDBCConnectionPool physical_co;
    public Datasource(int nboneco) { physical_co = new JDBCConnectionPool(nboneco); }
    public static Connection getConnection() {
        return physical_co.getConnection();
    }

    public static void setConnection(Connection c) {
        physical_co.setConnection(c);
    }

    public static void closeConnections() {
        physical_co.endConnections();
    }

    public static boolean isEmpty() { return physical_co.isEmpty(); }

    public int size() { return physical_co.size(); }
}
