import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class testConnectionPool {
    private final static Logger logger = LoggerFactory.getLogger(testConnectionPool.class.getName());
    public static void addElement(Connection c, String Table, String column, Object value) throws SQLException {
        String sql = "INSERT INTO \"" + Table + "\"(" + column + ") " + "VALUES ('" + value + "');";
        Statement smt = c.createStatement();
        smt.executeUpdate(sql);
    }

    public static void eraseElement(Connection c, String Table, String idcolumn, int id) throws SQLException {
        String sql = "DELETE FROM \"" + Table + "\" WHERE " + idcolumn + " = " + id + ";";
        Statement smt = c.createStatement();
        smt.executeUpdate(sql);
    }

    public static void showElement(Connection c , String Table, String idcolumn, String columna) throws SQLException {
        String sql = "select * from \"" + Table + "\";";
        Statement smt = c.createStatement();
        ResultSet rs = smt.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getArray(idcolumn) + "   " + rs.getArray(columna)) ;
        }
        System.out.println();
    }
    public static void main(String[] args) throws SQLException, ParseException, InterruptedException {
        ArrayList<Connection> connectionManager = new ArrayList<>();
        final Options options = new Options();
        final Option maxConnection = Option.builder().longOpt("maxConnection").hasArg().build();
        options.addOption(maxConnection);
        final Option timeOut = Option.builder().longOpt("timeOut").hasArg().build();
        options.addOption(timeOut);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine commandLine = parser.parse(options, args);
        int iMaxConnection = 10;
        int itimeOut = 5000;
        if (commandLine.hasOption("maxConnection"))
            iMaxConnection = Integer.parseInt(commandLine.getOptionValue("maxConnection"));
        if (commandLine.hasOption("timeOut"))
            itimeOut = Integer.parseInt(commandLine.getOptionValue("timeOut"))*1000;
        if (iMaxConnection>0) {
            Datasource source = new Datasource(iMaxConnection);
            int i = 0;
            while (!source.isEmpty()) {
                connectionManager.add(i, source.getConnection());
                showElement(connectionManager.get(i), "distance", "id_distance", "risque");
                sleep(itimeOut);
                i++;
            }
            logger.info("retrieve connection pool");
            while (!connectionManager.isEmpty()) {
                source.setConnection(connectionManager.get(0));
                connectionManager.remove(0);
            }
            source.closeConnections();
        } else {
            logger.info("Aucune connexion");
            System.exit(-1);
        }
        logger.info("Fin de connexion");
    }
}
