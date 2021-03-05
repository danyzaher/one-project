import org.apache.commons.cli.*;
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

    public static String showElement(Connection c , String Table, String column) throws SQLException {
        String result = null;
        String sql = "select * from \"" + Table + "\";";
        Statement smt = c.createStatement();
        ResultSet rs = smt.executeQuery(sql);
        while (rs.next()) {
            result = result + String.valueOf(rs.getArray(column)) + "\n";
        }
        return result;
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
            addElement(c1, "client", "nom_client", "yassir");
            System.out.println(showElement(c2, "client", "nom_client"));
            //eraseElement(c2, "distance", "id_distance",16);
            //showElement(c1, "distance", "risque");
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
