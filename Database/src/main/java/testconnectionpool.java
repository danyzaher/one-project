import java.sql.*;

public class testconnectionpool {
    public static void main(String[] args) throws SQLException {
        Datasource source = new Datasource(10);
        String sql =  "select * from \"Produit\"" ;
        Statement smt = source.getConnection().createStatement() ;
        ResultSet rs = smt.executeQuery(sql) ;
        while (rs.next()) {
            System.out.println(rs.getArray("name")) ;
        }
    }
}
