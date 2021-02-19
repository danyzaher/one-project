import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.InvocationTargetException;
import java.sql.* ;

public  class Pool {
	private final static Logger logger = LoggerFactory.getLogger(Pool.class.getName());
	public  static  void main (String[] args) {
		try {
			// chargement de la classe par son nom
			Class c = Class.forName("org.postgresql.Driver") ;
			Driver pilote = (Driver)c.getDeclaredConstructor().newInstance() ;
			// enregistrement du pilote auprès du DriverManager
			DriverManager.registerDriver(pilote);
			// Protocole de connexion
			String protocole =  "jdbc:postgresql:" ;
			// Adresse IP de l’hôte de la base et port
			String ip =  "localhost" ;
			String port =  "5432" ;  // port MySQL par défaut
			// Nom de la base 
			String nomBase =  "testone" ;
			// Chaîne de connexion
			String conString = protocole +  "//" + ip +  ":" + port +  "/" + nomBase ;
			// Identifiants de connexion et mot de passe
			String nomConnexion =  "dany";
			String motDePasse =  "123";
			// Connexion
			Connection con = DriverManager.getConnection(conString, nomConnexion, motDePasse) ;
			logger.info("connexion reussie");
			// Envoi d’un requête générique
			String sql =  "select * from \"Produit\"" ;
			Statement smt = con.createStatement() ;
			ResultSet rs = smt.executeQuery(sql) ;
			while (rs.next()) {
				System.out.println(rs.getArray("name")) ;
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