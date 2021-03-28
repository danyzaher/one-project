import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.InvocationTargetException;
import java.sql.* ;

public  class Pool {
	private final static Logger logger = LoggerFactory.getLogger(Pool.class.getName());
	public  static  void main (String[] args) {
		try {
			// Load class by its name
			Class c = Class.forName("org.postgresql.Driver") ;
			Driver pilote = (Driver)c.getDeclaredConstructor().newInstance() ;
			// Saving of the pilot with the DriverManager
			DriverManager.registerDriver(pilote);
			// Connection protocol
			String protocole =  "jdbc:postgresql:" ;
			// Host IP address of the database and the port
			String ip =  "localhost" ;
			String port =  "5432" ;  // MySQL default port
			// Database name
			String nomBase =  "testone" ;
			// Connection chain
			String conString = protocole +  "//" + ip +  ":" + port +  "/" + nomBase ;
			// Connection IDs and password
			String nomConnexion =  "julien";
			String motDePasse =  "123";
			// Connection
			Connection con = DriverManager.getConnection(conString, nomConnexion, motDePasse) ;
			logger.info("connexion reussie");
			// Sending a generic request
			String sql =  "select * from \"distance\"" ;
			Statement smt = con.createStatement() ;
			ResultSet rs = smt.executeQuery(sql) ;
			while (rs.next()) {
				System.out.println(rs.getArray("risque")) ;
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}