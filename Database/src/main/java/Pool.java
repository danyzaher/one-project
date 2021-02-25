import java.lang.reflect.InvocationTargetException;
import java.sql.* ;
import javax.sql.*;
import javax.naming.*;

public  class Pool {
	public  static  void main (String[] args)  {

		Connection con = DBCPDataSource.getConnection(); 

		String sql =  "select * from \"Produit\"" ;
		Statement smt = con.createStatement() ;
		ResultSet rs = smt.executeQuery(sql) ;
		
		while (rs.next()) {
			System.out.println(rs.getArray("name")) ;
		}
	}
}

public class DBCPDataSource {
    
    private static BasicDataSource ds = new BasicDataSource();
    
    static {
        ds.setUrl("jdbc:postgresql://localhost:5432/testone");
        ds.setUsername("dany");
        ds.setPassword("123");
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);
    }
    
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
    
    private DBCPDataSource(){ }
}


