import org.apache.commons.cli.*;
import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;

public class testConnectionPool  {
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

    public static void showElement(Connection c , String Table, String column) throws SQLException {
        String sql = "select * from \"" + Table + "\";";
        Statement smt = c.createStatement();
        ResultSet rs = smt.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getArray(column)) ;
        }
    }
    public static void main(String[] args) throws SQLException, ParseException  {
        final Options options = new Options();

        final Option maxConnection = Option.builder().longOpt("maxConnection").hasArg().build();
        options.addOption(maxConnection);

        final CommandLineParser parser = new DefaultParser();
        final CommandLine commandLine = parser.parse(options, args);

        int iMaxConnection = 10;
        if (commandLine.hasOption("maxConnection"))
            iMaxConnection = Integer.parseInt(commandLine.getOptionValue("maxConnection"));

        if (iMaxConnection>0) {
            Datasource source = new Datasource(iMaxConnection);
            Connection c1 =  source.getConnection();
            Connection c2 =  source.getConnection();
            addElement(c1, "distance", "risque", "meteo");
            showElement(c2, "distance", "risque");
            eraseElement(c2, "distance", "id_distance",16);
            showElement(c1, "distance", "risque");
            source.setConnection(c1);
            source.setConnection(c2);
            source.closeConnections();
            logger.info("Fin de connexion");
        } else {
            logger.info("Aucune connexion");
            System.exit(-1);
        }

    }
}
