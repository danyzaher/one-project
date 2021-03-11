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

    public static void eraseElement(Connection c, String Table, String idcolumn, String id) throws SQLException {
        String sql = "DELETE FROM \"" + Table + "\" WHERE " + idcolumn + " = " + id + ";";
        Statement smt = c.createStatement();
        smt.executeUpdate(sql);
    }
    public static void deleteElement(Connection c, String Table, String idcolumn) throws SQLException {
        String sql = "delete from \"" + Table + "\" where " + idcolumn + " = (select max(" + idcolumn + ") from \"" + Table + "\");";
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
    public static void updateElement(Connection c , String Table, String idcolumn,
                                     String namecolumn,String newValue) throws SQLException {
        String sql = "update \"" + Table + "\"set \"" + namecolumn + "\" = \"" + newValue
                + "\" where id_produit = \""+ idcolumn + "\";";
        Statement smt = c.createStatement();
        ResultSet rs = smt.executeQuery(sql);
        String sql2 = "select * from \"" + Table + "\" where id_produit = \""+ idcolumn + "\";";
        Statement smt2 = c.createStatement();
        ResultSet rs2 = smt2.executeQuery(sql2);
        while (rs2.next()) {
            System.out.println(rs.getArray(idcolumn) + "   " + rs.getArray(namecolumn)) ;
        }
        System.out.println();

    }
    public static void main(String[] args) throws SQLException, ParseException, InterruptedException {
        ArrayList<Connection> connectionManager = new ArrayList<>();
        final Options options = new Options();
        final Option maxConnection = Option.builder().longOpt("maxConnection").hasArg().build();
        options.addOption(maxConnection);
        final Option namecolomn = Option.builder().longOpt("namecolumn").hasArg().build();
        options.addOption(namecolomn);
        final Option tableName = Option.builder().longOpt("tableName").hasArg().build();
        options.addOption(tableName);
        final Option idcolomn = Option.builder().longOpt("idcolomn").hasArg().build();
        options.addOption(idcolomn);
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
            itimeOut = Integer.parseInt(commandLine.getOptionValue("timeOut"))*1000;
        iShow = commandLine.hasOption("show");
        iCreate = commandLine.hasOption("create");
        iDelete = commandLine.hasOption("delete");
        iUpdate = commandLine.hasOption("update");
        if (iMaxConnection>0) {
            Datasource source = new Datasource(iMaxConnection);
            int i = 0;
            if (iShow) {
                while (!source.isEmpty()) {
                    logger.info("Number of available connections: " + source.size());
                    connectionManager.add(i, source.getConnection());
                    showElement(connectionManager.get(i), "produit", "id_produit", "nom");
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
                    logger.info("Number of available connections: " + source.size());
                    connectionManager.add(i, source.getConnection());
                    updateElement(connectionManager.get(i),
                            commandLine.getOptionValue("tableName"),commandLine.getOptionValue("idcolomn"),commandLine.getOptionValue("namecolomn"), commandLine.getOptionValue("update"));
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
                    logger.info("adding element");
                    addElement(connectionManager.get(i), "produit", "nom", commandLine.getOptionValue("create"));
                    showElement(connectionManager.get(i), "produit", "id_produit", "nom");
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
                    logger.info("deleting element");
                    deleteElement(connectionManager.get(i), "produit", "id_produit");
                    showElement(connectionManager.get(i), "produit", "id_produit", "nom");
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
