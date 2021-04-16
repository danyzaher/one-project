import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class testConnectionPool {
    private final static Logger logger = LoggerFactory.getLogger(testConnectionPool.class.getName());

    public static void main(String[] args) throws SQLException, ParseException, InterruptedException {
        ConnectionCrud CC = new ConnectionCrud();
        ArrayList<Connection> connectionManager = new ArrayList<>();
        final Options options = new Options();
        final Option maxConnection = Option.builder().longOpt("maxConnection").hasArg().build();
        options.addOption(maxConnection);
        final Option namecolomn = Option.builder().longOpt("namecolumn").hasArg().build();
        options.addOption(namecolomn);
        final Option tableName = Option.builder().longOpt("tableName").hasArg().build();
        options.addOption(tableName);
        final Option idvalue = Option.builder().longOpt("idvalue").hasArg().build();
        options.addOption(idvalue);
        final Option show = Option.builder().longOpt("show").hasArg().build();
        options.addOption(show);
        final Option create = Option.builder().longOpt("create").hasArg().build();
        options.addOption(create);
        final Option timeOut = Option.builder().longOpt("timeOut").hasArg().build();
        options.addOption(timeOut);
        final Option delete = Option.builder().longOpt("delete").hasArg().build();
        options.addOption(delete);
        final Option update = Option.builder().longOpt("update").hasArg().build();
        options.addOption(update);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine commandLine = parser.parse(options, args);
        int iMaxConnection = 10;
        int itimeOut = 5000;
        boolean iShow = false, iCreate = false, iDelete = false, iUpdate = false;
        if (commandLine.hasOption("maxConnection"))
            iMaxConnection = Integer.parseInt(commandLine.getOptionValue("maxConnection"));
        if (commandLine.hasOption("timeOut"))
            itimeOut = Integer.parseInt(commandLine.getOptionValue("timeOut")) * 1000;
        iShow = commandLine.hasOption("show");
        iCreate = commandLine.hasOption("create");
        iDelete = commandLine.hasOption("delete");
        iUpdate = commandLine.hasOption("update");
        if (iMaxConnection > 0) {
            Datasource source = new Datasource(iMaxConnection);
            int i = 0;
            if (iShow) {
                while (!source.isEmpty()) {

                    logger.info("Number of available connections: " + source.size());
                    connectionManager.add(i, source.getConnection());
                    CC.setC(connectionManager.get(i));
                    CC.showElement("produit", "id_produit", "nom");
                    sleep(itimeOut);
                    i++;
                }
                logger.info("no more connections");
                logger.info("retrieve connection pool");
                while (!connectionManager.isEmpty()) {

                    logger.info("Number of available connections: " + source.size());

                    source.setConnection(connectionManager.get(0));
                    connectionManager.remove(0);
                    sleep(itimeOut);
                }
            }
            if (iUpdate) {
                while (!source.isEmpty()) {

                    //logger.info("Number of available connections: " + source.size());
                    connectionManager.add(i, source.getConnection());
                    CC.setC(connectionManager.get(i));
                    //updateElement(connectionManager.get(i), commandLine.getOptionValue("tableName"),commandLine.getOptionValue("idvalue"),commandLine.getOptionValue("namecolomn"), commandLine.getOptionValue("update"));

                    sleep(itimeOut);
                    i++;
                }
                logger.info("no more connections");
                logger.info("retrieve connection pool");
                while (!connectionManager.isEmpty()) {

                    logger.info("Number of available connections: " + source.size());

                    source.setConnection(connectionManager.get(0));
                    connectionManager.remove(0);
                    sleep(itimeOut);
                }
            }
            if (iCreate) {
                while (!source.isEmpty()) {

                    logger.info("Number of available connections: " + source.size());

                    connectionManager.add(i, source.getConnection());
                    CC.setC(connectionManager.get(i));
                    logger.info("adding element");
                    CC.addElement("produit", "nom", commandLine.getOptionValue("create"));
                    CC.showElement( "produit", "id_produit", "nom");
                    sleep(itimeOut);
                    i++;
                }
                logger.info("no more available connections");
                logger.info("retrieve connection pool");
                while (!connectionManager.isEmpty()) {

                    logger.info("Number of connections: " + source.size());

                    source.setConnection(connectionManager.get(0));
                    connectionManager.remove(0);
                    sleep(itimeOut);
                }
            }
            if (iDelete) {
                while (!source.isEmpty()) {

                    logger.info("Number of available connections: " + source.size());

                    connectionManager.add(i, source.getConnection());
                    CC.setC(connectionManager.get(i));
                    logger.info("deleting element");
                   CC.deleteElement("produit", "id_produit");
                   CC.showElement("produit", "id_produit", "nom");
                    sleep(itimeOut);
                    i++;
                }
                logger.info("no more available connections");
                logger.info("retrieve connection pool");
                while (!connectionManager.isEmpty()) {

                    logger.info("Number of available connections: " + source.size());

                    source.setConnection(connectionManager.get(0));
                    connectionManager.remove(0);
                    sleep(itimeOut);
                }
            }
            source.closeConnections();
        } else {
            logger.info("no connection");
            System.exit(-1);
        }
        logger.info("ending connection");
    }
}