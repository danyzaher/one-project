import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class testConnectionPool {
    private final static Logger logger = LoggerFactory.getLogger(testConnectionPool.class.getName());
    public static void main(String[] args) throws SQLException, ParseException {
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
            Connection c1 = source.getConnection(), c2 = source.getConnection();
            String sql1 = "select * from \"distance\"";
            Statement smt1 = c1.createStatement();
            ResultSet rs1 = smt1.executeQuery(sql1);
            String sql2 = "select * from \"distance\"";
            Statement smt2 = c2.createStatement();
            ResultSet rs2 = smt2.executeQuery(sql2);
            while (rs1.next()) {
                logger.info(String.valueOf(rs1.getArray("risque")));
            }
            while (rs2.next()) {
                logger.info(String.valueOf(rs2.getArray("id_distance")));
            }
            source.setConnection(c1);source.setConnection(c2);
            source.closeConnections();
            logger.info("Fin de connexion");
        } else {
            throw new ArithmeticException("Aucune connexion");
        }
    }
}
