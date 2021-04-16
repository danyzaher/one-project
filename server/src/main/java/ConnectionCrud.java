import java.sql.*;

public class ConnectionCrud {
    private Connection c;
    public ConnectionCrud() {}

    public void setC(Connection c) {
        this.c = c;
    }

    public Connection getC() {
        return c;
    }

    public void addElement(String Table, String column, Object value) throws SQLException {
        String sql = "INSERT INTO \"" + Table + "\"(" + column + ") " + "VALUES ('" + value + "');";
        Statement smt = c.createStatement();
        smt.executeUpdate(sql);
    }
    public void addElement(String Table, String column, String column1, Object value, Object value1) throws SQLException {
        String sql = "INSERT INTO \"" + Table + "\"(" + column + "," + column1+ ") " + "VALUES ('" + value + "','" + value1 + "');";
        Statement smt = c.createStatement();
        smt.executeUpdate(sql);
    }
    public void eraseElement( String Table, String idcolumn, String id) throws SQLException {
        String sql = "DELETE FROM \"" + Table + "\" WHERE " + idcolumn + " = " + id + ";";
        Statement smt = c.createStatement();
        smt.executeUpdate(sql);
    }

    public  void deleteElement( String Table, String idcolumn) throws SQLException {
        String sql = "delete from \"" + Table + "\" where " + idcolumn + " = (select max(" + idcolumn + ") from \"" + Table + "\");";
        Statement smt = c.createStatement();
        smt.executeUpdate(sql);
    }
    public String showElement(String Table, String idcolumn, String columna) throws SQLException {
        String sql = "select * from \"" + Table + "\";";
        Statement smt = c.createStatement();
        ResultSet rs = smt.executeQuery(sql);
        String result = null;
        while (rs.next()) {
            result += rs.getArray(idcolumn) + "   " + rs.getArray(columna);
        }
        return result;
    }
    public  String showElement(String Table) throws SQLException {
        String sql = "select * from \"" + Table + "\";";
        Statement smt = c.createStatement();
        ResultSet rs = smt.executeQuery(sql);
        String result = null;
        while (rs.next()) {
            result += rs.getArray(1) + "        |               " + rs.getArray(2) + "            |       "  + rs.getArray(3) + "\n";
        }
        return result;
    }
    public String getCompanyName() throws SQLException{
        String sql = "Select name from Company;";
        Statement smt = c.createStatement();
        ResultSet rs = smt.executeQuery(sql);
        String result = "";
        while (rs.next()) {
            result += rs.getArray("name");}
        return result;
    }

    public String getWinStore() throws SQLException{
        String sql = "Select room from Company;"; // To change
        Statement smt = c.createStatement();
        ResultSet rs = smt.executeQuery(sql);
        String result = "";
        while (rs.next()) {
            result += rs.getArray("name");}
        return result;
    }


}
