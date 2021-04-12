import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

    public void deleteElement( String Table, String idcolumn, String id) throws SQLException {
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
    public void updateElement(String Table, String column, Object value) throws SQLException {
        String sql = "update\"" + Table + "\" set " + column + " = " + value + ";";
        Statement smt = c.createStatement();
        smt.executeUpdate(sql);
    }
    public void updateElement(String Table, String column,String idcolumn, String id,Object value) throws SQLException {
        String sql = "update\"" + Table + "\" set " + column + " = " + value + "\" where " + idcolumn + " = " + id +";";
        Statement smt = c.createStatement();
        smt.executeUpdate(sql);
    }
    public void updateElement(String Table,String column,String column1,String idcolumn,String idcolumn1,Object value1,Object value2,String id,String id1) throws SQLException {
        String sql = "update\"" + Table + "\" set (" + column + ","+ column1 + ") = (" + value1 +"," + value2 + ")\" where " +idcolumn+ "=" +id+"and"+idcolumn1+"="+id1+";";
        Statement smt = c.createStatement();
        smt.executeUpdate(sql);
    }


}
