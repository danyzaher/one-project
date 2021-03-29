import java.net.*;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

class ServerTCP {
    final static int port = 3333;

    public static void main(String[] args) {
        try {
            ArrayList<Connection> connectionManager = new ArrayList<>();
            ServerSocket socketServer = new ServerSocket(port);
            System.out.println("Lancement du serveur");
            Datasource source = new Datasource(5);
            int i = 0;
            while (true) {
                Socket socketClient = socketServer.accept();
                String message = "";
                connectionManager.add(i,source.getConnection());
                System.out.println("Connexion avec : "+socketClient.getInetAddress());

                // InputStream in = socketClient.getInputStream();
                // OutputStream out = socketClient.getOutputStream();

                BufferedReader in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
                PrintStream out = new PrintStream(socketClient.getOutputStream());
                message = in.readLine();
                addElement(connectionManager.get(i), "produit", "nom", message);
                out.println(showElement(connectionManager.get(i),"produit","nom"));
                socketClient.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
}