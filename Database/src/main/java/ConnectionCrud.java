import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class ConnectionCrud implements Connection {
    public void addElement(String Table, String column, Object value) throws SQLException {
        String sql = "INSERT INTO \"" + Table + "\"(" + column + ") " + "VALUES ('" + value + "');";
        Statement smt = this.createStatement();
        smt.executeUpdate(sql);
    }

    public void eraseElement(String Table, String idcolumn, int id) throws SQLException {
        String sql = "DELETE FROM \"" + Table + "\" WHERE " + idcolumn + " = " + id + ";";
        Statement smt = this.createStatement();
        smt.executeUpdate(sql);
    }

    public String showElement(String Table, String column) throws SQLException {
        String result = null;
        String sql = "select * from \"" + Table + "\";";
        Statement smt = this.createStatement();
        ResultSet rs = smt.executeQuery(sql);
        while (rs.next()) {
            result = result + String.valueOf(rs.getArray(column)) + "\n";
        }
        return result;
    }
}
