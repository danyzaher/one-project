import java.sql.*;
public class crud {
    public String addElement(Connection c, String Table, String column, Object value) throws SQLException {
        String sql = "INSERT INTO \"" + Table + "\"(" + column + ")" + "VALUES (" + value + ")";
        Statement smt = c.createStatement();
        ResultSet rs = smt.executeQuery(sql);
        return "successfully added";
    }

    public String eraseElement(Connection c, String Table, String column, String id) throws SQLException {
        String sql = "DELETE FROM \"" + Table + "\" WHERE " + column + " = " + id;
        Statement smt = c.createStatement();
        ResultSet rs = smt.executeQuery(sql);
        return "deleted successfully";
    }

    public String showElement(Connection c , String Table) throws SQLException {
        String sql = "select * from \"" + Table + "\"";
        Statement smt = c.createStatement();
        ResultSet rs = smt.executeQuery(sql);
        String result = null;
        while (rs.next()) {
            result = result + "/n" + rs.getArray(Table);
        }
        return result;
    }
}
//Test
